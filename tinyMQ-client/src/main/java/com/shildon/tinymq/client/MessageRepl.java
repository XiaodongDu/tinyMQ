package com.shildon.tinymq.client;

import com.shildon.tinymq.core.model.*;
import com.shildon.tinymq.core.util.ProtostuffSerializeUtils;

import java.util.Scanner;

/**
 * @author shildon
 */
public final class MessageRepl {

    public static void main(String[] args) {
        MessageClient messageClient = new MessageClient("127.0.0.1", 10101);
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!"exit".equals(line)) {
            try {
                line = scanner.nextLine();
                if ("publish".equals(line)) {
                    MessageRequestHeader messageRequestHeader = new MessageRequestHeader(System.currentTimeMillis(), 1, Operation.PUBLISH);
                    PublishMessageRequestBody publishMessageRequestBody = new PublishMessageRequestBody("test", "test".getBytes());
                    byte[] serializedData = ProtostuffSerializeUtils.serialize(publishMessageRequestBody);
                    MessageRequestBody messageRequestBody = new MessageRequestBody(serializedData);
                    MessageRequest messageRequest = new MessageRequest(messageRequestHeader, messageRequestBody);
                    messageClient.send(messageRequest);
                } else if ("subscribe".equals(line)) {
                    MessageRequestHeader messageRequestHeader = new MessageRequestHeader(System.currentTimeMillis(), 1, Operation.SUBSCRIBE);
                    SubscribeMessageRequestBody subscribeMessageRequestBody = new SubscribeMessageRequestBody("test");
                    byte[] serializedData = ProtostuffSerializeUtils.serialize(subscribeMessageRequestBody);
                    MessageRequestBody messageRequestBody = new MessageRequestBody(serializedData);
                    MessageRequest messageRequest = new MessageRequest(messageRequestHeader, messageRequestBody);
                    messageClient.send(messageRequest);
                } else if ("close".equals(line)) {
                    messageClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}