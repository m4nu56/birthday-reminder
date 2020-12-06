package dev.m4nu56.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DayCounterTest {

    private DayCounter dayCounter;

    @BeforeEach
    public void setUp() {
        dayCounter = new DayCounter();
    }

    @Test
    void countDaysBetweenTest() {
        // GIVEN
        LocalDate from = LocalDate.of(2020, Month.DECEMBER, 6);
        LocalDate to = LocalDate.of(2020, Month.DECEMBER, 7);
        // WHEN
        long days = dayCounter.countDaysBetween(from, to);
        // THEN
        assertEquals(1, days);
    }

    @Test
    void countDaysBetween_withInputNull_shouldThrowException() {
        // GIVEN
        LocalDate date = LocalDate.of(2020, Month.DECEMBER, 7);
        // WHEN
        assertThrows(NullPointerException.class, () -> dayCounter.countDaysBetween(null, date));
        assertThrows(NullPointerException.class, () -> dayCounter.countDaysBetween(date, null));
    }

    @Test
    void getDatePlusDaysTest() {
        // GIVEN
        LocalDate from = LocalDate.of(2020, Month.DECEMBER, 6);
        // WHEN
        LocalDate to = dayCounter.getDatePlusDays(from, 1);
        // THEN
        assertEquals(LocalDate.of(2020, Month.DECEMBER, 7), to);
    }
}
