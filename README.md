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
## import code from another repository 
```
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.
```
```
。git status显示的文件三种状态，
。红色：表示修改了原来管理起来的文件，或者是新增的文件
。绿色：表示该文件已经被git管理起来了。
```
