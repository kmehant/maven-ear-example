# After decoupling WAR and EJB (accessible remotely)

## Project Execution:

clone the repository
```
$ git clone https://github.com/kmehant/maven-ear-example.git
```

Move to the root of the repository
```
$ cd maven-ear-example
```

Move to ejb module
```
$ cd module-ejb
```

Build JAR file and add it to local repository for access to WAR (at package time)
```
$ mvn clean compile package install
```

Move to web module from the root of the project
```
$ cd module-web
```

Build WAR file
```
$ mvn clean compile package
```

Now simply deploy both of the packages separately on JBoss Windlify

you will access project at http://127.0.0.1:8080/module-web-1.0.0 



