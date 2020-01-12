package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket message model
 */
public class Message {

    public static final String ENTER = "ENTER";
    public static final String CHAT = "CHAT";
    public static final String QUIT = "QUIT";

    private String userName;
    private int onlineUsersCount;
    private String message;
    private String type;

    public static String jsonString(String userName, int onlineUsersCount, String message, String type) {
        return JSON.toJSONString(new Message(userName, onlineUsersCount, message, type));
    }

    public Message(String userName, int onlineUsersCount, String message, String type) {
        this.userName = userName;
        this.onlineUsersCount = onlineUsersCount;
        this.message = message;
        this.type = type;
    }

    public String getENTER() {
        return ENTER;
    }

    public String getCHAT() {
        return CHAT;
    }

    public String getQUIT() {
        return QUIT;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOnlineUsersCount() {
        return onlineUsersCount;
    }

    public void setOnlineUsersCount(int onlineUsersCount) {
        this.onlineUsersCount = onlineUsersCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
