Linux安装dockers，谷粒商城安装Dokcer容器

### 安装docker
```
卸载旧版本
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

### 安装docker需要依赖的包

```
sudo yum install -y yum-utils

告诉Linux，Docker安装地址
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```
### 安装Docker引擎，客户端，容器

```
sudo yum install docker-ce docker-ce-cli containerd.io
```

### 启动
```
sudo systemctl start docker
```

### 设置开机自启
```
systemctl enable docker
```
### 配置阿里云镜像加速（CnetOS）
```
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201130213501172.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3MzY5NTQ1,size_16,color_FFFFFF,t_70)
### 执行命令重启
```
创建文件夹
sudo mkdir -p /etc/docker

配置镜像加速器地址
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://这里需要登陆阿里云.com"]
}
EOF

重启docker的后台线程
sudo systemctl daemon-reload

重启docker的服务
sudo systemctl restart docker

```

## 下面放大招
### docker安装mysql
```
 docker pull mysql:5.7

查看docker中镜像
docker images
```
### 创建实例并启动
```
docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201130213838602.png)
### 创建mysql配置文件
```
vi /mydata/mysql/conf/my.cnf
```
### 写入配置信息
```
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
```
### 重启mysql容器
```
docker restart mysql
```
### 进入mysql容器内部，并查看文件目录
```
 docker exec -it mysql /bin/bash
 
```
## docker安装redis
### 下载最新镜像
```
docker pull redis
```
###   创建目录结构
```
mkdir -p /mydata/redis/conf

创建配置文件
touch /mydata/redis/conf/redis.conf


```
### 安装redis（并挂载配置文件）
```
docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```
###  连接到docker的redis
```
docker exec -it redis redis-cli

```
### 测试redis（exit：退出）
```
127.0.0.1:6379> set a b
OK
127.0.0.1:6379> get a
"b"


127.0.0.1:6379> exit
docker restart redis
```
### 修改redis配置文件（设置持久化）
```
appendonly yes
```
###  设置容器在docker启动的时候启动
```
docker update mysql --restart=always
docker update redis --restart=always
```
## docker安装nginx
### 首先要cd到mydata文件夹下面，创建文件夹
```
mkdir nginx

下载并启动
docker run -p 80:80 --name nginx -d nginx:1.10
```
### 将容器内的配置文件拷贝到当前nginx目录（注意此时我们的位置在mydata文件夹下）
```
docker container cp nginx:/etc/nginx .
```

### 停止nginx容器并删除nginx镜像
```
docker stop nginx
docker rm nginx
```
### 重命名nginx文件夹为conf
```
mv nginx conf

再次创建nginx文件夹
mkdir nginx

将conf移动到nginx
mv conf nginx/
```
### 再次创建docker实例
```
docker run -p 80:80 --name nginx \
-v /mydata/nginx/html:/usr/share/nginx/html \
-v /mydata/nginx/logs:/var/log/nginx \
-v /mydata/nginx/conf:/etc/nginx \
-d nginx:1.10
```
### 设置开机启动
```
docker update nginx --restart=always
```
```
访问http://http://30.37.112.250/

默认访问html文件夹，在html文件夹下创建index.html，并写入<h1>hello</h1>，再次访问就可以访问到

在html文件夹下创建文件夹es，在es文件夹创建test.text，写入张三

页面访问http://30.37.112.250/es/test.text即可访问到
```
##  docker安装RabbitMQ

```
 docker run -d --name rabbitmq -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15671:15671 -p 15672:15672 rabbitmq:management
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020113021510297.png)

查看 docker 容器使用的资源（占用CPU,内存的情况）
```
docker stats
```

### docker 安装elasticsearch
```
docker pull elasticsearch:7.4.2

创建挂载目录
mkdir -p /mydata/elasticsearch/config
mkdir -p /mydata/elasticsearch/data

写入文件,可被远程任何机器访问
echo "http.host: 0.0.0.0" > /mydata/elasticsearch/config/elasticsearch.yml

运行，暴漏两个端口  9200 访问端口 9300 节点通信端口  单节点启动
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 --privileged=true \
 -e ``"discovery.type=single-node"`  `\
 -e ES_JAVA_OPTS=``"-Xms64m -Xms128m"`  `\
 -``v` `/mydata/elasticsearch/config/elasticsearch``.yml:``/usr/share/elasticsearch/config/elasticsearch``.yml   \
 -``v` `/mydata/elasticsearch/data``:``/usr/share/elasticsearch/data`   `\
 -``v` `/mydata/elasticsearch/plugins``:``/usr/share/elasticsearch/plugins`   `\
 -d elasticsearch:7.4.2
```
需求给挂载文件夹授予权限
否则没法启动
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206164224412.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3MzY5NTQ1,size_16,color_FFFFFF,t_70)
docker start 26baf8009232
##### 设置开启自启动
```
docker update Id号 --restart=always
```
如此就可以访问了，但是虚拟机需要设置网络端口 阿里云服务器需要设置 安全组 开发9200端口
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206164532291.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3MzY5NTQ1,size_16,color_FFFFFF,t_70)
http://192.168.56.1:9200/


### 安装kibana
```
docker pull kibana:7.4.2
# 一定记得改成自己的虚拟机（服务器地址）
docker run --name kibana -e ELASTICSEARCH_HOSTS=http://192.168.56.1:9200 -p 5601:5601 -d kibana:7.4.2

设置开启自启动
docker update Id号 --restart=always

```
设置虚拟机地址映射 
访问 192.168.56.1:5601
需要等一会才能访问成功
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206165905392.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3MzY5NTQ1,size_16,color_FFFFFF,t_70)
几个cat命令
```
GET /_cat/nodes: 查看所有节点
GET /_cat/health: 查看es健康状况
GET /_cat/master: 查看主节点
GET /_cat/indices: 查看所有索引
```
### 分词器 Ik  支持中文分词
```
https://github.com/medcl/elasticsearch-analysis-ik/releases/tag/v7.4.2

下载zip直接解压 放到挂载的plugins文件夹下面
重启Electicsearch
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201206181446491.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3MzY5NTQ1,size_16,color_FFFFFF,t_70)

## 虚拟机平不同外网
```
1、开启密码登录
vi /etc/ssh/sshd_config
修改 PasswordAuthentication yes
重启服务 service sshd restart  这样就可Xshell登录了

2、修改网卡
cd /etc/sysconfig/network-scripts/
修改eth1
NM_CONTROLLED=yes
BOOTPROTO=none
ONBOOT=yes
IPADDR=192.168.56.1
NETMASK=255.255.255.0
GATEWAY=192.168.56.1
DNS1=114.114.114.114
DNS2=8.8.8.8
DEVICE=eth1
PEERDNS=no
#VAGRANT-END

重启网卡
service network restart
```



















