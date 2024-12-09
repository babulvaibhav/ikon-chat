package com.ikon.chat.ikon_message_producer.dto;

public class MessageDto {

    private String key;
    private String senderNumber;
    private String recipientNumber;
    private String body;

    public MessageDto() {
    }

    public MessageDto(String key, String senderNumber, String recipientNumber, String body) {
        this.key = key;
        this.senderNumber = senderNumber;
        this.recipientNumber = recipientNumber;
        this.body = body;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
