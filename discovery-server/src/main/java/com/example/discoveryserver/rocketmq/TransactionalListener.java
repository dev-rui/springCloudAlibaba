package com.example.discoveryserver.rocketmq;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@Log4j2
@RocketMQTransactionListener(txProducerGroup = "test-stream-rocketmq-transactional")
public class TransactionalListener implements RocketMQLocalTransactionListener {
    /**
     * 处理本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        // 消息头
        MessageHeaders headers = message.getHeaders();
        String Payload=new String((byte[]) message.getPayload());
        String transactionalId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        String comment = headers.get("comment").toString();
        try {
            log.info("mq处理本地事务:{},{},{}",Payload,transactionalId,comment);
            // 本地业务
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 若在本地事务执行过程中缺少二次确认消息或生产者处于等待状态
     * MQ Server将向同一组中的每个生产者发送检查消息
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        try {
        MessageHeaders headers = message.getHeaders();
        String transactionalId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        String comment = headers.get("comment").toString();
        log.info("mq检查事务:{},{}",transactionalId,comment);
            // 检查业务
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
