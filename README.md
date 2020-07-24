# maobugs

喵喵喵

## 1.samples-web-1.2.4.war 
为 shiro <=1.2.4 硬编码漏洞的war包。说实在这个war真的是难打...

## 2.jdwp-shellifier-master.zip
自己调试的话使用
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar spring-boot-h2-0.0.1-SNAPSHOT.jar 打开jdwp端口
jdwp 端口开启了的话就能被rce ,详情解压文件readme。
这里并不是无条件rce。
有一个很容易达到的条件是：设置的方法需要能被debug到。
也就是说下的断点能被断到。https://security.tencent.com/index.php/blog/msg/137   
