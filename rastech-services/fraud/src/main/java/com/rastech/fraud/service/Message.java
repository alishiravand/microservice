package com.rastech.fraud.service;

/**
 * @author Ali Shiravand, 9/28/22 8:51 PM
 */
public record Message(String data) {
    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                '}';
    }
}
