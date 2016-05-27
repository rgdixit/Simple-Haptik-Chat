package com.example.rgdixit.haptikchat.chat;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rgdixit.haptikchat.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rgdixit on 5/27/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context mContext;
    private ArrayList<ChatModelWrapper.Message> mMessages;
    private String mUserName = "steve-t";
    private HashMap<String, Integer> mMap = new HashMap<>();

    public ChatAdapter(Context context, ArrayList<ChatModelWrapper.Message> messages) {
        mContext = context;
        mMessages = messages;
        for (ChatModelWrapper.Message message : messages) {
            Integer messagesCount = mMap.get(message.getUsername());
            if (messagesCount == null) {
                messagesCount = 0;
            }
            mMap.put(message.getUsername(), ++messagesCount);
        }
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_layout, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatModelWrapper.Message message = mMessages.get(position);


        if (message.getUsername().equals(mUserName)) {
            // my message
            holder.friendName.setVisibility(View.GONE);
            holder.friendMessage.setVisibility(View.GONE);
            holder.myName.setVisibility(View.VISIBLE);
            holder.myMessage.setVisibility(View.VISIBLE);
            holder.friendCount.setVisibility(View.GONE);
            holder.myCount.setVisibility(View.VISIBLE);
            holder.myName.setText(message.getName());
            holder.myMessage.setText(message.getBody());
            holder.myCount.setText(mContext.getString(R.string.count, mMap.get(message.getUsername())));
        } else {
            holder.myName.setVisibility(View.GONE);
            holder.myMessage.setVisibility(View.GONE);
            holder.friendMessage.setVisibility(View.VISIBLE);
            holder.friendName.setVisibility(View.VISIBLE);
            holder.friendCount.setVisibility(View.VISIBLE);
            holder.myCount.setVisibility(View.GONE);
            holder.friendName.setText(message.getName());
            holder.friendMessage.setText(message.getBody());
            holder.friendCount.setText(mContext.getString(R.string.count, mMap.get(message.getUsername())));
        }

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView myMessage, myName, friendMessage, friendName, myCount, friendCount;

        public ChatViewHolder(View itemView) {
            super(itemView);
            myMessage = (TextView) itemView.findViewById(R.id.my_message_text);
            myName = (TextView) itemView.findViewById(R.id.my_name);
            friendMessage = (TextView) itemView.findViewById(R.id.friend_message_text);
            friendName = (TextView) itemView.findViewById(R.id.friend_name);
            myCount = (TextView) itemView.findViewById(R.id.my_message_count);
            friendCount = (TextView) itemView.findViewById(R.id.friend_message_count);
        }
    }
}
