package com.example.calebk4.awty;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private PendingIntent pendingIntent;
    private boolean stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);*/
        EditText phonenumber = (EditText) findViewById(R.id.phonenumber);
        EditText time = (EditText) findViewById(R.id.time);
        EditText message = (EditText) findViewById(R.id.message);


        phonenumber.setHint("Enter a phone number!");
        time.setHint("repeat time? (seconds)");
        message.setHint("The message you would like to send");
        Button start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stop){ //checks if it has already been started
                    stop();
                } else if(goodToStart()){ //checks if fields are all there
                    EditText phonenumber = (EditText) findViewById(R.id.phonenumber);
                    Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                    alarmIntent.putExtra("phone", phonenumber.getText().toString());
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

                    annoy();
                }
            }
        });
    }

    private boolean goodToStart(){
        EditText phonenumber = (EditText) findViewById(R.id.phonenumber);
        EditText time = (EditText) findViewById(R.id.time);
        EditText message = (EditText) findViewById(R.id.message);
        return !phonenumber.getText().toString().matches("") && !time.getText().toString().matches("")
                && !message.getText().toString().matches("");

    }

    public void annoy(){
        EditText phonenumber = (EditText) findViewById(R.id.phonenumber);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        EditText time = (EditText) findViewById(R.id.time);
        int interval = Integer.parseInt(time.getText().toString());
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                1000*interval, pendingIntent);
        Toast.makeText(this, phonenumber.getText().toString() + ": are we there yet?", Toast.LENGTH_SHORT).show();
        Button start = (Button) findViewById(R.id.start);
        start.setText("Stop");
        stop = true;
    }

    public void stop() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "AWTY stopped", Toast.LENGTH_SHORT).show();
        Button start = (Button) findViewById(R.id.start);
        start.setText("start");
        stop = false;
    }

}
