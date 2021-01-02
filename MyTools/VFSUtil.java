package com.app.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.poi.hwpf.usermodel.Picture;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.app.aq.SpObserver;
import com.app.common.exception.FileOperationException;


/**
 * 处理应用之外的文件，如照片、邮件附件等不依赖于应用存在而存在的文件。
 * 注意：里面的文件路径除了特殊说明外都是基于VFS根路径的
 */
public abstract class VFSUtil {
	
	//key是当前数据库名 value是 当前的vfs_root_path
	private static ConcurrentMap<String,String> rootPathCache =new ConcurrentHashMap<String,String>();
	
	private static final String DEFAULT_DATABASE_NAME = "__default__";
	
	private static final String DEFAULT_SCHOOL_ID = "devroot";
	
	private static Log log = LogFactory.getLog(VFSUtil.class);
	
	/** VFS 根路径(最后没有/号) */
	private static String VFS_ROOT_PATH;
	static {
		try {
			readVFSRootPath();//给VFS_ROOT_PATH赋初始值
		} catch (Exception e) {
			log.error("读取配置文件出错！", e);
		}
		
	}
	
	private static void readVFSRootPath() {
		String key = null;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ServiceLocator.getDataSource());

		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			key = "VFS_ROOT_PATH_WINDOWS";
		} else {
			key = "VFS_ROOT_PATH_LINUX";
		}
		
		VFS_ROOT_PATH = jdbcTemplate.queryForObject("select max(value) from dp_config where config_id = ?", new Object[]{key}, String.class);
		if (StringUtils.isBlank(VFS_ROOT_PATH)) {
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
				VFS_ROOT_PATH = "D:/vfsroot/dp";
			} else {
				VFS_ROOT_PATH = "/opt/vfsroot/dp";
			}
		}
	}
	
	/**
	 * 获取当前的VfsRootPath  
	 * @return
	 */
	public static String getVFSRootPath(){
		createRootPathIfNotExists(VFS_ROOT_PATH);
		return VFS_ROOT_PATH;
	}

	/**
	 * @param filePath 基于VFS根路径的文件路径
	 * @return
	 */
	public static String getVFSPath(String filePath) {
		return normalize(getVFSRootPath() + "/" + filePath);
	}
	
	public static String getVFSReportPath(String filePath) {
		return normalize(getVFSRootPath() +"/report"+ filePath);
	}
	
	/**
	 * 根据文件路径查找某个文件
	 * @param filePath
	 * @return
	 */
	public static File getFile(String filePath) {
		return new File(getVFSPath(filePath));
	}
	
	/**
	 * 根据文件路径查找某个文件，如果文件不存在的话则创建这个文件
	 * @param filePath
	 * @return
	 */
	public static File getFileIsNotExistCreate(String filePath) {
		File file = new File(getVFSPath(filePath));
		if(!file.exists()) {
			file = recursiveMakeDiectory(filePath, getVFSRootPath());
		}
		return file;
	}
	
	/**
	 * 根据文件路径查找某个文件，如果文件不存在的话则创建这个文件
	 * @param filePath
	 * @return
	 */
	public static File getVFSFile(String filePath) {
		return getFileIsNotExistCreate(filePath);
	}
	
	public static boolean isVFSFileExist(String filePath) {
		File file = new File(getVFSPath(filePath));
		return file.exists();
	}
	
	/**
	 * 列出某个文件夹下的子文件，不包括目录
	 * @param filePath
	 * @return
	 */
	public static List<File> listChildFiles(String filePath) {
		File parentFile = new File(getVFSPath(filePath));
		List<File> childFiles = new ArrayList<File>();
		if(parentFile.exists() && parentFile.canRead()) {
			File[] files = parentFile.listFiles();
			for (File file : files) {
				if(file.isFile()) {
					childFiles.add(file);
				}
			}
		}
		return childFiles;
	}
	
	/**
	 * 删除VFS下的某个文件
	 * @param filePath
	 */
	public static void removeVFSFile(String filePath) {
		File file = new File(getVFSPath(filePath));
		if(file.exists()) {
			try{
				FileUtils.forceDelete(file);
			}catch(IOException ex) {
				throw new FileOperationException("删除文件失败,请检查文件是否正在被使用", ex);
			}
		}
	}
	
	/**
	 * 拷贝文件到某个目录（如果不存在则新建）下
	 * @param fromFilePath
	 * @param destDir
	 * @param isIncludeSelf 如果源文件是目录的话是否拷贝自己
	 */
	public static void copyVFSFile(String fromFilePath, String destDir, boolean isIncludeSelf) {
		copyVFSFile(fromFilePath, destDir, null, isIncludeSelf);
	}
	
	/**
	 * 拷贝文件到某个目录（如果不存在则新建）下
	 * @param fromFilePath
	 * @param destDir
	 * @param newFileName 拷贝后新的文件名,如果为null则使用拷贝的文件名
	 * @param isIncludeSelf 如果源文件是目录的话是否拷贝自己
	 */
	public static void copyVFSFile(String fromFilePath, String destDir, String newFileName, boolean isIncludeSelf) {
		fromFilePath = getVFSPath(fromFilePath);
		File fromFile = new File(fromFilePath);
		if(!fromFile.exists()) {
			return;
		}
		File destDirectory = getFileIsNotExistCreate(destDir);
		if(destDirectory.exists() && !destDirectory.isDirectory()) {
			throw new FileOperationException("拷贝文件失败,目标文件不是目录");
		}
		if(fromFile.isFile()) {
			try{
				 FileUtils.copyFile(fromFile, new File(destDirectory, StringUtils.defaultIfEmpty(newFileName, fromFile.getName())), true);
			}catch(IOException ex){
				throw new FileOperationException(
						"拷贝文件【"+fromFilePath+"】到目录【"+destDir+"】下失败，失败原因：" + ex.getMessage(), ex);
			}
		}
		if(fromFile.isDirectory()) {
			if(fromFile.list().length == 0) { //空文件夹
				return;
			}
			try {
				if(isIncludeSelf) {
					copyDirectoryToDirectory(fromFile, destDirectory, StringUtils.defaultIfEmpty(newFileName, fromFile.getName()));
				}else{
					FileUtils.copyDirectory(fromFile, destDirectory);
				}
			} catch (IOException ex) {
				throw new FileOperationException(
						"拷贝目录【"+fromFilePath+"】到目录【"+destDir+"】下失败，失败原因：" + ex.getMessage(), ex);
			}
		}
	}
	
	private static void copyDirectoryToDirectory(File srcDir, File destDir, String newDirectoryName) throws IOException {
        if (srcDir == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (srcDir.exists() && srcDir.isDirectory() == false) {
            throw new IllegalArgumentException("Source '" + destDir + "' is not a directory");
        }
        if (destDir == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (destDir.exists() && destDir.isDirectory() == false) {
            throw new IllegalArgumentException("Destination '" + destDir + "' is not a directory");
        }
        FileUtils.copyDirectory(srcDir, new File(destDir, newDirectoryName), true);
    }
	
	/**
     * 允许创建多级目录，目录名之间用/隔开，暂不支持创建失败回滚的功能
     * @param  fileName 要创建的目录名
     * @return 已创建的最底层的目录
     */
	private static File recursiveMakeDiectory(String fileName, String root) {
		root = normalize(root);
		StringTokenizer directories = new StringTokenizer(normalize(fileName), "/");
		File parent = new File(root);
		while (directories.hasMoreTokens()) {
			String name = directories.nextToken();
			parent = new File(parent, name);

			if (log.isInfoEnabled()) {
				log.info("will create the directory [" 
				                                     + parent.getAbsolutePath() + "]");
			}
			if (!parent.exists()) {
				if (log.isInfoEnabled()) {
					log.info("creating the directory [" 
							+ parent.getAbsolutePath() + "]");
				}

				if (!parent.mkdir()) {
					String msg = "fail to created the directory [" 
						+ parent.getAbsolutePath() + "]";
					if (log.isInfoEnabled()) {
						log.info(msg);
					}
					throw new FileOperationException(msg);
				}
			}
		}
		return parent;
	}
	
	
	private static String normalize(String original) {
        if (log.isDebugEnabled()) {
        	log.debug("normalize前[" + original + "]");
        }
        
        original = original.replace('\\', '/');
        original = eliminateRedundantSlassh(original);
        
        /**
         * in Linux(Unix like) system, must add prefix "/", unckecked,
         * and in WindowNT, if there is no ":", shoulb add one.
         */
        if (original.indexOf(':') == -1) {
            if (!original.startsWith("/")) {
                original = "/" + original;
            }
        } else {
            if (original.startsWith("/")) {
                original = original.substring(1);
            } 
        }

        if (original.endsWith("/")) {
            original = original.substring(0, original.length() - 1);
        }
        
        if (log.isDebugEnabled()) {
        	log.debug("normalize后[" + original + "]");
        }
        
        return FileUtil.getValidFolderName(original);
    }
	
	/**
     * 从路径名称中排除多余的 "/", 如果路径中有"\", 结果我没有测试过; 通常调用这个方法之前
     * 一定要把 "\" 转成 "/"
     * @return  返回排除了多余的"/"的路径
     */
    private static String eliminateRedundantSlassh(String path) {
        if (log.isDebugEnabled()) {
        	log.debug("要排除多余的'/'之前[" + path + "]");
        }
        
        boolean isSlash = false;
        StringBuffer result = new StringBuffer(path.length());
        
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            
            if (c != '/' || !isSlash) {
                result.append(c);
            }
            
            isSlash = (c == '/');
        }

        if (log.isDebugEnabled()) {
        	log.debug("要排除多余的'/'之前[" + path + "]");
        }
        
        return result.toString();
    }
    
    public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String downloadFileName) {
    	File downloadFile = new File(getVFSPath(filePath));
    	InputStream reader = null;
		OutputStream out = null;
		try {
			reader = getInputStream(downloadFile, true);
			byte[] buf = new byte[1024];
			int len = 0;
			response.setContentType("application/octet-stream");// 均不提供直接打开
			response.setHeader("Content-Disposition",
					"attachment;filename=" + HttpUtils.convert(request, downloadFileName));
			response.setContentLength((int)downloadFile.length());
			out = response.getOutputStream();
			while ((len = reader.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			log.error("读取文件["+filePath+"]报错", e);
		} finally{
			if(reader != null){
				try{
					reader.close();
				}catch(Exception ex){
					log.error("关闭文件流出错", ex);
				}
			}
			if(out != null) {
				try{
					out.close();
				}catch(Exception ex){
					log.error("关闭输出流出错", ex);
				}
			}
		}
    }
    
    public static InputStream getInputStream(File file,boolean fileStream){
    	/**
    	 * 当前情况下不需要区分，不存在还是不可访问，而且试读一次文件流没有必要(可能占用大量内存)。
    	 */
    	if(fileStream == true){//使用文件流
    		FileInputStream fin = null;
    		try {
    			fin = new FileInputStream(file);

    		} catch (FileNotFoundException e) {
    			if (log.isDebugEnabled()) {
    				log.debug(e);
    			}
    			String msg = "找不到指定的文件[" + file.getName() + "]。";
    			if (log.isDebugEnabled()) {
    				log.debug(msg);
    			}
    			throw new FileOperationException(msg,e);
    		}
    		return fin;
    	}else{	//使用内存流
    		InputStream in = null;
    		if (file != null && file.canRead() && file.isFile()) {
    			try {
    				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    				FileInputStream stream = new FileInputStream(file);
    				BufferedInputStream bin = new BufferedInputStream(stream);
    				int len = 0;
    				byte[] b = new byte[1024];
    				while ((len = bin.read(b)) != -1) {
    					buffer.write(b, 0, len);
    				}

    				stream.close();
    				in = new ByteArrayInputStream(buffer.toByteArray());
    			} catch (Exception e) {
    				String msg = "不能读取文件[" + file.getName() + "]";
    				if (log.isErrorEnabled()) {
    					log.error(msg, e);
    				}
    				throw new FileOperationException(msg, e);
    			}
    		} else {
    			String msg = "不是文件或文件不可读[" + file.getName() + "]";
    			if (log.isDebugEnabled()) {
    				log.debug(msg);
    			}
    			throw new FileOperationException("不是文件或文件不可读");
    		}
    		return in;
    	}
    }
    

	public static void saveFile(MultipartFile thefile, String path) {
		try {
			thefile.transferTo(VFSUtil.getFileIsNotExistCreate(path));
		} catch (Exception e) {
			throw new FileOperationException("保存文件出错",e);
		}
	}
	
	public static void saveFile(byte[] fileContent, String path) {
		try {
			String filedirpath = path.substring(0, path.lastIndexOf("/"));
			String filename = path.substring(path.lastIndexOf("/")+1);
			File destFile = VFSUtil.getFileIsNotExistCreate(filedirpath);
			File file = new File(destFile.getAbsolutePath()+"/"+filename);
			FileUtils.writeByteArrayToFile(file, fileContent);
		} catch (Exception e) {
			throw new FileOperationException("保存文件出错",e);
		}
	}
	
	public static String savePic(Picture pic , String path) {
		try {
			String filedirpath = path.substring(0, path.lastIndexOf("/"));
			String filename = path.substring(path.lastIndexOf("/")+1);
			File destFile = VFSUtil.getFileIsNotExistCreate(filedirpath);
			File file = new File(destFile.getAbsolutePath()+"/"+filename);
			
			OutputStream out = new FileOutputStream(file);
			pic.writeImageContent(out);
			return file.getAbsolutePath();
		} catch (Exception e) {
			throw new FileOperationException("保存文件出错",e);
		}
	}
	
	public static String saveFile(InputStream is , String path) {
		
		OutputStream out = null;
		try {
			String filedirpath = path.substring(0, path.lastIndexOf("/"));
			String filename = path.substring(path.lastIndexOf("/")+1);
			File destFile = VFSUtil.getFileIsNotExistCreate(filedirpath);
			File file = new File(destFile.getAbsolutePath()+"/"+filename);
			
			out = new FileOutputStream(file);
			
		/*	byte[] b = new byte[1024];
			while (is.read(b) != -1) {
				out.write(b);
			} */
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			
			return file.getAbsolutePath();
		} catch (Exception e) {
			throw new FileOperationException("保存文件出错",e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new FileOperationException("保存文件出错",e);
			}
			try {
				out.close();
			} catch (IOException e) {
				throw new FileOperationException("保存文件出错",e);
			}
		}
	}
	
	/**
	 * 移动某个文件/目录到目标目录下
	 * @param src
	 * @param target
	 * @param newFileName 如果不为空的话,移动后的文件将被重命名
	 * @param isOverride 如果文件存在的话是否覆盖
	 */
	public static void moveFile(String src, String target, String newFileName, boolean isOverride) {
		src = normalize( src );
		target = normalize( target );
		if( StringUtils.isBlank(src) || StringUtils.isBlank(target) ) {
			throw new FileOperationException("移动文件失败：待移动文件或移动位置不存在");
		}
		if( src.equals( target ) ) {
			return;
		}
		
		File srcFile = new File( getVFSPath(src) );
		if( !srcFile.exists() ) {
			return;
//			throw new FileOperationException("移动文件失败：待移动的原文件不存在");
		}
		
		File targetFile = new File( getVFSPath(target) );
		if( targetFile.exists() && targetFile.isFile() ) {
			throw new FileOperationException("移动文件失败：目标不是目录");
		}
		
		if( srcFile.isFile() ) {
			File newFile = new File(targetFile, StringUtils.defaultIfEmpty(newFileName, srcFile.getName()));
			if(isOverride && newFile.exists()) {
				newFile.delete();
			}
			try{
				FileUtils.moveFile(srcFile, newFile);
			}catch(IOException ex){
				throw new FileOperationException(
						"移动文件【"+src+"】到目录【"+target+"】下失败，失败原因：" + ex.getMessage(), ex);
			}
		} else if ( srcFile.isDirectory() ) {
			File newFile = new File(targetFile, StringUtils.defaultIfEmpty(newFileName, srcFile.getName()));
			if(isOverride && newFile.exists()) {
				newFile.delete();
			}
			try{
				FileUtils.moveDirectory(srcFile, newFile);
			}catch(IOException ex){
				throw new FileOperationException(
						"移动文件【"+src+"】到目录【"+target+"】下失败，失败原因：" + ex.getMessage(), ex);
			}
		}
	}
	
	/**
	 * 移动某个文件/目录到目标目录下,如果目标目录已经存在这个文件则抛出异常
	 * @param src
	 * @param target
	 */
	public static void moveFile(String src, String target) {
		moveFile(src, target, null, false);
	}
	
	/**
	 * 不存在创建根目录
	 * @param rootPath
	 */
	private static void createRootPathIfNotExists(String rootPath){
		try{
			File file=new File(rootPath);
			if(!file.exists()){
				file.mkdirs();
			}
		}catch(Exception e){
			log.error("创建VFS失败["+rootPath+"]", e);
			throw new FileOperationException("创建VFS失败["+rootPath+"]");
		}
	}

    public static void main(String[] args) throws FileSystemException {
    	//VFSUtil.copyVFSFile("mail-attachment/5686e15f5a74423e8d06c08fa7cadf50", "mubiao", false);
    	String vsf=VFSUtil.VFS_ROOT_PATH;
    	System.out.println("......"+vsf);
    }
}
