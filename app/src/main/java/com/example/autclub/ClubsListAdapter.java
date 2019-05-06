package com.example.autclub;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.autclub.ClubController.ClubPageActiviy;
import com.example.autclub.MainController.NewsfeedActivity;

import java.util.ArrayList;
import java.util.List;

public class ClubsListAdapter extends RecyclerView.Adapter<ClubsListAdapter.ClubsListHolder> {

    List<Club> clubs;
    Context context;

    public ClubsListAdapter(Context context, List<Club> clubs) {
        this.context = context;
        this.clubs = clubs;
    }

    public ClubsListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image, viewGroup, false);
        ClubsListHolder clubs = new ClubsListHolder(view, context, this.clubs);
        return clubs;
    }

    @Override
    public void onBindViewHolder(ClubsListHolder viewHolder, int i) {
        int imageid = clubs.get(i).getImage();
        viewHolder.clubimages.setImageResource(imageid);
        viewHolder.FollowClubs.setText("FOLLOW " + clubs.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }

    public void SearchClubs(List<Club> newList) {
        clubs = new ArrayList<>();
        clubs.addAll(newList);
        notifyDataSetChanged();

    }

    public static class ClubsListHolder extends RecyclerView.ViewHolder {
        final Context context;
        ImageView clubimages;
        Button FollowClubs;
        List<Club> name;

        public ClubsListHolder(@NonNull View itemView, final Context context, List<Club> name) {
            super(itemView);

            clubimages = itemView.findViewById(R.id.msa1);
            FollowClubs = itemView.findViewById(R.id.image1_title);

            clubimages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(context, ClubPageActiviy.class);
                    context.startActivity(intent1);
                }
            });
            FollowClubs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, NewsfeedActivity.class);
                    if (FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW MSA")) {
                        in.putExtra("ReplyMsa", "MSA CLUB");
                        in.putExtra("ReplyExpression", "NO");
                        in.putExtra("ReplyHorizon", "NO");
                        in.putExtra("ReplyStemwomen", "NO");
                    }
                    if (FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW EXPRESSION")) {
                        in.putExtra("ReplyExpression", "EXPRESSION CLUB");
                        in.putExtra("ReplyMsa", "NO");
                        in.putExtra("ReplyHorizon", "NO");
                        in.putExtra("ReplyStemwomen", "NO");
                    }
                    if (FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW HORIzON")) {
                        in.putExtra("ReplyHorizon", "HORIZON CLUB");
                        in.putExtra("ReplyExpression", "NO");
                        in.putExtra("ReplyMsa", "NO");
                        in.putExtra("ReplyStemwomen", "NO");
                    }
                    if (FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW STEMWOMEN")) {
                        in.putExtra("ReplyStemwomen", "STEMWOMEN CLUB");
                        in.putExtra("ReplyMsa", "NO");
                        in.putExtra("ReplyHorizon", "NO");
                        in.putExtra("ReplyExpression", "NO");
                    }
                    context.startActivity(in);
                }
            });
            this.context = context;

            this.name = name;
        }


    }
}
