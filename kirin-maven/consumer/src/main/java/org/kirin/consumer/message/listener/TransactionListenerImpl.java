package org.kirin.consumer.message.listener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author Hinsteny
 * @version TransactionListenerImpl: TransactionListenerImpl 2019-05-16 16:44 All rights reserved.$
 */
public class TransactionListenerImpl implements TransactionListener {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:{
                    logger.info("COMMIT_MESSAGE message: {}", msg.getMsgId());
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        logger.info("COMMIT_MESSAGE message: {}", msg.getMsgId());
        return LocalTransactionState.COMMIT_MESSAGE;
    }

}
