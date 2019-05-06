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

import java.util.ArrayList;
import java.util.List;

public class ClubsListAdapter extends RecyclerView.Adapter<ClubsListAdapter.ClubsListHolder>  {


    List<Clubs> name;
    Context context;


    public ClubsListAdapter(Context c, List<Clubs>name){

        this.context=c;
        this.name=name;

    }

    public ClubsListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View r= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image,viewGroup,false);
        ClubsListHolder clubs=new ClubsListHolder(r,context,name);
        return clubs;
    }

    @Override
    public void onBindViewHolder(ClubsListHolder viewHolder, int i) {
        int imageid = name.get(i).getImage();
        viewHolder.clubimages.setImageResource(imageid);
        viewHolder.FollowClubs.setText("FOLLOW "+name.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }


   public void SearchClubs(List<Clubs> newList){
       name=new ArrayList<>();
       name.addAll(newList);
       notifyDataSetChanged();

   }


    public static class ClubsListHolder extends RecyclerView.ViewHolder  {
        ImageView clubimages;
        Button FollowClubs;
       final  Context c;
        List<Clubs> name;




        public ClubsListHolder(@NonNull View itemView, final Context c, List<Clubs>name) {
            super(itemView);

            clubimages=itemView.findViewById(R.id.msa1);
            FollowClubs=itemView.findViewById(R.id.image1_title);

            clubimages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(c,Clubpage.class);


                    c.startActivity(intent1);
                }
            });
            FollowClubs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in=new Intent (c,Newsfeed.class);
                    if(FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW MSA")){
                        in.putExtra("ReplyMsa","MSA CLUB");
                        in.putExtra("ReplyExpression","NO");
                        in.putExtra("ReplyHorizon","NO");
                        in.putExtra("ReplyStemwomen","NO");
                    }if(FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW EXPRESSION")){
                        in.putExtra("ReplyExpression","EXPRESSION CLUB");
                        in.putExtra("ReplyMsa","NO");
                        in.putExtra("ReplyHorizon","NO");
                        in.putExtra("ReplyStemwomen","NO");
                    }if(FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW HORIzON")){
                        in.putExtra("ReplyHorizon","HORIZON CLUB");
                        in.putExtra("ReplyExpression","NO");
                        in.putExtra("ReplyMsa","NO");
                        in.putExtra("ReplyStemwomen","NO");
                    }if(FollowClubs.getText().toString().equalsIgnoreCase("FOLLOW STEMWOMEN")){
                        in.putExtra("ReplyStemwomen","STEMWOMEN CLUB");
                        in.putExtra("ReplyMsa","NO");
                        in.putExtra("ReplyHorizon","NO");
                        in.putExtra("ReplyExpression","NO");
                    }

                    c.startActivity(in);
                }
            });

            this.c=c;

            this.name=name;
        }
















    }
}
