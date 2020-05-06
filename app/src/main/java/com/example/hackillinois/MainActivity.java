package com.example.hackillinois;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static TextView eventtxtbox;
    private static TextView event2txtbox;
    private static TextView event3txtbox;

//    public static TextView[] friTexts;
//    public static TextView[] satTexts;
//    public static TextView[] sunTexts;
//    LinearLayout textLayout;
//
    Button fridayButton;
    Button saturdayButton;
    Button sundayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventtxtbox = findViewById(R.id.event);
        event2txtbox = findViewById(R.id.event2);
        event3txtbox = findViewById(R.id.event3);

        fridayButton = findViewById(R.id.friday);
        saturdayButton = findViewById(R.id.saturday);
        sundayButton = findViewById(R.id.sunday);

        //textLayout = (LinearLayout) findViewById(R.id.textlay);

        //create retrieveData object to parse json
        final retrieveData process = new retrieveData();
        process.execute();

//        friTexts = new TextView[process.getFriNum()];
//
//        for(int i=0;i<process.getFriNum();i++)
//        {
//            TextView text = new TextView(this);
//            text.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//            text.append(process.getDates()[i]);
//            friTexts[i] = text;
//            textLayout.addView(text);
//        }

        //handle displaying of information
        eventtxtbox.setVisibility(View.VISIBLE);
        event2txtbox.setVisibility(View.GONE);
        event3txtbox.setVisibility(View.GONE);

        fridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventtxtbox.setVisibility(View.VISIBLE);
                event2txtbox.setVisibility(View.GONE);
                event3txtbox.setVisibility(View.GONE);
            }
        });

        saturdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventtxtbox.setVisibility(View.GONE);
                event2txtbox.setVisibility(View.VISIBLE);
                event3txtbox.setVisibility(View.GONE);
            }
        });

        sundayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventtxtbox.setVisibility(View.GONE);
                event2txtbox.setVisibility(View.GONE);
                event3txtbox.setVisibility(View.VISIBLE);

            }
        });

    }

    //setters for adding text to textboxes
    public static void setEventtxtbox(String text) {
        eventtxtbox.append(text);
    }

    public static void setEvent2txtbox(String text) {
        event2txtbox.append(text);
    }

    public static void setEvent3txtbox(String text) {
        event3txtbox.append(text);
    }


}
