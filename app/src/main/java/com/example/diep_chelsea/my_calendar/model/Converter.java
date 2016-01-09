package com.example.diep_chelsea.my_calendar.model;

import java.util.ArrayList;

/**
 * Created by Diep_Chelsea on 20/11/2015.
 */
public class Converter {
    double pi=3.141592653589793;
    public int getLunarMonth11(int yy,double timeZone)
    {
        int  k, off, nm = 0, sunLong;
        off = jdFromDate(31, 12, yy) - 2415021;
        k = (int)(off / 29.530588853);
        nm = getNewMoonDay(k, timeZone);
        sunLong = getSunLongitude(nm, timeZone); // sun longitude at local midnight
        if (sunLong >= 9) {
            nm = getNewMoonDay(k-1, timeZone);
        }
        return nm;
    }
    public int getSolarMonth(int dd,int mm,int yy)
    {
        int solar_month=0;
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList = this.getSolarDate(dd, mm, yy, 0, 7.0);
        solar_month=arrayList.get(1);
        return solar_month;
    }
    public int getSolarDay(int dd,int mm,int yy)
    {
        int solar_day=0;
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList = this.getSolarDate(dd,mm,yy,0,7.0);
        solar_day=arrayList.get(0);
        return solar_day;
    }
    public int getSolarYear(int dd,int mm,int yy)
    {
        int solar_month=0;
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList = this.getSolarDate(dd,mm,yy,0,7.0);
        solar_month=arrayList.get(2);
        return solar_month;
    }
    public int getLunarYear(int dd,int mm,int yy,double timeZone)
    {
        double leapMonthDiff;
        int k, dayNumber, monthStart, a11, b11, lunarDay, lunarMonth, lunarYear, diff,lunarLeap;
        dayNumber = jdFromDate(dd, mm, yy);
        k = (int)((dayNumber - 2415021.076998695) / 29.530588853);
        monthStart = getNewMoonDay(k+1, timeZone);
        if (monthStart > dayNumber) {
            monthStart = getNewMoonDay(k, timeZone);
        }
        a11 = getLunarMonth11(yy, timeZone);
        b11 = a11;
        if (a11 >= monthStart) {
            lunarYear = yy;
            a11 = getLunarMonth11(yy-1, timeZone);
        } else {
            lunarYear = yy+1;
            b11 = getLunarMonth11(yy+1, timeZone);
        }
        lunarDay = dayNumber-monthStart+1;
        diff = (int)((monthStart - a11)/29);
        lunarLeap = 0;
        lunarMonth = diff+11;
        if (b11 - a11 > 365) {
            leapMonthDiff = getLeapMonthOffset(a11, timeZone);
            if (diff >= leapMonthDiff) {
                lunarMonth = diff + 10;
                if (diff == leapMonthDiff) {
                    lunarLeap = 1;
                }
            }
        }
        if (lunarMonth > 12) {
            lunarMonth = lunarMonth - 12;
        }
        if (lunarMonth >= 11 && diff < 4) {
            lunarYear -= 1;
        }
        return lunarYear;
    }

