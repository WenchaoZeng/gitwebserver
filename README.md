# gitwebserver

基于git存储(gitlab, github等)的web服务器

## 使用方式:

比如, 有一个免登录访问的gitlab/github的raw文件地址: https://raw.githubusercontent.com/WenchaoZeng/gitwebserver/master/web/index.html

启动gitwebserver, 得到一个url地址, 比如: http://192.168.1.104:8000/index.html

修改地址为: http://192.168.1.104:8000/raw.githubusercontent.com/WenchaoZeng/gitwebserver/master/web/index.html

则可直接打开查看网页内容.

To Do List:

1. 不要自己写mime type对应关系, 用别人写好的. 

2. 打release包.

3. 处理404.