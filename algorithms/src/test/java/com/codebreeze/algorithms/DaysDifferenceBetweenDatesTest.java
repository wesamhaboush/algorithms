package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DaysDifferenceBetweenDatesTest {

    @Test
    void distanceBetweenDays() {
        assertThat(
            DaysDifferenceBetweenDates.distanceBetweenDays(
                LocalDate.parse("2021-10-29"),
                LocalDate.parse("2021-11-01")
            )
        )
            .isEqualTo(3);
        assertThat(
            DaysDifferenceBetweenDates.distanceBetweenDays(
                LocalDate.parse("2020-10-29"),
                LocalDate.parse("2021-11-01")
            )
        )
            .isEqualTo(368);
    }

    @Test
    void dayOfTheWeek() {
        assertThat(DaysDifferenceBetweenDates.dayOfTheWeek(LocalDate.of(2021, 11, 1)))
            .isEqualTo(1); // Monday
        assertThat(DaysDifferenceBetweenDates.dayOfTheWeek(LocalDate.of(2021, 10, 29)))
            .isEqualTo(5); // Monday
    }

    @Test
    void calendar() {
        assertThat(DaysDifferenceBetweenDates.calendar(2, 2021))
            .isEqualTo(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28
            });
        assertThat(DaysDifferenceBetweenDates.calendar(2, 2020))
            .isEqualTo(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29
            });
        assertThat(DaysDifferenceBetweenDates.calendar(3, 2020))
            .isEqualTo(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
            });
        assertThat(DaysDifferenceBetweenDates.calendar(4, 2020))
            .isEqualTo(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30
            });
    }
}
