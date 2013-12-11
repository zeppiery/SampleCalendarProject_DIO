package com.diosoft.sample.calendar.datastore;

import com.diosoft.sample.calendar.common.Event;

import java.util.HashMap;
import java.util.Map;

public class CalendarDataStoreImpl implements CalendarDataStore {

    private final Map<String, Event> store = new HashMap<>();

    @Override
    public void publish(Event expectedEvent) {
        store.put(expectedEvent.getName(), expectedEvent);
    }

    @Override
    public Event remove(String inputName) {
        Event event = store.get(inputName);
        store.remove(inputName);
        return event;
    }

    @Override
    public Event getEvent(String name) {
        return store.get(name);
    }
}
