package org.kirin.consumer.message.body;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hinsteny
 * @version UserCreateEventMessage: UserCreateEventMessage 2019-05-13 17:29 All rights reserved.$
 */
public class EventMessageInfo<T> {

    private String topic;

    private String tags;

    private Map<String, String> properties = new HashMap<>();

    private int retryTimes;

    private T data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
