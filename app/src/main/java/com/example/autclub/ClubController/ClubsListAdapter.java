package com.example.autclub.ClubController;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.example.autclub.AppModel.Club;
import com.example.autclub.R;

import java.util.ArrayList;
import java.util.List;

public class ClubsListAdapter extends RecyclerView.Adapter<ClubsListAdapter.ClubsListHolder> implements Filterable {

    private ArrayList<Club> clubList;
    private ArrayList<Club> clubListFull;
    private OnItemClickListener listener;

    public ArrayList<Club> getClubList() {
        return clubList;
    }

    public interface OnItemClickListener {
        void onFollowClick(int position,ArrayList<Club> clubList);

        void onImageClick(int position,ArrayList<Club> clubList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ClubsListAdapter(ArrayList<Club> clubList) {
        clubList.get(0).setImage(R.drawable.msa);
        clubList.get(1).setImage(R.drawable.expression);
        clubList.get(2).setImage(R.drawable.horizon);
        clubList.get(3).setImage(R.drawable.stemwomen);
        this.clubList = clubList;
        clubListFull = new ArrayList<>(clubList);
    }

    public ClubsListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image, viewGroup, false);
        return new ClubsListHolder(view);
    }

    @Override
    public void onBindViewHolder(ClubsListHolder viewHolder, int i) {
        Club club = clubList.get(i);
        viewHolder.clubimages.setImageResource(club.getImage());
        String followStatus = "FOLLOW ";
        if (club.isFollowStatus()) {
            followStatus = "FOLLOWING ";

        }
        viewHolder.followClubs.setText(followStatus + club.getName());
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Club> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(clubListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Club club : clubListFull) {
                    if (club.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(club);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clubList.clear();
            clubList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ClubsListHolder extends RecyclerView.ViewHolder {
        ImageView clubimages;
        Button followClubs;

        public ClubsListHolder(@NonNull View itemView) {
            super(itemView);
            clubimages = itemView.findViewById(R.id.msa1);
            followClubs = itemView.findViewById(R.id.image1_title);

            followClubs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            clubList.get(position).setChangeFollowStatus();
                            ArrayList<Club> clubs = clubList;
                            listener.onFollowClick(position,clubs);
                            notifyDataSetChanged();
                        }
                    }
                }
            });

            clubimages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            ArrayList<Club> clubs = clubList;
                            listener.onImageClick(position,clubs);
                        }
                    }
                }
            });
        }
    }
}
