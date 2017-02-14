package com.sonikro.deeptalkme.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.model.chat.ChatMessage;
import com.sonikro.deeptalkme.model.chat.MessageList;

/**
 * Created by Jonathan Nagayoshi on 9/07/2016.
 */
public class ChatMessagesAdapter extends RecyclerView.Adapter<ChatMessagesAdapter.MessageViewHolder> {

    private MessageList chatMessageList;

    public ChatMessagesAdapter(MessageList messageList)
    {
        this.chatMessageList = messageList;
    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                viewType ,parent,false
        );
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        ChatMessage message = chatMessageList.get(position);
        holder.message.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessageList.get(position).getMessageLayout();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder
    {
        public TextView message;
        public MessageViewHolder(View view)
        {
            super(view);
            message = (TextView) view.findViewById(R.id.message);
        }
    }
}
