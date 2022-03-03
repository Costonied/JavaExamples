# RabbitMQ Examples

## Tutorials
https://www.rabbitmq.com/getstarted.html

## RabbitMQ on Docker

### Pull docker image  
`docker pull rabbitmq`

### Running the daemon
`docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3.9-management`

### RabbitMQ UI Management
`http://localhost:15672/`  
Default credentials: guest / guest