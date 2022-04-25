package mao.rabbitmq_priority_queue.consumer;

import mao.rabbitmq_priority_queue.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Project name(项目名称)：rabbitMQ_priority_queue
 * Package(包名): mao.rabbitmq_priority_queue.consumer
 * Class(类名): Consumer1
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:52
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Component
public class Consumer1
{
    private static final Logger log = LoggerFactory.getLogger(Consumer1.class);

    @RabbitListener(queues = {RabbitMQConfig.QUEUE_NAME})
    public void getMessage(String message)
    {
        log.info("消费者1接收到消息：" + message);
    }
}
