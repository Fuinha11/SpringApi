package com.marcos.springapi.service;

public interface QueueService {
    void publishSession();
    void consumeSession(String content);
}
