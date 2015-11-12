# What's This
This is a template ssh project for reins.

# Run the Demo
## Setup environment (redis + mysql)
If you have native redis and mysql, ignore the following. Or you can use Docker to setup mysql and redis. Make sure that mysql can be access by `-uadmin -padmin`.
```bash
vagrant up  # for databases support
vagrant ssh
sudo service redis-server start
sudo service mysql start
exit
```

## Create mysql tables
```
mysql -uadmin -padmin -h 127.0.0.1
source db/create.sql;
source db/init.sql;
```

## Run It
```
mvn tomcat7:run
```

Now you can open `localhost:8080`

# Use the Code
Now I assume that you are using Eclipse.

+ Download [Lombok](https://projectlombok.org/download.html) and install it!
+ Config tomcat7. This is quite trivial.
+ Set the module context path to `/` in tomcat7. Initially it's `/reins-ssh` 
+ Right click the project name and `Run On Server`

# Explaination
Our project is based on Spring. All our objects are managed by the Spring container. They will be automatically created, assemblied, injected.

## Folder structure
+ cn.edu.sjtu.reins.ssh
    + domain: domain objects which are independent of database entities
    + service: put your service interfaces here. Note that services are not one-to-one-correspondent to DAOs. Your services should perform a full functional task.
    + web: put your web related code here, such as controllers/ajax endpoints
    + impl:
        + dao: You Daos
        + entity: database entities
        + service: service implementation
    + support: spring supports for security, etc.

## Spring config
+ application.properties: property configuration
+ application-context.xml: the main spring context, will automatically scan for beans(annotated by @Service/@Controller/@Component) and manage them in the spring container:
    + `cn.edu.sjtu.reins.ssh.impl`
    + `cn.edu.sjtu.reins.ssh.web`
    + `cn.edu.sjtu.reins.ssh.support`
    + `cn.edu.sjtu.reins.ssh.impl.dao`
+ facet/*: define various spring supports, such as redis, mysql, cache, security.
+ module-context/*: in case your need your own context, put them here. But generally you don't have to.

## Route Layer
We use struts2 for routing. There are three files/folders configuring struts2 in `src/main/resource`:

+ struts.xml
+ struts.properties: struts property definition
+ router/*: all submodule router tables are located here

There are two kinds of urls:

+ *.do: for controllers
+ /api/*: for ajax endpoints

## Service Layer

## Persistence Layer

## Misc
### Stateless Session
### Service Cache

# Todo
+ maven architype