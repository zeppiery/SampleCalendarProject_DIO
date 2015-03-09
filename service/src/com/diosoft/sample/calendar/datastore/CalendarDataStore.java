package com.diosoft.sample.calendar.datastore;

import com.diosoft.sample.calendar.common.Event;
import com.diosoft.sample.calendar.common.Person;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public interface CalendarDataStore {
    
    void publish(Event event);
    //void addEvent(Event event);

    Event remove(String eventName);

    Event getEvent(String name);

    Event search(String eventName);

    Map<String, Event> searchByPerson(Person person);

    Boolean checkIfPersonIsFree(Person pNastya, GregorianCalendar inputStartDate);

    Map<String,Event> foundEventsOnDay(GregorianCalendar foundAllEventsOnDay);
}
