package com.wenhao.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTest {
    private static String url = "tcp://11.0.0.123:61616";

    public static void main(String[] args) throws JMSException {
        System.out.println("002");
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
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
