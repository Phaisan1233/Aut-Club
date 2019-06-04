package com.example.autclub.MainController;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.autclub.AppModel.Message;
import com.example.autclub.R;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    private ArrayList<Message> messages = new ArrayList<>();
    private Context context;

    ChatAdapter(Context context) {
        this.context = context;
    }

    void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(position);

        TextView messageBody;
        if (message.isBelongsToCurrentUser()) {
            convertView = messageInflater.inflate(R.layout.my_message, parent,false);
            messageBody = convertView.findViewById(R.id.message_body);
            messageBody.setText(message.getMessage());
        } else {
            convertView = messageInflater.inflate(R.layout.their_message, parent,false);
            View avatar = convertView.findViewById(R.id.avatar);
            TextView name = convertView.findViewById(R.id.name);
            messageBody =  convertView.findViewById(R.id.message_body);

            name.setText(message.getName());
            messageBody.setText(message.getMessage());
            GradientDrawable drawable = (GradientDrawable) avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getColor()));
        }

        return convertView;
    }
}
