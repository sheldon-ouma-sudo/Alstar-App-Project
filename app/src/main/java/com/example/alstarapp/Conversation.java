package com.example.alstarapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

@ParseClassName("Conversation")
public class Conversation extends ParseObject {
    public static final String KEY_personOne = "personOne";
    public static final String KEY_personTwo = "personTwo";
    public static final String KEY_messages = "messages";
    public static final String KEY_updatedAt = "updatedAt";

    // getters and setters
    public ParseUser getPersonOne() {
        return getParseUser(KEY_personOne);
    }

    public void setPersonOne(ParseUser userSending) {
        put(KEY_personOne, userSending);
    }

    public ParseUser getPersonTwo() {
        return getParseUser(KEY_personTwo);
    }

    public void setPersonTwo(ParseUser userReceiving) {
        put(KEY_personTwo, userReceiving);
    }

    public Object getMessages() {
        return get(KEY_messages);
    }

    public void setMessage(List messages) {
        put(KEY_messages, messages);
    }
}
