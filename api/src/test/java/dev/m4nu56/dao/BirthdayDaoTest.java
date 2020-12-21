package dev.m4nu56.dao;

import dev.m4nu56.model.tables.daos.ReminderDao;
import dev.m4nu56.model.tables.pojos.Reminder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BirthdayDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(BirthdayDaoTest.class);

    @Autowired
    private BirthdayDao birthdayDao;

    private ReminderDao reminderDao;

    @BeforeEach
    public void setUp() {
        reminderDao = birthdayDao.getReminderDao();
        reminderDao.deleteById(reminderDao.findAll().stream().map(Reminder::getId).collect(Collectors.toList()));
    }

    @Test
    void createTest() {
        // GIVEN
        reminderDao.insert(new Reminder(null, LocalDate.of(2020, Month.DECEMBER, 6), "m4nu56@gmail.com"));
        // WHEN
        List<Reminder> reminders = reminderDao.findAll();
        // THEN
        assertEquals(1, reminders.size());
        assertEquals(LocalDate.of(2020, Month.DECEMBER, 6), reminders.get(0).getBirthdayDate());
    }

    @Test
    void findAll() {
        // GIVEN
        reminderDao.insert(new Reminder(null, LocalDate.of(2020, Month.DECEMBER, 6), "m4nu56@gmail.com"));
        reminderDao.insert(new Reminder(null, LocalDate.of(2020, Month.DECEMBER, 7), "m4nu56@gmail.com"));
        reminderDao.insert(new Reminder(null, LocalDate.of(2020, Month.DECEMBER, 8), "m4nu56@gmail.com"));
        // WHEN
        List<Reminder> reminders = reminderDao.findAll();
        // THEN
        reminders.forEach(reminder -> logger.info(reminder.toString()));
        assertEquals(3, reminders.size());
    }

    @Test
    void updateTest() {
        // GIVEN
        Reminder reminder = birthdayDao.insertAndFetch(new Reminder(null, LocalDate.of(2020, Month.DECEMBER, 6), "m4nu56@gmail.com"));
        // WHEN
        reminderDao.update(reminder.setBirthdayDate(LocalDate.of(2020, Month.DECEMBER, 1)));
        // THEN
        Reminder byId = reminderDao.findById(reminder.getId());
        assertEquals(LocalDate.of(2020, Month.DECEMBER, 1), byId.getBirthdayDate());
    }
}
