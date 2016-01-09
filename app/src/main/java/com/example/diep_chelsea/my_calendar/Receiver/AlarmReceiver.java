package com.example.diep_chelsea.my_calendar.Receiver;

/**
 * Created by Diep_Chelsea on 09/01/2016.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Diep_Chelsea on 07/01/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar now = Calendar.getInstance();
        Toast.makeText(context,"Ringing",Toast.LENGTH_LONG).show();
    }
}