    public int getLunarDay(int dd,int mm,int yy,double timeZone)
    {
        double leapMonthDiff;
        int k, dayNumber, monthStart, a11, b11, lunarDay, lunarMonth, lunarYear, diff,lunarLeap;
        dayNumber = jdFromDate(dd, mm, yy);
        k = (int)((dayNumber - 2415021.076998695) / 29.530588853);
        monthStart = getNewMoonDay(k+1, timeZone);
        if (monthStart > dayNumber) {
            monthStart = getNewMoonDay(k, timeZone);
        }
        a11 = getLunarMonth11(yy, timeZone);
        b11 = a11;
        if (a11 >= monthStart) {
            lunarYear = yy;
            a11 = getLunarMonth11(yy-1, timeZone);
        } else {
            lunarYear = yy+1;
            b11 = getLunarMonth11(yy+1, timeZone);
        }
        lunarDay = dayNumber-monthStart+1;
        diff = (int)((monthStart - a11)/29);
        lunarLeap = 0;
        lunarMonth = diff+11;
        if (b11 - a11 > 365) {
            leapMonthDiff = getLeapMonthOffset(a11, timeZone);
            if (diff >= leapMonthDiff) {
                lunarMonth = diff + 10;
                if (diff == leapMonthDiff) {
                    lunarLeap = 1;
                }
            }
        }
        if (lunarMonth > 12) {
            lunarMonth = lunarMonth - 12;
        }
        if (lunarMonth >= 11 && diff < 4) {
            lunarYear -= 1;
        }
        return lunarDay;
    }
    public int getLunarMonth(int dd,int mm,int yy,double timeZone)
    {
        double leapMonthDiff;
        int k, dayNumber, monthStart, a11, b11, lunarDay, lunarMonth, lunarYear, diff,lunarLeap;
        dayNumber = jdFromDate(dd, mm, yy);
        k = (int)((dayNumber - 2415021.076998695) / 29.530588853);
        monthStart = getNewMoonDay(k+1, timeZone);
        if (monthStart > dayNumber) {
            monthStart = getNewMoonDay(k, timeZone);
        }
        a11 = getLunarMonth11(yy, timeZone);
        b11 = a11;
        if (a11 >= monthStart) {
            lunarYear = yy;
            a11 = getLunarMonth11(yy-1, timeZone);
        } else {
            lunarYear = yy+1;
            b11 = getLunarMonth11(yy+1, timeZone);
        }
        lunarDay = dayNumber-monthStart+1;
        diff = (int)((monthStart - a11)/29);
        lunarLeap = 0;
        lunarMonth = diff+11;
        if (b11 - a11 > 365) {
            leapMonthDiff = getLeapMonthOffset(a11, timeZone);
            if (diff >= leapMonthDiff) {
                lunarMonth = diff + 10;
                if (diff == leapMonthDiff) {
                    lunarLeap = 1;
                }
            }
        }
        if (lunarMonth > 12) {
            lunarMonth = lunarMonth - 12;
        }
        if (lunarMonth >= 11 && diff < 4) {
            lunarYear -= 1;
        }
        return lunarMonth;
    }
    public int jdFromDate(int dd,int mm,int yy)
    {
        int a,d,m,y;
        int jd;
        a = (int) ((14 - mm) / 12);
        y = yy+4800-a;
        m = mm+12*a-3;
        jd = dd + (int)((153*m+2)/5) + 365*y + (int)(y/4) - (int)(y/100) + (int)(y/400) - 32045;
        if (jd < 2299161) {
            jd = dd + (int)((153*m+2)/5) + 365*y + (int)(y/4) - 32083;
        }
        return jd;
    }
    public int getNewMoonDay(int k,double timeZone)
    {
        double T, T2, T3, dr, Jd1, M, Mpr, F, C1, deltat;
        double JdNew = 0;
        T = k/1236.85; // Time in Julian centuries from 1900 January 0.5
        T2 = T * T;
        T3 = T2 * T;
        dr = pi/180;
        Jd1 = 2415020.75933 + 29.53058868*k + 0.0001178*T2 - 0.000000155*T3;
        Jd1 = Jd1 + 0.00033*Math.sin((166.56 + 132.87*T - 0.009173*T2)*dr); // Mean new moon
        M = 359.2242 + 29.10535608*k - 0.0000333*T2 - 0.00000347*T3; // Sun's mean anomaly
        Mpr = 306.0253 + 385.81691806*k + 0.0107306*T2 + 0.00001236*T3; // Moon's mean anomaly
        F = 21.2964 + 390.67050646*k - 0.0016528*T2 - 0.00000239*T3; // Moon's argument of latitude
        C1=(0.1734 - 0.000393*T)*Math.sin(M*dr) + 0.0021*Math.sin(2*dr*M);
        C1 = C1 - 0.4068*Math.sin(Mpr*dr) + 0.0161*Math.sin(dr*2*Mpr);
        C1 = C1 - 0.0004*Math.sin(dr*3*Mpr);
        C1 = C1 + 0.0104*Math.sin(dr*2*F) - 0.0051*Math.sin(dr*(M+Mpr));
        C1 = C1 - 0.0074*Math.sin(dr*(M-Mpr)) + 0.0004*Math.sin(dr*(2*F+M));
        C1 = C1 - 0.0004*Math.sin(dr*(2*F-M)) - 0.0006*Math.sin(dr*(2*F+Mpr));
        C1 = C1 + 0.0010*Math.sin(dr*(2*F-Mpr)) + 0.0005*Math.sin(dr*(2*Mpr+M));
        if (T < -11) {
            deltat= 0.001 + 0.000839*T + 0.0002261*T2 - 0.00000845*T3 - 0.000000081*T*T3;
        } else {
            deltat= -0.000278 + 0.000265*T + 0.000262*T2;
        };
        JdNew = Jd1 + C1 - deltat;
        return (int)(JdNew + 0.5 + timeZone/24);
    }

