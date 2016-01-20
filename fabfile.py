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

def deploy_only():
    local('git push dokku master')

def deploy(msg = 'update'):
    print('Prepare to deploy to dokku')

    local('git add .')
    local('git commit -am "{}"'.format(msg))
    local('git push dokku master')

def commit(msg = 'update'):
    print('Prepare to commit to main repo')

    with lcd('..'):
        local('git add ui')
        local('git commit -am "{}"'.format(msg))
        local('git push')

def clean():
    local('mvn clean')