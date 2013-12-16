package com.diosoft.sample.calendar;

import com.diosoft.sample.calendar.datastore.CalendarDataStore;
import com.diosoft.sample.calendar.datastore.CalendarDataStoreImpl;
import com.diosoft.sample.calendar.service.CalendarService;
import com.diosoft.sample.calendar.service.CalendarServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CalendarService service = (CalendarService) context.getBean("calendarService");

        for (String name : args)
            service.addEvent(name, name, null, null, null);

        for (String name : args)
            logger.info("Created event in data store: " + service.getEvent(name));

    }
}
