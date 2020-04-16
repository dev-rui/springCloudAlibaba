package com.example.discoveryserver.rocketmq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class Producer {
        // implements CommandLineRunner
    // 获取name为output的binding

    @Autowired
    private Source output;


   /* @Override
    public void run(String... args) throws Exception {
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, "tagStr");
        String msg=new String("producer");
        Message message = MessageBuilder
                .withPayload()
                .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader("comment", JSON.toJSONString(forObject))
                .build();

                //MessageBuilder.createMessage(msg, new MessageHeaders(headers));
        output.output().send(message);
    }*/

    public String pushmessage(String args) {
        Message message = MessageBuilder
                .withPayload(args)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader(MessageConst.PROPERTY_TAGS,"tagStr")
                .setHeader("comment", JSON.toJSONString(args))
                .build();
        //MessageBuilder.createMessage(msg, new MessageHeaders(headers));
        output.output().send(message);
        return "success";
    }

}
