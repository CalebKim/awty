package com.example.calebk4.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by iguest on 3/1/15.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive (Context context, Intent intent) {
        //show text
        String phone = intent.getStringExtra("phone");
        Toast.makeText(context, phone + ": Are We There Yet", Toast.LENGTH_SHORT).show();
    }

}
