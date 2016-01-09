package com.example.diep_chelsea.my_calendar.source_code;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.diep_chelsea.my_calendar.R;
import com.example.diep_chelsea.my_calendar.Receiver.AlarmReceiver;

import java.util.Calendar;
import java.util.Date;


public class Add_work_Activity extends ActionBarActivity {

    public PendingIntent pendingIntent;
    TimePicker timePicker;
    int minute_tp,hour_tp,minue_cld,hour_cld,day_dp,month_dp,day_cld,month_cld,year_cld,year_dp;
    Button start;
    Button stop;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        //Calendar to get Current time
        Calendar currentTime = Calendar.getInstance();
        minue_cld=currentTime.get(Calendar.MINUTE);
        hour_cld=currentTime.get(Calendar.HOUR_OF_DAY);
        day_cld =currentTime.get(Calendar.DAY_OF_MONTH);
        month_cld=currentTime.get(Calendar.MONTH);
        year_cld=currentTime.get(Calendar.YEAR);
        //DatePicker
        day_dp = datePicker.getDayOfMonth();
        month_dp = datePicker.getMonth();
        datePicker.init(currentTime.get(Calendar.YEAR), month_cld, day_cld, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day_dp = dayOfMonth;
                month_dp = monthOfYear;
                year_dp = year;
            }
        });
        // time pikcer
        timePicker.clearFocus();
        timePicker.setCurrentHour(hour_cld);
        timePicker.setCurrentMinute(minue_cld);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                minute_tp = minute;
                hour_tp = hourOfDay;
            }
        });

        //main
        Intent launchIntent = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,launchIntent,0);
        final AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long interval = getTimeToCountdown(hour_cld,minue_cld,hour_tp,minute_tp);
                Toast.makeText(Add_work_Activity.this, "Current time is " + hour_cld + "/" + minue_cld + " , will ring after " + interval / 1000 + " seconds", Toast.LENGTH_SHORT).show();

//                manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + interval, 1000000, pendingIntent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(pendingIntent);
            }
        });
    }
    public long getDay()
    {
        Date cr_date = new Date(year_cld,month_cld,day_cld);
        Date ft_date = new Date(year_dp,month_dp,day_dp);
        long diffDay = Math.abs(ft_date.getTime()-cr_date.getTime());
        return diffDay;

    }
    public long getTimeToCountdown(int hour_cld,int minute_cld,int hour_tp,int minute_tp) {
        long days = getDay();
        long x = (hour_tp - hour_cld) * 3600 + (minute_tp - minute_cld) * 60 + days*86400;
        x = 1000 * x;
        return x;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
