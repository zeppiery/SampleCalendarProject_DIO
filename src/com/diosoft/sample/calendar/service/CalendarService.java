package com.diosoft.sample.calendar.service;

import com.diosoft.sample.calendar.common.Event;
import com.diosoft.sample.calendar.common.Person;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public interface CalendarService {

    void addEvent(String name, String description, GregorianCalendar startDate, GregorianCalendar endDate, List<Person> attenders);

    Event removeEvent(String name);

    Event addAttender(String name, Person... newPersons);

    Event getEvent(String name);
}
