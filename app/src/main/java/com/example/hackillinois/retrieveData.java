package com.example.hackillinois;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Locale;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class retrieveData extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String[] events;
    private String[] dates;
    private String prevTime;
//    private int friNum;
//    private int satNum;
//    private int sunNum;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //set up a url connection
            URL url = new URL("https://api.hackillinois.org/event/");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            //reads the data
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while(line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jsnobject = new JSONObject(data);

            JSONArray jsonArray = jsnobject.getJSONArray("events");

            events = new String[jsonArray.length()];
            dates = new String[jsonArray.length()];

            //read data from json
            for (int i = 0; i < jsonArray.length(); i++) {
                //String id = jsonArray.getJSONObject(i).getString("id");
                String name = jsonArray.getJSONObject(i).getString("name");;
                String description = jsonArray.getJSONObject(i).getString("description");
                String startTime = jsonArray.getJSONObject(i).getString("startTime");
                //convert start time from epoch time
                long unix_seconds = Long.parseLong(startTime);
                Date date = new Date(unix_seconds * 1000);
                SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String java_date = jdf.format(date);
                String endTime = jsonArray.getJSONObject(i).getString("endTime");
                //convert end time from epoch time
                long unix_secondsEnd = Long.parseLong(endTime);
                Date dateEnd = new Date(unix_secondsEnd * 1000);
                SimpleDateFormat jdfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String java_dateEnd = jdf.format(dateEnd);
                String info = name + "\n" + description + "\n" + java_date + "\n" + java_dateEnd;
                events[i] = info;
                dates[i] = java_date;
//                if (java_date.substring(8, 10).equals("28")) {
//                    friNum++;
//                }
                System.out.println(events[i]);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("WRONG JSON");
            e.printStackTrace();
        }
        return null;
    }

//    public int getFriNum() {
//        return friNum;
//    }
//
//    public String[] getDates() {
//        return dates;
//    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        prevTime = "";

        //add data to textbox
        for (int i = 0; i < events.length; i++) {
            String currTime = dates[i].substring(11);
            if (dates[i].substring(8, 10).equals("28")) {
                //(MainActivity.friTexts)[x].append(events[i]);
                if (!currTime.equals(prevTime)) {
                    MainActivity.setEventtxtbox("\n" + currTime + "\n");
                }
                MainActivity.setEventtxtbox("\n" + events[i] + "\n");
            } else if (dates[i].substring(8, 10).equals("29")) {
                if (!currTime.equals(prevTime)) {
                    MainActivity.setEvent2txtbox("\n" + currTime + "\n");
                }
                MainActivity.setEvent2txtbox("\n" + events[i] + "\n");
            } else if (dates[i].substring(8, 10).equals("01")) {
                if (!currTime.equals(prevTime)) {
                    MainActivity.setEvent3txtbox("\n" + currTime + "\n");
                }
                MainActivity.setEvent3txtbox("\n" + events[i] + "\n");
            } else {
                MainActivity.setEventtxtbox("none");
                MainActivity.setEvent2txtbox("none");
                MainActivity.setEvent3txtbox("none");
            }
            prevTime = currTime;
            //MainActivity.eventtxtbox.setText(events[i]);
        }

    }
}

