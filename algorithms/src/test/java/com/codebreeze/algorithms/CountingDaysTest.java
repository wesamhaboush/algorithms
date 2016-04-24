package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountingDaysTest
{
    @Test
    public void calculate() throws Exception
    {
        final CountingDays.YMD startDate = CountingDays.YMD.of(1901, 1, 1);
        final CountingDays.YMD endDate = CountingDays.YMD.of(2000, 12, 31);
        assertThat(CountingDays.calculate(
                    CountingDays.YMD.of(2015, 10, 1),
                    CountingDays.YMD.of(2015, 12, 1))
                  )
                .isEqualTo(1);

        assertThat(CountingDays.calculate(
                startDate,
                endDate)
                  )
                .isEqualTo(171);
    }

}
