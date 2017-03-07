
# 自动生成代码
通过mysql information_schema获取到表的字段信息，
然后通过这些信息生成相应的代码。
借鉴了MyBatis-Generator的思想。
# 文件配置
jdbcUrl=jdbc:mysql://127.0.0.1:3306/alchemystar\_db?useUnicode=true&characterEncoding=gbk    
appName=lancer   
dbName=test   
dbUserName=123   
password=123   
tables=t_test   
# 运行方式
运行ShellRunner.main即可
