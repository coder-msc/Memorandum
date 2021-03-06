### 新版本GitHub操作

### …or create a new repository on the command line
```
echo "# SpringSecrityDemo" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:coder-msc/SpringSecrityDemo.git
git push -u origin main
```

### 创建已存在仓库 or push an existing repository from the command line
```$xslt
git remote add origin git@github.com:coder-msc/SpringSecrityDemo.git
git branch -M main
git push -u origin main
```
--------------------------------------------------------------------------------------------------------




# git
 基本操作
```
git 的基本操作
```
## create a new repository on the command line 
```
git config --global user.email 1771947201@qq.com//设置账户，邮箱
git config --list // 查看账户信息
echo "# git1" >> README.md
git init 
git add README.md
git commit -m "first commit" 
git remote add origin https://github.com/Devil-K/git1.git 
git push -u origin master 
git reset --hard 39fa9a1bccf72e6a97756c28209617ca  //后面接上版本编号，即可回滚到那个版本

git reflog //
回滚后，前面的版本会不见，此时用git reflog 即可查看所有的版本（包括回滚查看不到的），此时用git reset --hard 版本号，即可前滚（回滚）
```
```
git branch //查看分支
git branch  dev //创建Dev分支
git branch bug //创建bug分支
当在分支上开发/修改bug后 提交后在合并到主分支上（master）
1、先回到主分支 git checkout master
2、git merge dev 合并到主分支上
3、记得卸磨杀驴，修改完bug后一般要删除bug分支    git branch -d bug 

注意：若多个分支修改了同一行代码，则会产生冲突，需要手动解决冲突在进行提交至主分支
```
## push an existing repository from the command line 
```
git remote add origin https://github.com/Devil-K/git1.git 
git push -u origin master 
```

```
git pull –rebase origin master意为先取消commit记录，并且把它们临时 保存为补丁(patch)(这些补丁放到”.git/rebase”目录中)，之后同步远程库到本地，最后合并补丁到本地库之中。
```
## 克隆远程仓库到本地
```
1、git clone 地址
2、进入拉取完成的目录在 git init 
3、之后才可以修改进行提交
```
## 快速解决冲突
```
1、安装beyond compare
2、在git中配置
。git config --local merge.tool bc3
。git config --local mergetool.path '/url/local/bin/bcomp'
。git config --local mergetool.keepBackup false  //不保留备份文件
3、应用beyond compare解决冲突
。git mergetool

在软件里用图形界面方式直接修改即可
```
## 实际开发中
```
1、先克隆代码到本机；
2、切换到DEV分支进行开发，将Master分支的代码merge到Dev分支；
3、进行开发 （修改代码，提交，推送）
4、第二台电脑（家）开发时候，切换到DEV分支，拉代码，git pull origin Dev
5、继续开发，提交
6、第一台电脑（公司）再次拉代码
7、需要上线，本地切换到Master分支，将DEV合并到Master分支；
8、将分支都提交（推送）
```
## import code from another repository 
```
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.
```
```
。git status显示的文件三种状态，
。红色：表示修改了原来管理起来的文件，或者是新增的文件
。绿色：表示该文件已经被git管理起来了。
```






