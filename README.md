# What's This
This is a template ssh project for reins.

# Usage
## Setup environment
If you have native redis and mysql, ignore the following.
```bash
vagrant up  # for databases support
vagrant ssh
sudo service redis-server start
sudo service mysql start
exit
mysql -uadmin -padmin -h 127.0.0.1
source db/create.sql;
source db/init.sql;
```

## Setup Lombok
I used Lombok extensively in this project, refer to [Lombok Website](https://projectlombok.org/) for details.