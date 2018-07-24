package com.example.mqapp;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
// This class receives the message
@Component
public class Consumer {
    private CountDownLatch latch = new CountDownLatch(1);

    public void consumeMessage(String message){
        System.out.println("Received <"+ message+">");
        latch.countDown();
    }
    public CountDownLatch getLatch(){
        return latch;
    }
}
