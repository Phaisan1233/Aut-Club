package com.example.autclub.ClubController;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.autclub.AppModel.App;
import com.example.autclub.AppModel.Club;
import com.example.autclub.AppModel.ThreadConnectDatabase;
import com.example.autclub.AppModel.User;
import com.example.autclub.AppModel.VolleyResponseListener;
import com.example.autclub.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClubListPageActivity extends AppCompatActivity {

    private static final String POST_PHP = "Post.php"; //php file
    private ClubsListAdapter clubsListAdapter;
    private User user;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clublist);
        requestQueue = Volley.newRequestQueue(ClubListPageActivity.this);
        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        Intent intent = getIntent();
        user = new Gson().fromJson(intent.getStringExtra("user"), User.class);
        clubsListAdapter = new ClubsListAdapter(user.getClubArrayList());
        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ClubListPageActivity.this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(clubsListAdapter);
        clubsListAdapter.setOnItemClickListener(new ClubsListAdapter.OnItemClickListener() {
            @Override
            public void onFollowClick(int position, ArrayList<Club> clubList) {
                user.setClubArrayList(clubList);
                String sql;
                if(clubList.get(position).getConnectID() == 0){
                     sql = "INSERT INTO following (connectID, user_ID, club_ID, followStatus, joinStatus) " +
                             "VALUES (NULL, "+user.getUserID()+", "+clubList.get(position).getClubID()+", 1, 0)";
                }else{
                    int n =0;
                    if(clubList.get(position).isFollowStatus()){
                        n=1;
                    }
                    sql = "UPDATE following SET followStatus = "+n+" WHERE connectID = "+clubList.get(position).getConnectID();
                }
                Map<String, String> params = new HashMap<>();
                params.put("sql", sql);


                ThreadConnectDatabase threadConnectDatabase = new ThreadConnectDatabase(requestQueue,params, POST_PHP, new VolleyResponseListener() {
                    @Override
                    public void onResponse(String response) {

                    }
                });
                threadConnectDatabase.stopThread();
                threadConnectDatabase.start();

            }

            @Override
            public void onImageClick(int position,ArrayList<Club> clubList) {
                user.setClubArrayList(clubList);
                App.newActivityPage(ClubListPageActivity.this,ClubPageActiviy.class,user.toString(), String.valueOf(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                clubsListAdapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }

}
