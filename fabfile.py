from fabric.api import local, lcd

def prepare():
    local('docker start mysql')

def run():
    prepare()
    local('mvn spring-boot:run -Drun.arguments=--logging.level.org.springframework.orm=DEBUG')

def testUnit():
    prepare()
    local('mvn test')

def testInt():
    prepare()
    local('mvn integration-test -Pintegration')

def testAll():
    prepare()
    local('mvn integration-test')

def deploy():
    local('git checkout -b deploy')
    local('fis3 release -d src/main/resources/static -r frontend')
    local('rm -rf src/main/resources/static/import')
    local('rm -rf src/main/resources/static/s')
    local('rm -rf db frontend')
    local('git add src/main/resources/static')
    local('git push dokku deploy:master')
    local('git checkout master')
    local('git branch -d deploy')

def commit(msg = 'update'):
    print('Prepare to commit to main repo')

    with lcd('..'):
        local('git add ui')
        local('git commit -am "{}"'.format(msg))
        local('git push')

def clean():
    local('mvn clean')