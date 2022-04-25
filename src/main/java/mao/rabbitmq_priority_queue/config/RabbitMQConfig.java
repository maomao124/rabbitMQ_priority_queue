package mao.rabbitmq_priority_queue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Project name(项目名称)：rabbitMQ_priority_queue
 * Package(包名): mao.rabbitmq_priority_queue.config
 * Class(类名): RabbitMQConfig
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 14:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */


@Configuration
public class RabbitMQConfig
{
    /**
     * The constant EXCHANGE_NAME.
     */
    public static final String EXCHANGE_NAME = "priority_exchange";
    /**
     * The constant QUEUE_NAME.
     */
    public static final String QUEUE_NAME = "priority_queue";

    /**
     * Priority exchange direct exchange.
     *
     * @return the direct exchange
     */
    @Bean
    public DirectExchange priority_exchange()
    {
        return new DirectExchange(EXCHANGE_NAME, false, false, null);
    }

    /**
     * Priority queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue priority_queue()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-priority", 10);
        return new Queue(QUEUE_NAME, false, false, false, map);
    }

    /**
     * Priority exchange bind priority queue binding.
     *
     * @param priority_exchange the priority exchange
     * @param priority_queue    the priority queue
     * @return the binding
     */
    @Bean
    public Binding priority_exchange_bind_priority_queue(@Qualifier("priority_exchange") DirectExchange priority_exchange,
                                                         @Qualifier("priority_queue") Queue priority_queue)
    {
        return BindingBuilder.bind(priority_queue).to(priority_exchange).with("key1");
    }
}
