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
    ArrayList<Message> messages = new ArrayList<>();
    Context context;

    public View avatar;
    public TextView name;
    public TextView messageBody;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void add (Message message){
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

        if (message.isBelongsToCurrentUser()) {
            convertView = messageInflater.inflate(R.layout.my_message, null);
            messageBody = (TextView) convertView.findViewById(R.id.message_body);
            //convertView.setTag(holder);
            messageBody.setText(message.getMessage());
        } else {
            convertView = messageInflater.inflate(R.layout.their_message, null);
            avatar = (View) convertView.findViewById(R.id.avatar);
            name = (TextView) convertView.findViewById(R.id.name);
            messageBody = (TextView) convertView.findViewById(R.id.message_body);
            //convertView.setTag(holder);

            name.setText(message.getName());
            messageBody.setText(message.getMessage());
            GradientDrawable drawable = (GradientDrawable) avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getColor()));
        }

        return convertView;
    }
}
