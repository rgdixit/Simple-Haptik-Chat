package com.example.rgdixit.haptikchat.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by rgdixit on 5/27/16.
 */
public class ChatModelWrapper {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("messages")
    @Expose
    private ArrayList<Message> messages = new ArrayList<Message>();

    public Integer getCount() {
        return count;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public static class Message {
        @Expose
        private String body;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("image-url")
        @Expose
        private String imageUrl;
        @SerializedName("message-time")
        @Expose
        private String messageTime;


        public String getBody() {
            return body;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getMessageTime() {
            return messageTime;
        }
    }
}
