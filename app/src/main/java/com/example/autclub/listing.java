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

    private List<String> imagestag=new ArrayList<>();
    private List<Clubs> imagename=new ArrayList<>();
    public static ArrayList<String> description=new ArrayList<>();
    private RecyclerView.LayoutManager ly;
    private ClubsListAdapter Clubadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clublist);
        imagestag.add("horizon");
        imagestag.add("expression");
        imagestag.add("msa");
        imagestag.add("stem");
        imagename.add(new Clubs("Horizon",R.drawable.horizon));
        imagename.add(new Clubs("Expression",R.drawable.expression));
        imagename.add(new Clubs("MSA",R.drawable.msa));
        imagename.add(new Clubs("StemWomen",R.drawable.stemwomen));
        description.add("The Muslim Students Association(MSA) at Auckland University of Technology (AUT) is dedicated to helping our fellow brothers and sisters.");
        description.add("Horizon. ADP is a club from AUT who focus on creating a sense of community wihtin the AUT student body. The club provides social events, casual dance classes and opportunities for students who want to further develop themselves both in dance and in their own lives. We believe every day should be Pink Shirt Day and love our craft!");
        description.add("Expression is a fun way to express yourself through dance! All styles are welcome. Join in to have fun, learn a few moves and loosen up from your hectic uni life!");
        description.add("This group is to support and encourage women in STEM and help them develop the skills they need to do effective networking and succeed in their field of study.");


        RecyclerView r=findViewById(R.id.recycler);
        ly=new GridLayoutManager(listing.this,2);
        r.setHasFixedSize(true);
        r.setLayoutManager(ly);
        Clubadapter=new ClubsListAdapter(listing.this,imagename,imagestag);
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
