package com.example.autclub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class listing extends AppCompatActivity {

    private List<Integer> images=new ArrayList<>();
    private List<Clubs> imagename=new ArrayList<>();
    private RecyclerView.LayoutManager ly;
    private ClubsListAdapter Clubadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clublist);
        images.add(R.drawable.horizon);
        images.add(R.drawable.expression);
        images.add(R.drawable.msa);
        images.add(R.drawable.stemwomen);
        imagename.add(new Clubs("Horizon",R.drawable.horizon));
        imagename.add(new Clubs("Expression",R.drawable.expression));
        imagename.add(new Clubs("MSA",R.drawable.msa));
        imagename.add(new Clubs("StemWomen",R.drawable.stemwomen));

        RecyclerView r=findViewById(R.id.recycler);
        ly=new GridLayoutManager(listing.this,2);
        r.setHasFixedSize(true);
        r.setLayoutManager(ly);
        Clubadapter=new ClubsListAdapter(listing.this,imagename);
        r.setAdapter(Clubadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem search=menu.findItem(R.id.search);
        SearchView v=(SearchView)search.getActionView();
        v.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
              List<Clubs> n=new ArrayList<>();
              n=LookForClubs(n,s.toLowerCase());

                Clubadapter.SearchClubs(n);
                return true;
            }
        });
        return true;
    }

    public List<Clubs> LookForClubs(List<Clubs> n,String textinput){
        for(int i=0;i<imagename.size();i++){
            if(imagename.get(i).getName().toLowerCase().trim().contains(textinput)){
                n.add(imagename.get(i));
            }
        }
        return n;
    }
}
