# Technologies stack

* Maven
* Jetty - using jetty-maven-plugin
* JAX-RS - REST API Controller
* WAR - Run war via jetty

What showing example:
1. Creating simple REST Controller using JAX-RS without Spring at all.  
2. Create WAR with application.  
3. Run application (war) on Jetty (via maven plugin)

How to start:
```
mvn jetty:run-war
```