package mao.rabbitmq_priority_queue.controller;

import mao.rabbitmq_priority_queue.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * Project name(项目名称)：rabbitMQ_priority_queue
 * Package(包名): mao.rabbitmq_priority_queue.controller
 * Class(类名): MyController
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@RestController
public class MyController
{
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @Autowired
    RabbitTemplate rabbitTemplate;


    public static int getIntRandom(int min, int max)
    {
        if (min > max)
        {
            min = max;
        }
        return min + (int) (Math.random() * (max - min + 1));
    }

    public static int getIntRandom1(int min, int max)
    {
        if (min > max)
        {
            min = max;
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    @GetMapping("/test1/")
    public String test1()
    {
        //先关闭消费者
        for (int i = 0; i < 100; i++)
        {
            int priority = getIntRandom(0, 10);
            String message = "消息" + (i + 1) + "，优先级：" + priority;
            log.info("开始发送消息：" + message);
            UUID uuid = UUID.randomUUID();
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
                    "key1", message, new MessagePostProcessor()
                    {
                        @Override
                        public Message postProcessMessage(Message message) throws AmqpException
                        {
                            message.getMessageProperties().setMessageId(uuid.toString());
                            message.getMessageProperties().setPriority(priority);
                            return message;
                        }
                    });
        }
        return "完成";
    }
}
