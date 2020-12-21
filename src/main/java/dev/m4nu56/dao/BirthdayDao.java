package dev.m4nu56.dao;

import dev.m4nu56.model.tables.daos.ReminderDao;
import dev.m4nu56.model.tables.pojos.Reminder;
import dev.m4nu56.model.tables.records.ReminderRecord;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.m4nu56.model.Tables.REMINDER;

@Repository
@Slf4j
public class BirthdayDao {

    @Getter
    private final ReminderDao reminderDao;

    private final DSLContext jooq;

    public BirthdayDao(@Qualifier("dsl") DSLContext jooq, DefaultConfiguration configuration) {
        this.reminderDao = new ReminderDao(configuration);
        this.jooq = jooq;
    }

    @Transactional
    public Reminder insertAndFetch(Reminder reminder) {
        ReminderRecord reminderRecord = jooq.insertInto(REMINDER)
                .set(createRecord(reminder))
                .returning()
                .fetchOne();
        return new Reminder(reminderRecord.getId(), reminderRecord.getBirthdayDate(), reminderRecord.getEmail());
    }

    private ReminderRecord createRecord(Reminder reminder) {
        ReminderRecord record = new ReminderRecord();
        record.setId(reminder.getId());
        record.setBirthdayDate(reminder.getBirthdayDate());
        record.setEmail(reminder.getEmail());
        return record;
    }

    public List<LocalDate> getBirthdayReminders() {
        Result<Record> result = jooq.select().from(REMINDER).fetch();
        for (Record r : result) {
            Long id = r.getValue(REMINDER.ID);
            LocalDate birthdayDate = r.getValue(REMINDER.BIRTHDAY_DATE);
            String email = r.getValue(REMINDER.EMAIL);

            log.info("ID: " + id + " birtdayDate: " + birthdayDate + " email: " + email);
        }
        return new ArrayList<>();
    }

    public void insertRecord(LocalDate date, String email) {
        jooq.insertInto(REMINDER)
                .set(REMINDER.BIRTHDAY_DATE, date)
                .set(REMINDER.EMAIL, email)
                .execute();
    }

}
