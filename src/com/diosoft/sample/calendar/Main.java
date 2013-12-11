package com.diosoft.sample.calendar;

import com.diosoft.sample.calendar.datastore.CalendarDataStore;
import com.diosoft.sample.calendar.datastore.CalendarDataStoreImpl;
import com.diosoft.sample.calendar.service.CalendarService;
import com.diosoft.sample.calendar.service.CalendarServiceImpl;

import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        CalendarDataStore dataStore = new CalendarDataStoreImpl();

        CalendarService service = new CalendarServiceImpl(dataStore);

        for (String name : args)
            service.addEvent(name, name, null, null, null);

        for (String name : args)
            logger.info("Created event in data store: " + service.getEvent(name));

    }
}
