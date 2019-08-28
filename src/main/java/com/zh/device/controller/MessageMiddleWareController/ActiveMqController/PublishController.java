//package com.zh.device.controller.MessageMiddleWareController.ActiveMqController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.jms.Queue;
//import javax.jms.Topic;
//
//@RestController
//public class PublishController {
//
//    @Autowired
//    private JmsMessagingTemplate template;
//
//    @Autowired
//    private Queue queue;
//
//    @Autowired
//    private Topic topic;
//
//    @RequestMapping("/queue")
//    public String queue(){
//        for(int i=0;i<100;i++){
//            template.convertAndSend(queue,"你是真的皮"+i);
//        }
//        return "发送成功queue";
//    }
//
//    @RequestMapping("/topic")
//    public String topic(){
//        for(int i=0;i<10;i++){
//            template.convertAndSend(topic,"皮皮虾"+i);
//        }
//        return "发送成功topic";
//    }
//
//    @JmsListener(destination = "out.queue")
//    public void consumerMsg(String msg){
//        System.out.println(msg);
//    }
//}
