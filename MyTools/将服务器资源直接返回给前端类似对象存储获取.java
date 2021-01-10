protected void outputStream(HttpServletResponse response, File file, String contentType, Integer buf) throws Exception {
		if( null == contentType || "".equals(contentType) ) {
			contentType = "image/jpg";
		}
		if( null == buf || buf == 0) {
			buf = 1024;
		}
		if( null != file && file.canRead() && file.isFile() ) {
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				byte[] b = new byte[buf];
				OutputStream os = response.getOutputStream();
				int len = 0;
				while((len = is.read(b)) != -1 ) {
					os.write(b, 0, len);
				}
				os.close();
			} catch (Exception e) {
				throw new FileOperationException("文件读取错误");
			} finally {
				if( null != is ) {
					is.close();
				}
			}
		} else {
			throw new FileOperationException("文件不存在或不是文件");
		}
	}