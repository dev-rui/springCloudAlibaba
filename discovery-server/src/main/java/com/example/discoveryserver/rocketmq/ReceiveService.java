package com.example.discoveryserver.rocketmq;

import lombok.extern.log4j.Log4j2;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class ReceiveService {

    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
       log.info("input1 receive: " + receiveMsg);
    }

    @StreamListener("input2")
    public void receiveInput2(String receiveMsg) {
        log.info("input2 receive: " + receiveMsg);
    }

}
