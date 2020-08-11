package com.example.alstarapp;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Message> messageList;
    private Context mContext;
    private ParseUser currentUser = ParseUser.getCurrentUser();

    public ChatAdapter(List<Message> messageList, Context mContext) {
        this.messageList = messageList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View contactView = layoutInflater.inflate(R.layout.item_chat, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.body1.setVisibility(View.VISIBLE);
        holder.body2.setVisibility(View.VISIBLE);

        Message message;
        try {
            message = messageList.get(position).fetchIfNeeded();
            final boolean isMe = (message.getUserSending().getObjectId() != null) && message.getUserSending().getObjectId().equals(currentUser.getObjectId());

            if (isMe) {
                holder.imageMe.setVisibility(View.VISIBLE);
                holder.imageOther.setVisibility(View.GONE);
                holder.body1.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                holder.body2.setVisibility(View.GONE);
            } else {
                holder.imageOther.setVisibility(View.VISIBLE);
                holder.imageMe.setVisibility(View.GONE);
                holder.body2.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                holder.body1.setVisibility(View.GONE);
            }

            final ImageView profileView = isMe ? holder.imageMe : holder.imageOther;
            ParseFile image = message.getUserSending().fetchIfNeeded().getParseFile("profileImg");
            profileView.setImageResource(R.drawable.ic_baseline_person_pin_24);
            if (image != null) {
                Glide.with(mContext)
                        .load(image.getUrl())
                        .circleCrop()
                        .into(profileView);
            }
            if (isMe) {
                holder.body1.setText(message.getMessageBody());
            } else {
                holder.body2.setText(message.getMessageBody());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageOther;
        ImageView imageMe;
        TextView body1, body2;

        public ViewHolder(View itemView) {
            super(itemView);
            imageOther = itemView.findViewById(R.id.ivProfileOther);
            imageMe = itemView.findViewById(R.id.ivProfileMe);
            body1 = itemView.findViewById(R.id.tvMessageBody1);
            body2 = itemView.findViewById(R.id.tvMessageBody2);
        }
    }
}
