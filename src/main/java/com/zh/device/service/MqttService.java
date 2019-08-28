package com.zh.device.service;


import com.zh.device.config.PropertiesConfig;
import com.zh.device.message.JMessage;
import com.zh.device.message.trans.MessageTransformer;
import com.zh.device.message.type.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageHandler;

import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(PropertiesConfig.class)
public class MqttService {

    @Autowired
    PropertiesConfig propertiesConfig;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setKeepAliveInterval(20);
        factory.setServerURIs(propertiesConfig.getMqttServer());
        factory.setUserName(propertiesConfig.getMqttUsername());
        factory.setPassword(propertiesConfig.getMqttPassword());
        return factory;
    }
//    public B handle(MessageHandler messageHandler) {
//        return this.handle(messageHandler, (Consumer)null);
//    }
    @Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from(mqttInbound())
                .channel(c -> c.executor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1)))
                .handle(MessageTransformer::mqttMessage2JMessage)
                .<JMessage, MessageType>route(JMessage::getMessageType, (x) ->
                        x.subFlowMapping(MessageType.LIVE, onOffSub())
                                .subFlowMapping(MessageType.UNKNOW, unknowSub())
                                .subFlowMapping(MessageType.BMUUPGRADE, bmuUpgradeSub())
                                .subFlowMapping(MessageType.BREAKDOWN, breakDownSub())
                                .subFlowMapping(MessageType.CONFIG, configSub())
                                .subFlowMapping(MessageType.CONFIG_FILE, configFileSub())
                                .subFlowMapping(MessageType.RDBMSG, dataReportSub())
                                .subFlowMapping(MessageType.RDBUPGRADE, rdbUpgradSub())
                                .subFlowMapping(MessageType.RESTART,restartSub())
                                .subFlowMapping(MessageType.SYNC_TIME,syncTimeSub())
                                .defaultOutputToParentFlow())
                .get();
    }

    private IntegrationFlow syncTimeSub() {
        return f -> f.handle("syncTimeService","handle");
    }

    private IntegrationFlow restartSub() {
        return f -> f.handle("restartService","handle");
    }

    private IntegrationFlow rdbUpgradSub(){
        return f -> f.handle("rdbUpgradeService","handle");
    }
    private IntegrationFlow dataReportSub() {
        return f -> f.handle("dataReportService","handle");
    }

    private IntegrationFlow configFileSub() {
        return f -> f.handle("configFileService","handle");
    }

    private IntegrationFlow configSub() {
        return f -> f.handle("configService","handle");
    }

    private IntegrationFlow breakDownSub() {
        return f -> f.handle("mySer","handle");
    }

    private IntegrationFlow bmuUpgradeSub() {
        return f -> f.handle("bmuUpgradeService","handle");
    }

    IntegrationFlow onOffSub(){
        return f -> f.handle("onOffService","handle");
    }
    IntegrationFlow unknowSub(){
        return f -> f.handle("mySer","handle");
    }

//    @Bean
//    public MessageProducerSupport inbound() {
//        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
//        converter.setPayloadAsBytes(true);
//        MqttPahoMessageDrivenChannelAdapter adapter =
//                new MqttPahoMessageDrivenChannelAdapter(propertiesConfig.getMqttSub(), mqttClientFactory(),
//                        propertiesConfig.getMqttSubtopics());
//        adapter.setCompletionTimeout(500);
//        adapter.setConverter(converter);
//        adapter.setQos(1);
//        return adapter;
//    }
    @Bean
    public MessageProducerSupport mqttInbound() {
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        //将消息转换成字节
        converter.setPayloadAsBytes(true);
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                propertiesConfig.getMqttSub(),mqttClientFactory(),propertiesConfig.getMqttSubtopics()
        );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(converter);
        adapter.setQos(1);
        return adapter;
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(propertiesConfig.getMqttPub(), mqttClientFactory());
        messageHandler.setAsync(true);
        return messageHandler;
    }
}
