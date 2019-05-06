package com.example.autclub.ClubController;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.autclub.AppModel.Club;
import com.example.autclub.AppModel.ClubsListAdapter;
import com.example.autclub.R;

import java.util.ArrayList;
import java.util.List;

public class ClubListPageActivity extends AppCompatActivity {

    private List<String> imagestag = new ArrayList<>();
    private List<Club> imagename = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private ClubsListAdapter Clubadapter;
    public static ArrayList<String> description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clublist);

        imagestag.add("horizon");
        imagestag.add("expression");
        imagestag.add("msa");
        imagestag.add("stem");

        imagename.add(new Club("Horizon", R.drawable.horizon));
        imagename.add(new Club("Expression", R.drawable.expression));
        imagename.add(new Club("MSA", R.drawable.msa));
        imagename.add(new Club("StemWomen", R.drawable.stemwomen));

        description = new ArrayList<>();

        description.add("The Muslim Students Association(MSA) at Auckland University of Technology (AUT) is dedicated to helping our fellow brothers and sisters.");
        description.add("Horizon. ADP is a club from AUT who focus on creating a sense of community wihtin the AUT student body. The club provides social events, casual dance classes and opportunities for students who want to further develop themselves both in dance and in their own lives. We believe every day should be Pink Shirt Day and love our craft!");
        description.add("Expression is a fun way to express yourself through dance! All styles are welcome. Join in to have fun, learn a few moves and loosen up from your hectic uni life!");
        description.add("This group is to support and encourage women in STEM and help them develop the skills they need to do effective networking and succeed in their field of study.");

        RecyclerView recyclerView = findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(ClubListPageActivity.this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Clubadapter = new ClubsListAdapter(ClubListPageActivity.this, imagename, imagestag);
        recyclerView.setAdapter(Clubadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
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
