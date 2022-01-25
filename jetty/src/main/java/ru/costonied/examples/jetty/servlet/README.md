# Embedded Jetty Server

Example showing work Jetty with Blocking Servlet and Non blocking (Async) Servlet.  
* Blocking Servlet - this class will be single threaded and block until completion
* Non blocking (Async) Servlet - this class will not block the executing thread but will perform the I/O operation in separate thread returning the result when ready using the AsyncContext.complete() method: 