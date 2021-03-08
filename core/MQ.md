### Kafka

    kafka可以通过多个消费者组的方式实现消息广播，rabbitmq不可以广播
    kafka是拉取数据模式，rabbit是推数据
    kafka数据是存储在topic这个逻辑概念下，rabbitmq存在队列中

##### 生产者

    消息先写系统缓存，定期flush到磁盘，每条消息追加到分区文件，顺序写磁盘，写入速度快
    数据写入和读取都是leader副本，follower从leader同步数据
    follower中和leader保存一定同步的机器集合为ISR集合，包含leader
    未达到一定同步的为OSR集合，AR=OSR+ISR
    ISR集合中下一条消息写入的offset为LEO，ISR中最小的LEO为HW
    生产者发送消息可以通过get()方法阻塞等待消息发送成功的响应
    也可以直接发送，通过回调的方式判断是否发送成功，发送失败增加业务处理
    生产端有个参数配置acks 可以配置为0，1，-1
    0:表示生产者发送完消息后不会等待leader的确认
    1：表示写入leader后立即返回响应
    -1: 表示写入leader成功并ISR集合中的副本同步结束返回响应

    保证生产者消息不丢失？同步方式，收到发送成功的响应后再发送下一条，acks设置为-1，同步完ISR集合后再返回响应



##### 消费者

    使用了nio的零拷贝技术，较少了系统缓存到kafka进程以及kafka进程到socket buffer的两次拷贝和上下文切换 读取速度快
    kafka进程发出sendfile系统调用，内核向磁盘请求数据，通过DMA方式从磁盘读取数据到内核缓存，复制文件描述符到socket buffer（文件描述符包括，内核buffer地址以及要读取的长度）
    协议引擎从socket buffer获取文件描述符，DMA方式读取内核缓存数据


    保证消费者不丢失数据，需要关闭自动提交，改为手动提交offset
    消息不重复消费？逻辑去重

    消费者手动提交offset分为同步和异步两种方式，同步是提交完成再拉取下一个批次消息，异步为提交后不等待结果直接拉取下个批次



###### kafka为什么这么快？

    - 磁盘顺序写：kafka分区消息有序，新的消息追加到partition末尾
    - partition并行处理：集群下不同partition可以在不同机器下，实现partition并行处理
    - 数据写入broker时写入page Cache由系统控制刷盘
    - producer发送数据到broker时，通过Memory map实现快速写入
    - consumer从broker读取数据时，通过senfile 从磁盘读取数据到内核缓冲区，发送到网卡	
