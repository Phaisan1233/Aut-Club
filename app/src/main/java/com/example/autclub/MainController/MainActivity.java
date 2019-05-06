package com.example.autclub.MainController;


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
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autclub.ClubController.ClubListPageActivity;
import com.example.autclub.Event;
import com.example.autclub.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    static ArrayList<Event> event = new ArrayList<>();
    static ArrayList<Event> MSAevent = new ArrayList<>();
    static long epoch;
    static ActionBar actionBar = null;

    private TextView calenderTextView;
    private Button clubButton, reportButton;
    private ViewFlipper viewFlipper;
    private CompactCalendarView compactCalendarView;
    private ImageButton homeButton;

    Event e;
    ArrayList<Event> Calendarevent = new ArrayList<>();
    List<String> startdate = new ArrayList<>();
    List<String> epochtime = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compactCalendarView = findViewById(R.id.compactcalendar_view);
        calenderTextView = findViewById(R.id.main_editText);
        calenderTextView.setText(simpleDateFormat.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        clubButton = findViewById(R.id.main_clubButton);
        reportButton = (Button) findViewById(R.id.buttonreport);
        homeButton = (ImageButton) findViewById(R.id.homeButton);

        mQueue = Volley.newRequestQueue(this);

        generalEvent();
        MSAjsonParse();
        SendEvents();
        viewFlip();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("It works");
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventHandleReportButton();
            }

        });


    }

    private void viewFlip() {
        int images[] = {R.drawable.horizon, R.drawable.expression, R.drawable.horizon1, R.drawable.horizon2};
        viewFlipper = findViewById(R.id.v_flip);
        for (int i = 0; i < images.length; i++)
            flipperImages(images[i]);
    }

    private void eventHandleReportButton() {
        Intent intent = new Intent(MainActivity.this, Report.class);
        startActivity(intent);
    }

    private void generalEvent() {
        String url = "https://graph.facebook.com/v3.3/me/events?access_token=EAAGLQONEKZC8BAPQ8SFrDS2bUZAediZC0yfd3M98oqKAlEYdm6iHldZBo13pNgrhpzfHab6rHYjBjYxduBSxvSO4fZBvJnYUiokjDcoDLXhBnxpCRzal6l9qQjCAnJHc2p2fwmrxKbLhqt64lVK1rpwdgy3uZBy2aVdR4JfYJxegZDZD";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            eventHandleGeneralResponse(response);
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

    private void eventHandleGeneralResponse(String response) throws JSONException, ParseException {
        JSONObject jsonObject = new JSONObject(response);
        String feed = jsonObject.getString("data");

        JSONArray data = new JSONArray(feed);
        for (int i = 0; i < data.length(); i++) {
            JSONObject desc = data.getJSONObject(i);
            String description = desc.getString("description");
            String endTime = desc.getString("end_time");
            String name = desc.getString("name");
            String startTime = desc.getString("start_time");
            e = new Event(name, description, startTime, endTime);

            Calendarevent.add(e);

            String u = "";
            if (event.size() != data.length()) {
                event.add(e);
            }
            addEvents(name, u, startTime, endTime);
            startdate.add(startTime.substring(0, 10));
        }
    }


    private void MSAjsonParse() {
        String url = "https://graph.facebook.com/v3.3/me/events?access_token=EAAE6FvjiUB0BALY254ZADiVYD1pPifIHkWyjSUyjuc1ytf8NJPjQRZB4CwC7I3JHjYd5jd84UOjHu2n30AcrC30buGR22gKSR3TS7ZCfilEfnFSwnWswLgBpHCXqCK13maO0s3zUsqSsSGvnzyN9WaOXwZCyoPh6CZAuv73ZAP3gZDZD";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            eventHandleMSAJsonResponse(response);
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

    private void eventHandleMSAJsonResponse(String response) throws JSONException, ParseException {
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

    }

    private void addEvents(String one, String two, String the, String fo) throws ParseException {
        e.setName(one);
        e.setDescription(two);
        e.setStartime(the);
        e.setEndtime(fo);
        formatTime(e.getStartime());
    }

    private void SendEvents() {
        clubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ClubListPageActivity.class);
                startActivity(in);
            }
        });
    }

    private void formatTime(String y) {

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
                        if (startdate.contains(dateclicked(dateClicked.toString()))) {
                            for (int i = 0; i < startdate.size(); i++) {
                                if (startdate.get(i).equalsIgnoreCase(dateclicked(dateClicked.toString()))) {
                                    Toast.makeText(getBaseContext(), " " + Calendarevent.get(i), Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            toastMessage("There are no events");
                        }

                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    compactCalendarView.shouldScrollMonth(true);
                    calenderTextView.setText(simpleDateFormat.format(compactCalendarView.getFirstDayOfCurrentMonth()));
                }
            });
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

    }

    private String dateclicked(String date) throws ParseException {

        SimpleDateFormat old = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date ir = old.parse(date);
        SimpleDateFormat New = new SimpleDateFormat("yyyy-MM-dd");
        String nice = New.format(ir);
        epochtime.add(nice);
        return nice;
    }


    private void flipperImages(int image) {
        ImageView img = new ImageView(this);
        img.setBackgroundResource(image);

        viewFlipper.addView(img);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.settings);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                toastMessage("Item1clicked");
                return true;
            case R.id.item2:
                toastMessage("item clicked");
                return true;
            default:
                return false;
        }
    }

    private void toastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }


}





