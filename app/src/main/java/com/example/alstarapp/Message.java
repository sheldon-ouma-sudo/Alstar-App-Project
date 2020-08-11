package com.example.alstarapp;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Message")
public class Message extends ParseObject {
    public static final String KEY_UserSending = "UserSending";
    //    public static final String KEY_UserReceiving = "UserReceiving";
    public static final String KEY_messageBody = "messageBody";


    public ParseUser getUserSending() {
        return getParseUser(KEY_UserSending);
    }

    public void setUserSending(ParseUser userSending) {
        put(KEY_UserSending, userSending);
    }

    public String getMessageBody() {
        return getString(KEY_messageBody);
    }

    public void setMessageBody(String body) {
        put(KEY_messageBody, body);
    }
}
/* // getters and setters
    public ParseUser getUserSending() { return getParseUser(KEY_UserSending); }
    //    public ParseUser getUserReceiving() { return getParseUser(KEY_UserReceiving); }
    public String getMessageBody() { return getString(KEY_messageBody); }

    public void setUserSending(ParseUser userSending) { put(KEY_UserSending, userSending); }
    //    public void setUserReceiving(ParseUser userReceiving) { put(KEY_UserSending, userReceiving); }
    public void setMessageBody(String body) { put(KEY_messageBody, body); }
*/