    private int getSunLongitude(int jdn, double timeZone) {
        double T, T2, dr, M, L0, DL, L;
        T = (int) ((jdn - 2451545.5 - timeZone/24) / 36525); // Time in Julian centuries from 2000-01-01 12:00:00 GMT
        T2 = T*T;
        dr = pi/180; // degree to radian
        M = 357.52910 + 35999.05030*T - 0.0001559*T2 - 0.00000048*T*T2; // mean anomaly, degree
        L0 = 280.46645 + 36000.76983*T + 0.0003032*T2; // mean longitude, degree
        DL = (1.914600 - 0.004817*T - 0.000014*T2)*Math.sin(dr*M);
        DL = DL + (0.019993 - 0.000101*T)*Math.sin(dr*2*M) + 0.000290*Math.sin(dr*3*M);
        L = L0 + DL; // true longitude, degree
        L = L*dr;
        L = L - pi*2*((int)(L/(pi*2))); // Normalize to (0, 2*PI)
        return (int) (L / pi * 6)  ;
    }
    private double getLeapMonthOffset(int a11, double timeZone) {
        int k, last, arc, i;
        k = (int) ((a11 - 2415021.076998695) / 29.530588853 + 0.5);
        last = 0;
        i = 1; // We start with the month following lunar month 11
        arc = getSunLongitude(getNewMoonDay(k+i, timeZone), timeZone);
        do {
            last = arc;
            i++;
            arc = getSunLongitude(getNewMoonDay(k+i, timeZone), timeZone);
        }
        while (arc != last && i < 14);
        return i-1;
    }
    public ArrayList<Integer> getSolarDate(int lunarDay, int lunarMonth, int lunarYear, int lunarLeap,double timeZone)
    {
        int k, a11, b11, off, leapOff, leapMonth, monthStart;
        if (lunarMonth < 11) {
            a11 = getLunarMonth11(lunarYear-1, timeZone);
            b11 = getLunarMonth11(lunarYear, timeZone);
        } else {
            a11 = getLunarMonth11(lunarYear, timeZone);
            b11 = getLunarMonth11(lunarYear+1, timeZone);
        }
        off = lunarMonth - 11;
        if (off < 0) {
            off += 12;
        }
        if (b11 - a11 > 365) {
            leapOff = (int) getLeapMonthOffset(a11, timeZone);
            leapMonth = leapOff - 2;
            if (leapMonth < 0) {
                leapMonth += 12;
            }
            if (lunarLeap != 0 && lunarMonth != leapMonth) {
                return null;
            } else if (lunarLeap != 0 || off >= leapOff) {
                off += 1;
            }
        }
        k = (int)(0.5 + (a11 - 2415021.076998695) / 29.530588853);
        monthStart = getNewMoonDay(k + off, timeZone);
        return jdToDate(monthStart + lunarDay - 1);
    }
    public ArrayList<Integer> jdToDate(int jd)
    {   ArrayList<Integer> array;
        array=new ArrayList<>();
        int  a, b, c, d, e, m, day, month, year;
        if (jd > 2299160) { // After 5/10/1582, Gregorian calendar
            a = jd + 32044;
            b = (int)((4*a+3)/146097);
            c = a - (int)((b*146097)/4);
        }
        else {
            b = 0;
            c = jd + 32082;
        }
        d = (int)((4*c+3)/1461);
        e = c - (int)((1461*d)/4);
        m = (int)((5*e+2)/153);
        day = e - (int)((153*m+2)/5) + 1;
        month = m + 3 - 12*(int)(m/10);

        year = b*100 + d - 4800 + (int)(m/10);
        array.add(day);
        array.add(month);
        array.add(year);

        return array;

    }
}