package dev.m4nu56.model.tables.daos;

import dev.m4nu56.model.tables.pojos.Reminder;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReminderDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(ReminderDaoTest.class);

    @Autowired
    private DefaultConfiguration configuration;

    private ReminderDao reminderDao;

    @BeforeEach
    public void setUp() {
        reminderDao = new ReminderDao(configuration);
    }

    @Test
    void findAll() {
        List<Reminder> reminders = reminderDao.findAll();
        reminders.forEach(reminder -> logger.info(reminder.toString()));
    }
}
