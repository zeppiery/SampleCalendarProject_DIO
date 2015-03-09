package com.diosoft.sample.calendar.datastore;

import com.diosoft.sample.calendar.common.Event;
import com.diosoft.sample.calendar.common.Person;
import org.mockito.internal.matchers.Equals;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.*;

public class CalendarDataStoreImpl implements CalendarDataStore {

    private final Map<String, Event> store = new HashMap<>();

    @Override
    public Event search(String eventName) {
        return store.get(eventName);
    }

    @Override
    public Map<String, Event> searchByPerson(Person person) {
        Iterator it = store.entrySet().iterator();
        Map<String, Event> eventWithPerson = new HashMap<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //it.remove(); // avoids a ConcurrentModificationException
            boolean contains =((Event)pair.getValue()).getAttenders().contains(person);
            System.out.println("Event name:= " + ((Event)pair.getValue()).getName());
            System.out.println("Event:= " + (Event)pair.getValue());
            if (contains) {
                eventWithPerson.put(((Event)pair.getValue()).getName(), (Event)pair.getValue());
            }
        }
        return (Map<String, Event>) eventWithPerson;
    }

    @Override
    public Boolean checkIfPersonIsFree(Person pNastya, GregorianCalendar inputStartDate) {
        Map<String, Event> eventWithPerson = new HashMap<>();
        eventWithPerson = (Map<String, Event>) searchByPerson(pNastya);
        Iterator it = eventWithPerson.entrySet().iterator();
        boolean isTimeExist=true;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //it.remove(); // avoids a ConcurrentModificationException
            if (((Event)pair.getValue()).getStartTime() == inputStartDate) {
                isTimeExist=false;
            }
        }
        return isTimeExist;
    }

    @Override
    public Map<String, Event> foundEventsOnDay(GregorianCalendar foundAllEventsOnDay) {
        Map<String, Event> eventsOnDay = new HashMap<>();
        Iterator it = store.entrySet().iterator();
        GregorianCalendar eventDate;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //it.remove(); // avoids a ConcurrentModificationException
            eventDate=((Event)pair.getValue()).getStartTime();
            if ((eventDate.get(Calendar.YEAR) == foundAllEventsOnDay.get(Calendar.YEAR))
                ||(eventDate.get(Calendar.MONTH) == foundAllEventsOnDay.get(Calendar.MONTH))
                    ||(eventDate.get(Calendar.DAY_OF_MONTH) == foundAllEventsOnDay.get(Calendar.DAY_OF_MONTH))) {
                eventsOnDay.put(((Event) pair.getValue()).getName(), (Event) pair.getValue());
            }
        }

        return (Map<String, Event>) eventsOnDay;
    }

    @Override
    public void publish(Event event) {
        store.put(event.getName(), event);
        persistEvent(event);
    }

    @Override
    public Event remove(String eventName) {
        Event event = store.get(eventName);
        store.remove(eventName);
        return event;
    }

    @Override
    public Event getEvent(String name) {
        return store.get(name);
    }

    private void persistEvent(Event expectedEvent){

        JAXBContext context = null;

        EventAdapter eventAdapter = new EventAdapter(expectedEvent);
        try {
            context = JAXBContext.newInstance(EventAdapter.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(eventAdapter, new File("./"+expectedEvent.getName() +". xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
