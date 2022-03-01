# RabbitMQ Examples

## Tutorials
https://www.rabbitmq.com/getstarted.html

## RabbitMQ on Docker

### Pull docker image  
`docker pull rabbitmq`

### Running the daemon
`docker run -d --hostname my-rabbit --name some-rabbit -p 8080:15672 -p 5672:5672 rabbitmq:3-management`

### RabbitMQ UI Management
`http://localhost:8080/`  
Default credentials: guest / guest