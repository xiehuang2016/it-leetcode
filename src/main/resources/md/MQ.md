## MQ

#### 消息中间件，有没有遇到丢失的情况 https://www.jianshu.com/p/4491cba335d1

- 生产者丢了数据

  - RabbitMQ 提供的事务功能，生产者发送，服务端未成功接收，生产者回滚，强一致性，一般不采用
  - RabbitMQ开启confirm模式，前者同步的，这个是异步的，发送消息后，等待服务器接收后的ack异步回调，一般用confirm模式
  - **[Kafka]**producer端设置acks=all，要求每条数据，必须是**写入所有 replica 之后，才能认为是写成功了**

- 消息中间件丢了数据

  - 开启持久化，RabbitMQ开启持久化
  - **[Kafka]**topic设置`replication.factor` 参数：这个值必须大于 1，要求每个 partition 必须有至少 2 个副本； **&** Kafka 服务端设置 `min.insync.replicas` 参数：这个值必须大于 1，这个是要求一个 leader 至少感知到有至少一个 follower 还跟自己保持联系（场景：leader挂掉，follow切leader，可能会丢数据；）

- 消费端弄丢了数据

  - 关闭RabbitMQ的自动ACK，手动消费处理完后，手动调用ACK一次；防止未消费结果进程挂了，却自动ACK。注意保持幂等
  - **[Kafka]**关闭自动提交offset，在处理完之后自己手动提交 offset，就可以保证数据不会丢。注意保持幂等

  

#### Kafka发送消息消费消息的过程

##### 发送消息

异步将记录发送到主题，并在确认发送后调用提供的回调。发送是异步的，一旦记录存储在等待发送的记录缓冲区中，此方法将立即返回。这允许并行发送多个记录，而无需在每个记录之后等待响应。

todo 



### 消息队列算法自实现基本逻辑

 https://my.oschina.net/u/3959468/blog/2989038