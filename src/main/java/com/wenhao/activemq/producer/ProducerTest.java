package com.wenhao.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ProducerTest {
    private static String url = "tcp://11.0.0.123:61616";

    public static void main(String[] args) throws JMSException {
        //1创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2创建连接
        Connection connection = factory.createConnection();
        //3创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4创建队列
        Queue queue = session.createQueue("my-queue");
        //5启动连接
        connection.start();
        //6创建生产者
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 10; i++) {
            //7产生消息
            TextMessage textMessage = session.createTextMessage("第" + i + "个消息");
            //8发送消息
            producer.send(textMessage);
        }
        //9关闭连接
        connection.close();
        System.out.println("消息发送完毕");
    }
}
