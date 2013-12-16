package com.diosoft.sample.calendar.datastore;

import com.diosoft.sample.calendar.common.Event;

public interface CalendarDataStore {

    void publish(Event expectedEvent);

    Event remove(String inputName);

    Event getEvent(String name);
}
