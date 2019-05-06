package com.example.autclub.ClubController;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.autclub.Club;
import com.example.autclub.ClubsListAdapter;
import com.example.autclub.R;

import java.util.ArrayList;
import java.util.List;

public class ClubListPageActivity extends AppCompatActivity {

    private List<Club> imagename = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private ClubsListAdapter Clubadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clublist);

        imagename.add(new Club("Horizon", R.drawable.horizon));
        imagename.add(new Club("Expression", R.drawable.expression));
        imagename.add(new Club("MSA", R.drawable.msa));
        imagename.add(new Club("StemWomen", R.drawable.stemwomen));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(ClubListPageActivity.this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Clubadapter = new ClubsListAdapter(ClubListPageActivity.this, imagename);
        recyclerView.setAdapter(Clubadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Club> clubs = new ArrayList<>();
                clubs = LookForClubs(clubs, s.toLowerCase());
                Clubadapter.SearchClubs(clubs);
                return true;
            }
        });
        return true;
    }

    public List<Club> LookForClubs(List<Club> n, String textinput) {
        for (int i = 0; i < imagename.size(); i++) {
            if (imagename.get(i).getName().toLowerCase().trim().contains(textinput)) {
                n.add(imagename.get(i));
            }
        }
        return n;
    }
}
