package com.example.discoveryclient.rocketmq;

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

    // 获取name为output的binding
    @Autowired
    private Source source;



    public String pushmessage(String args) {
        Message message = MessageBuilder
                .withPayload(args)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, UUID.randomUUID().toString())
                .setHeader(MessageConst.PROPERTY_TAGS,"tagStr")
                .setHeader("comment", JSON.toJSONString(args))
                .build();
        source.output().send(message);
        return "success";
    }

}

