package dev.m4nu56.api;

import dev.m4nu56.business.DayCounter;
import dev.m4nu56.dao.BirthdayDao;
import dev.m4nu56.model.tables.daos.ReminderDao;
import dev.m4nu56.model.tables.pojos.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("unused")
@RestController
public class DayCounterApi {
    private static final Logger logger = LoggerFactory.getLogger(DayCounterApi.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final DayCounter dayCounter;

    private final BirthdayDao birthdayDao;

    public DayCounterApi(DayCounter dayCounter, BirthdayDao birthdayDao) {
        this.dayCounter = dayCounter;
        this.birthdayDao = birthdayDao;
    }

    @GetMapping("/count-days")
    public long countDaysBetween(
            @RequestParam(value = "from", defaultValue = "2020-12-06") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        logger.info(String.valueOf(counter.incrementAndGet()));
        logger.info(String.format(template, from.format(DateTimeFormatter.ISO_LOCAL_DATE)));
        return dayCounter.countDaysBetween(from, Optional.ofNullable(to).orElse(LocalDate.now()));
    }

    @GetMapping("/reminders")
    public List<Reminder> getBirthdayReminders() {
        logger.info(String.valueOf(counter.incrementAndGet()));
        return birthdayDao.getReminderDao().findAll();
    }
}
