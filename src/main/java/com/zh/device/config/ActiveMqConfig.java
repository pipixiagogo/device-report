//package com.zh.device.config;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//
//import javax.jms.Queue;
//import javax.jms.Topic;
//
//
//@Configuration
//public class ActiveMqConfig {
//
//    @Value("${spring.activemq.queueName}")
//    private String queueName;
//    @Value("${spring.activemq.topicName}")
//    private String topicName;
//    @Value("${spring.activemq.broker-url}")
//    private String activeUrl;
//    @Value("${spring.activemq.user}")
//    private String activeUserName;
//    @Value("${spring.activemq.password}")
//    private String activePassword;
//
//    @Bean
//    public Queue queue(){
//        return new ActiveMQQueue(queueName);
//    }
//
//    @Bean
//    public Topic topic(){
//        return new ActiveMQTopic(topicName);
//    }
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory(){
//        return  new ActiveMQConnectionFactory(activeUserName,activePassword,activeUrl);
//    }
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        return factory;
//    }
//
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
//        factory.setPubSubDomain(true);
//        factory.setConnectionFactory(connectionFactory);
//        return factory;
//    }
//}
