package com.example.diep_chelsea.my_calendar.source_code;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diep_chelsea.my_calendar.R;
import com.example.diep_chelsea.my_calendar.model.Converter;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class MainActivity extends Activity {
    AlertDialog dialog;
    TextView txt_solarDay,txt_solarMonth,txt_solarYear,txt_dayOfWeek,txt_lunar_day,txt_lunar_month,txt_lunar_year;
    Calendar current_calendar;
    LinearLayout root_layout;
    Converter converter1;
    int array[]={R.drawable.img_001,R.drawable.img_002,R.drawable.img_003,R.drawable.img_004,R.drawable.img_005,R.drawable.img_006,
            R.drawable.img_013,R.drawable.img_014,R.drawable.img_015, R.drawable.img_007,R.drawable.img_008,R.drawable.img_009,R.drawable.img_010,R.drawable.img_011,R.drawable.img_012,R.drawable.img_012,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //binding component
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_solarDay = (TextView) findViewById(R.id.ngay);
        txt_solarMonth = (TextView) findViewById(R.id.thang);
        txt_dayOfWeek = (TextView) findViewById(R.id.thu);
        txt_lunar_day = (TextView) findViewById(R.id.lunar_day);
        txt_lunar_month=(TextView) findViewById(R.id.lunar_month);
        txt_lunar_year=(TextView) findViewById(R.id.lunar_year);
        root_layout=(LinearLayout) findViewById(R.id.root_layout);
        int bg_image = setLayoutBG(array);
        current_calendar = Calendar.getInstance();
        int day_of_week = current_calendar.get(Calendar.DAY_OF_WEEK);
        converter1=new Converter();
//begin
        root_layout.setBackgroundResource(bg_image);
        txt_solarDay.setText(current_calendar.get(Calendar.DAY_OF_MONTH) + "");
        txt_solarMonth.setText(getString(R.string.thang) + " " + (current_calendar.get(Calendar.MONTH) + 1) + "/" + current_calendar.get(Calendar.YEAR));
        txt_dayOfWeek.setText(getString(R.string.thu) + " " + day_of_week);
        int tmp_day,tmp_month,tmp_year;
        tmp_day = current_calendar.get(Calendar.DAY_OF_MONTH);
        tmp_month = current_calendar.get(Calendar.MONTH)+1;
        tmp_year = current_calendar.get(Calendar.YEAR);
        txt_lunar_day.setText(""+converter1.getLunarDay(tmp_day, tmp_month, tmp_year, 7.0));
        txt_lunar_month.setText(""+converter1.getLunarMonth(tmp_day, tmp_month, tmp_year, 7.0));
        txt_lunar_year.setText("" + converter1.getLunarYear(tmp_day, tmp_month, tmp_year, 7.0));
//        txt_lunar_day.setText(""+current_day.getLunarDay(current_calendar.get(Calendar.DAY_OF_MONTH), current_calendar.get(Calendar.MONTH), current_calendar.get(Calendar.YEAR), 7.0));
//        txt_lunar_month.setText("" + current_day.getLunarMonth(current_calendar.get(Calendar.DAY_OF_MONTH), current_calendar.get(Calendar.MONTH), current_calendar.get(Calendar.YEAR), 7.0));
//        txt_lunar_year.setText("" + current_day.getLunarYear(current_calendar.get(Calendar.DAY_OF_MONTH), current_calendar.get(Calendar.MONTH), current_calendar.get(Calendar.YEAR), 7.0));
    }
    public void find_solarDate(int dd,int mm,int yy) {
        txt_solarDay.setText(dd);
        int solar_day=0;
        int solar_month=0;
        int solar_year=0;
        Converter converter= new Converter();

        solar_day = converter.getSolarDay(dd,mm,yy);
        solar_month = converter.getSolarMonth(dd, mm, yy);
        solar_year = converter.getSolarYear(dd, mm, yy);
        txt_solarMonth.setText(getString(R.string.thang) + " " + solar_month + "/" + solar_year);
        txt_solarDay.setText(solar_day);
        txt_lunar_day.setText(dd);
        txt_lunar_month.setText(mm);
    }

    public int setLayoutBG(int array[])
    {
        int bg = new Random().nextInt(array.length);
        return array[bg];
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
        if (id == R.id.goto_calendar) {
            Intent i = new Intent(MainActivity.this,CalendarActivity.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.goto_solarday)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            final View[] promptView = {layoutInflater.inflate(R.layout.find_date, null)};
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(promptView[0]);
            builder.setCancelable(true);
            final EditText edt_day,edt_month,edt_year;
            final DatePicker datePicker2 ;
            datePicker2 = (DatePicker) promptView[0].findViewById(R.id.datePicker2);
//            edt_day=(EditText) promptView[0].findViewById(R.id.ipd);
//            edt_month=(EditText) promptView[0].findViewById(R.id.ipm);
//            edt_year=(EditText) promptView[0].findViewById(R.id.ipy);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    int day   =   datePicker2.getDayOfMonth();
                    int month =   datePicker2.getMonth()+1;
                    int year  =   datePicker2.getYear();
                    Converter converter = new Converter();
                    txt_lunar_day.setText(converter.getLunarDay(day, month, year, 7.0)+"");
                    txt_lunar_month.setText(converter.getLunarMonth(day, month, year, 7.0) +"");
                    txt_lunar_year.setText(converter.getLunarYear(day, month, year, 7.0)+"");
                    txt_solarDay.setText(day+"");
                    txt_solarMonth.setText(getString(R.string.thang) + " " + month + "/" + year);
                    dialogInterface.cancel();
                    Calendar temp = Calendar.getInstance();
                    temp.set(year,month-1,day);
                    txt_dayOfWeek.setText(getString(R.string.thu)+" "+temp.get(Calendar.DAY_OF_WEEK));

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(id==R.id.find_lunarday)
        {

            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            final View[] promptView = {layoutInflater.inflate(R.layout.find_date, null)};
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(promptView[0]);
            builder.setCancelable(true);
            final DatePicker datePicker2 ;
            datePicker2 = (DatePicker) promptView[0].findViewById(R.id.datePicker2);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    int day   =   datePicker2.getDayOfMonth();
                    int month =   datePicker2.getMonth()+1;
                    int year  =   datePicker2.getYear();
                    Converter converter = new Converter();
                    txt_lunar_year.setText(year+"");
                    txt_lunar_month.setText(month+"");
                    txt_lunar_day.setText(day+"");
                    txt_solarDay.setText(converter.getSolarDay(day,month,year)+"");
                    txt_solarMonth.setText(getString(R.string.thang)+" " + converter.getSolarMonth(day, month, year)+"/"+converter.getSolarYear(day,month,year));
                    Calendar temp = Calendar.getInstance();
                    temp.set(converter.getSolarYear(day,month,year), converter.getSolarMonth(day,month,year)-1, converter.getSolarDay(day,month,year));
                    txt_dayOfWeek.setText(getString(R.string.thu)+" "+temp.get(Calendar.DAY_OF_WEEK));
                    dialogInterface.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
