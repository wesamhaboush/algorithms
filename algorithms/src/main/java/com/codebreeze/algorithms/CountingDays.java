package com.codebreeze.algorithms;

/*
Counting Sundays
Problem 19
You are given the following information, but you may prefer to do some research for yourself.

1 Jan 1900 was a Monday.
Thirty days has September,
April, June and November.
All the rest have thirty-one,
Saving February alone,
Which has twenty-eight, rain or shine.
And on leap years, twenty-nine.
A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class CountingDays
{
    /*
    start two counters of dates, and keep moving forward with them.
    1 for days (week to week), and 1 for months (from 1st to 1st)
    everytime the values are the same number of days, then we hav an incident of sunday on a first!
     */
    public static int calculate(final YMD startDate, final YMD endDate)
    {
        int countOfSundaysOnFirstOfMonth = 0;
        YMD nextFirstOfMonth = startDate;
        while(nextFirstOfMonth.isBefore(endDate))
        {
            if(dayofweek(nextFirstOfMonth) == 0) // i.e. sunday
            {
                countOfSundaysOnFirstOfMonth++;
            }
            nextFirstOfMonth = nextFirstOfMonth(nextFirstOfMonth);
        }
        return countOfSundaysOnFirstOfMonth;
    }

    private static YMD nextFirstOfMonth(YMD date)
    {
        int month = date.m == 12
                    ? 1
                    : date.m + 1;
        int year = date.m == 12
                   ? date.y + 1
                   : date.y;
        return YMD.of(year, month, 1);
    }

    private static int[] T = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
    /*
        https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
        Posted by Tomohiko Sakamoto on the comp.lang.c Usenet newsgroup in 1993,
        it is accurate for any Gregorian date.
        The function does not always preserve y, and returns 0 = Sunday, 1 = Monday, etc.
     */
    private static int dayofweek(YMD ymd)	/* 1 <= m <= 12,  y > 1752 (in the U.K.) */
    {
        int y = ymd.y;
        int m = ymd.m;
        int d = ymd.d;
        y -= m < 3 ? 1 : 0;
        return (y + y/4 - y/100 + y/400 + T[m - 1] + d) % 7;
    }

    public static class YMD
    {
        int y;
        int m;
        int d;

        public static YMD of(int y, int m, int d)
        {
            final YMD ymd = new YMD();
            ymd.y = y;
            ymd.d = d;
            ymd.m = m;
            return ymd;
        }

        public boolean isBefore(YMD other)
        {
            if( y < other.y) return true;
            if( y == other.y && m < other.m) return true;
            if( y == other.y && m == other.m && d < other.d) return true;
            return false;
        }

        public boolean isSame(YMD other)
        {
            return y == other.y && m == other.m && d == other.d;
        }

        public boolean isAfter(YMD other)
        {
            return !isBefore(other) && !isSame(other);
        }
    }
}
