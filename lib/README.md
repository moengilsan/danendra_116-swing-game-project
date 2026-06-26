# JDBC driver

Place the MySQL Connector/J driver JAR in this directory. The launcher looks
for a file named like `mysql-connector-j-*.jar`.

The compile script does not need the driver, but the application and database
integration test require it at runtime.
