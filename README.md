# JavaHomework

- 期末演示课堂技术的示例工程，实现学生和课程管理以及选课关系的构建
- 在docker中部署了mysql, nginx, tomcat, redis, kafka
  - 使用一台nginx和两台tomcat配置负载均衡，在nginx中配置动静分离
  - 使用两台mysql数据库配置主从复制以及读写分离
  - 使用kafka producer发送操作日志，consumer打印日志，演示消息系统
  - 部分字段使用redis进行缓存，并实现了缓存的curd
- 在两台centos虚拟机中进行配置
