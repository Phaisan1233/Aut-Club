package com.example.autclub;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    TextView v, m;
    Button b;
    ViewFlipper v_flip;

    private RequestQueue mQueue;


    CompactCalendarView compactCalendarView;
    Event e;
    static ArrayList<Event> event = new ArrayList<>();
    static ArrayList<Event> MSAevent = new ArrayList<>();
    ArrayList<Event> Calendarevent = new ArrayList<>();
    List<String> startdate = new ArrayList<>();
    List<String> epochtime = new ArrayList<>();


    static long epoch;
    static ActionBar ac = null;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        compactCalendarView = findViewById(R.id.compactcalendar_view);
        v = findViewById(R.id.editText);
        // v.setText(simpleDateFormat.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        b = findViewById(R.id.Button);
        v.setText(simpleDateFormat.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        mQueue = Volley.newRequestQueue(this);

        jsonParse();
        MSAjsonParse();
        SendEvents();


        ac = getSupportActionBar();

        ac.setDisplayHomeAsUpEnabled(false);
        ac.setTitle(null);
        compactCalendarView.setUseThreeLetterAbbreviation(true);


        Button button = (Button) findViewById(R.id.buttonreport);
        ImageButton homebutton = (ImageButton) findViewById(R.id.homeButton);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "It works", Toast.LENGTH_LONG).show();
            }
        });
        //casting because it wants the widget instead of the view


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Report.class);
                startActivity(intent);

            }

        });


        int images[] = {R.drawable.horizon, R.drawable.expression, R.drawable.horizon1, R.drawable.horizon2};

        v_flip = findViewById(R.id.v_flip);

        for (int i = 0; i < images.length; i++)
            flipperImages(images[i]);


    }

    public void jsonParse() {

        String url = "https://graph.facebook.com/v3.3/me/events?access_token=EAAFYYh2QEZCQBAIfnskP0OGgpVbN0k94qudZAZCgRZA7Rd8FfuxnOgInp2YA0cvcDdBNeGWwfB1DJHhYNFwpE1IwcbCfu4A6C0ZARJ3sCYPU8yZBqQVx3X00gtxjnaZAF8JYZC5ulysOrrkfvx7mZAaCTzZAtXqXgmVWfIfa5yZBvgZCkttyLcwQDmppRIAmApXBfcjsAwZA8E5OlSQZDZD";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject ob = new JSONObject(response);
                            String feed = ob.getString("data");


                            JSONArray data = new JSONArray(feed);
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject desc = data.getJSONObject(i);
                                String n = desc.getString("description");
                                String timeend = desc.getString("end_time");
                                String name = desc.getString("name");
                                String starttime = desc.getString("start_time");
                                e = new Event(name, n, starttime, timeend);

                                Calendarevent.add(e);


                                String u = "";
                                if (event.size() != data.length()) {
                                    event.add(e);
                                }
                                addEvents(name, u, starttime, timeend);
                                startdate.add(starttime.substring(0, 10));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }

    public void MSAjsonParse() {
        String url = "https://graph.facebook.com/v3.3/me/events?access_token=EAAE6FvjiUB0BALY254ZADiVYD1pPifIHkWyjSUyjuc1ytf8NJPjQRZB4CwC7I3JHjYd5jd84UOjHu2n30AcrC30buGR22gKSR3TS7ZCfilEfnFSwnWswLgBpHCXqCK13maO0s3zUsqSsSGvnzyN9WaOXwZCyoPh6CZAuv73ZAP3gZDZD";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject ob = new JSONObject(response);
                            String feed = ob.getString("data");

                            JSONArray data = new JSONArray(feed);
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject desc = data.getJSONObject(i);
                                String n = desc.getString("description");
                                String timeend = desc.getString("end_time");
                                String name = desc.getString("name");
                                String starttime = desc.getString("start_time");
                                e = new Event(name, n, starttime, timeend);
                                if (MSAevent.size() != data.length()) {
                                    MSAevent.add(e);
                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }

    public void addEvents(String one, String two, String the, String fo) throws ParseException {
        e.setName(one);
        e.setDescription(two);
        e.setStartime(the);
        e.setEndtime(fo);


        formatTime(e.getStartime());


    }

    public void SendEvents() {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, listing.class);


                startActivity(in);


            }
        });
    }


    /**
     * This will get us time in epoch so that we can use it to add our events in the Calendar.
     *
     * @param y
     */
    public void formatTime(String y) {

        try {
            SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date r = form.parse(y);
            epoch = r.getTime();


            final com.github.sundeepk.compactcalendarview.domain.Event event1 = new com.github.sundeepk.compactcalendarview.domain.Event(Color.GREEN, epoch, Calendarevent.toString());
            compactCalendarView.addEvent(event1);


            compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

                @Override
                public void onDayClick(Date dateClicked) {
                    try {
                        if ((startdate.contains(dateclicked(dateClicked.toString())))) {
                            for (int i = 0; i < startdate.size(); i++) {
                                if (startdate.get(i).equalsIgnoreCase(dateclicked(dateClicked.toString()))) {
                                    Toast.makeText(getBaseContext(), " " + Calendarevent.get(i), Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "There are no events", Toast.LENGTH_LONG).show();
                        }

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }


                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    compactCalendarView.shouldScrollMonth(true);




                }
            });
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

    }

    public String dateclicked(String date) throws ParseException {

        SimpleDateFormat old = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date ir = old.parse(date);
        SimpleDateFormat New = new SimpleDateFormat("yyyy-MM-dd");
        String nice = New.format(ir);
        epochtime.add(nice);
        return nice;
    }


    public void flipperImages(int image) {
        ImageView img = new ImageView(this);
        img.setBackgroundResource(image);

        v_flip.addView(img);
        v_flip.setFlipInterval(4000);
        v_flip.setAutoStart(true);

        v_flip.setInAnimation(this, android.R.anim.slide_in_left);
        v_flip.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.settings);
        popup.show();

    }

    @Override

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item1clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item2:
                Toast.makeText(this, "item clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }
    }


}





