package dev.m4nu56.business;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class DayCounter {

    public long countDaysBetween(@NonNull LocalDate from, @NonNull LocalDate to) {
        return DAYS.between(from, to);
    }

    public LocalDate getDatePlusDays(@NonNull LocalDate from, long days) {
        return from.plusDays(days);
    }

}
