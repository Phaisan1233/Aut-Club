package com.example.autclub.MainController;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autclub.MainController.AboutUs2;
import com.example.autclub.R;

import org.w3c.dom.Text;

public class AboutUs extends AppCompatActivity {

    private Button more_button;
    // private ImageView profile1;
    // private TextView description1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        more_button = findViewById(R.id.morebutton);

        more_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(AboutUs.this, "CLicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AboutUs.this, AboutUs2.class);
                startActivity(intent);
            }


        });
    }

    //@Override

//        public boolean onCreateOptionsMenu (Menu menu)
//        {
//            getMenuInflater().inflate(R.menu.settings, menu);
//            return true;
//        }
    //@Override
//        public boolean onOptionsItemSelected (MenuItem item)
//        {
//            TextView displayTextView = (TextView) findViewById(R.id.purpose);
//            switch (item.getItemId()) {
//                case R.id.morebutton:
//                    Intent intent = new Intent(this, AboutUs2.class);
//                    this.startActivity(intent);
//                    break;
////                case R.id.instruction:
////                    // another startActivity, this is for item with id "menu_item2"
////                    break;
//                default:
//                    return super.onOptionsItemSelected(item);
//            }
//
//            return true;
//        }

}
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about_us);
//
//       // mListLayout = (RelativeLayout) findViewById(R.id.firstlayout);
//        //profile1 = (ImageView) findViewById(R.id.grouppicture);
//       // description1 = (TextView) findViewById(R.id.purpose);
//        more_button = (Button) (findViewById(R.id.morebutton));
//
//           more_button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                Intent intent = new Intent(AboutUs.this,AboutUs2.class);
//                startActivity(intent);
//            }
////
////
////                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(view1,"imageTransition"));
////                startActivity(nextpage);
//
//        });



