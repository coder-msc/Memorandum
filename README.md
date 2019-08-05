# git1

##create a new repository on the command line
echo "# git1" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/Devil-K/git1.git
git push -u origin master

##push an existing repository from the command line
git remote add origin https://github.com/Devil-K/git1.git
git push -u origin master

##import code from another repository
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.