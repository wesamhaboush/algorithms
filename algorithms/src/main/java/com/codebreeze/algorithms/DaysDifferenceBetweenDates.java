package com.codebreeze.algorithms;

import java.time.LocalDate;

/*
. Write procedures for the following date problems:
- given two dates, compute the number of days between them;
- given a date, return its day of the week;
- given a month and year, produce a calendar for the month as an array of characters.

The first version of your programs may assume that the year is in the 1900's;
the second version should be as general as possible.
 */
public class DaysDifferenceBetweenDates {
    // index starting from 1 (days in January),
    // 0 index shouldn't be accessed
    private static final int[] DAY_IN_MONTH = {-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int DAYS_IN_COMMON_YEAR = 365;
    private static final int DAYS_IN_LEAP_YEAR = 366;

    // Algorithm for determining leap year and common year
    // If (year is not divisible by 4) then (it is a common year)
    // else if (year is not divisible by 100) then (it is a leap year)
    // else if (year is not divisible by 400) then (it is a common year)
    // else (it is a leap year)
    private static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else {
            return year % 400 == 0;
        }
    }

    private static int dayInMonth(int month, int year) {
        if (month != 2) {
            return DAY_IN_MONTH[month];
        } else {
            return isLeapYear(year) ? DAY_IN_MONTH[month] + 1 : DAY_IN_MONTH[month];
        }
    }

    private static int wholeYearDifferenceInDays(LocalDate date1, LocalDate date2) {
        int wholeYearDifferenceInDays = 0;
        for (int year = date1.getYear() + 1; year < date2.getYear(); year++) {
            wholeYearDifferenceInDays += isLeapYear(year) ? DAYS_IN_LEAP_YEAR : DAYS_IN_COMMON_YEAR;
        }
        return wholeYearDifferenceInDays;
    }

    private static int daysLeftInYear(LocalDate date) {
        // first calculate days until the end of date.month
        // calculate days from date.day to end of date1.year
        // then count days in full month left in date.year
        int daysLeftInYear = dayInMonth(date.getMonthValue(), date.getYear()) - date.getDayOfMonth(); // daysLeftInMonth
        for (int month = date.getMonthValue() + 1; month <= 12; month++) {
            daysLeftInYear += dayInMonth(month, date.getYear());
        }
        return daysLeftInYear;
    }

    private static int daysPassedInYear(LocalDate date) {  // next calculate number days from first
        // day of date.year
        // to date.day
        int daysBeforeMonth = 0;
        for (int month = 1; month < date.getMonthValue(); month++) {
            daysBeforeMonth += dayInMonth(month, date.getYear());
        }

        return daysBeforeMonth + date.getDayOfMonth();
    }

    // problem 1:
    // given two dates, compute the number of days between them

    // computes number of days between date1 exclusive and date2
    public static int distanceBetweenDays(LocalDate date1, LocalDate date2) {
        if (date1.isAfter(date2)) {  // make sure date1 is an earlier day than date2
            return distanceBetweenDays(date2, date1);
        }

        // checks if two days are in different years
        if (date1.getYear() != date2.getYear()) {
            int daysAfterDate1Day = daysLeftInYear(date1);
            int daysBeforeDate2Day = daysPassedInYear(date2);

            return daysAfterDate1Day + daysBeforeDate2Day + wholeYearDifferenceInDays(date1, date2);
        } else if (date1.getMonthValue() != date2.getMonthValue()) {  // if date1 and date2 are within the same year
            // but not the same month
            // calculates days in whole months between them
            int daysBetweenDate1AndDate2Month = 0;
            for (int month = date1.getMonthValue() + 1; month < date2.getMonthValue(); ++month) {
                daysBetweenDate1AndDate2Month += dayInMonth(month, date1.getYear());
            }

            // add days left in the earlier date's month and later date's day
            return dayInMonth(date1.getMonthValue(), date1.getYear()) - date1.getDayOfMonth() + date2.getDayOfMonth() +
                daysBetweenDate1AndDate2Month;
        } else {  // same year same month
            return date2.getDayOfMonth() - date1.getDayOfMonth();
        }
    }

    // problem 2
    // given a date, return its day of the week

    /*
        Zeller’s Rule
        F=k+ [(13*m-1)/5] +D+ [D/4] +[C/4]-2*C where

        k is  the day of the month.
        m is the month number.
        D is the last two digits of the year.
        C is the first two digits of the year.

        Note:
        According to Zeller’s rule the month is counted as follows:
        March is 1, April is 2….. January is 11 and February is 12.
        So the year starts from March and ends with February.
        So if the given date has month as January or February subtract 1 from the year. For example:
        For 1st January 1998 subtract 1 from 1998 i.e. 1998-1=1997 and use 1997 for calculating D.
        Discard all the decimal values and then find the final value of F.

        After getting the value of F, divide it by 7.The value of F can be either positive or negative.
        If it is negative, let us suppose F = -15.
        When we divide by 7 we have to find the greatest multiple of 7 less than -15,
        so the remainder will be positive (or zero).
        -21 is the greatest multiple of 7 less than -15, so the remainder is 6 since -21 + 6 = -15.

        Alternatively, we can say that -7 goes into -15 twice, making -14 and leaving a remainder of -1.
        If we add 7 since the remainder is negative i.e. -1 + 7 we again get 6 as remainder.
        After getting the remainder we can find the day of the week for the given date.
         Following are the values for the corresponding remainders:

        Sun Mon Tue Wed Thurs Fri Sat
        0   1   2   3   4     5   6

*/
    public static int dayOfTheWeek(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        if(month == 1 || month == 2) {
            month = month + 12;
            year--;
        } else {
            month = month - 2;
        }
        int k = date.getDayOfMonth();
        int m = month;
        int D = year % 100;
        int c = year / 100;
        return (k + (13 * m - 1) / 5 + D + D / 4 + c / 4 - 2 * c) % 7;
    }

    // problem 3:
    // given a month and a year, produce a calendar for the month as an array of numbers
    public static int[] calendar(int month, int year) {

        int daysInMonth = dayInMonth(month, year);
        int[] output = new int[daysInMonth];

        for (int d = 1; d <= daysInMonth; ++d) {
            output[d - 1] = d;
        }

        return output;
    }

}
