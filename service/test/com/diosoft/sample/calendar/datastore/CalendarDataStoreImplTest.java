package com.diosoft.sample.calendar.datastore;

import com.diosoft.sample.calendar.common.Event;
import com.diosoft.sample.calendar.common.Person;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;

public class CalendarDataStoreImplTest {

    @Test
    public void testAddEvent() throws Exception {
        // initialize variable inputs
        GregorianCalendar inputStartDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        GregorianCalendar inputEndDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        List<Person> attenders = Arrays.asList(new Person.Builder().firstName("aName").build());

        Event expectedEvent = new Event.Builder()
                .name(inputName)
                .description(inputDescription)
                .startTime(inputStartDate)
                .endTime(inputEndDate)
                .attenders(attenders)
                .build();

        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();

        // invoke method on class to test
        dataStore.publish(expectedEvent);

        // assert return value
        Event returnedValue = dataStore.getEvent(inputName);

        assertEquals(expectedEvent, returnedValue);

        // verify mock expectations
    }

    @Test
    public void testRemove() throws Exception {
        // initialize variable inputs
        GregorianCalendar inputStartDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        GregorianCalendar inputEndDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        List<Person> attenders = Arrays.asList(new Person.Builder().firstName("aName").build());

        Event expectedEvent = new Event.Builder()
                .name(inputName)
                .description(inputDescription)
                .startTime(inputStartDate)
                .endTime(inputEndDate)
                .attenders(attenders)
                .build();

        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();
        dataStore.publish(expectedEvent);

        // invoke method on class to test
        Event returnedValue = dataStore.remove(inputName);

        // assert return value
        Event returnedEmptyValue = dataStore.remove(inputName);

        assertEquals(expectedEvent, returnedValue);
        assertNull(returnedEmptyValue);

        // verify mock expectations
    }
    @Test
    public void testIsFreePerson() throws Exception {
        GregorianCalendar inputStartDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        GregorianCalendar inputStartDate_free = new GregorianCalendar(2010, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        GregorianCalendar inputEndDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "sampleEvent";
        String inputDescription = "sampleEventDescription";
        Person pNastya = new Person.Builder().firstName("pNastya").build();
        List<Person> attenders = Arrays.asList(pNastya);

        Event expectedEvent = new Event.Builder()
                .name(inputName)
                .description(inputDescription)
                .startTime(inputStartDate)
                .endTime(inputEndDate)
                .attenders(attenders)
                .build();

        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore =new CalendarDataStoreImpl();
        dataStore.publish(expectedEvent);

        // invoke method on class to test
        //Event returnedValue = dataStore.remove(inputName);

        // assert return value
        //Event returnedEmptyValue = dataStore.remove(inputName);

        //assertEquals(expectedEvent, returnedValue);
        Boolean isFreePerson = dataStore.checkIfPersonIsFree(pNastya, inputStartDate);//inputStartDate_free
        System.out.println("ifFreePerson:= " + isFreePerson);

        assertFalse(isFreePerson);

        // verify mock expectations
    }

    @Test
    public void testSearchByPerson() throws Exception {

        GregorianCalendar inputStartDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        GregorianCalendar inputEndDate = new GregorianCalendar(2008, Calendar.APRIL,Calendar.TUESDAY,10, 12);
        String inputName = "searchByPersonEvent";
        String inputDescription = "sampleEventDescription";
        Person pNastya = new Person.Builder().firstName("pNastya").build();
        List<Person> attenders = Arrays.asList(pNastya);

        Event expectedEvent = new Event.Builder()
                .name(inputName)
                .description(inputDescription)
                .startTime(inputStartDate)
                .endTime(inputEndDate)
                .attenders(attenders)
                .build();

        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore = new CalendarDataStoreImpl();
        dataStore.publish(expectedEvent);

        // invoke method on class to test
        Map<String, Event> returnedList = dataStore.searchByPerson(pNastya);
        // assert return value
        System.out.println("expectedEvent:= " + expectedEvent);
        System.out.println("returnedList:= " + returnedList.toString());

        Boolean ifExistEvent = returnedList.containsKey(expectedEvent.getName());
        System.out.println("ifExistEvent:= " + ifExistEvent);
        assertTrue(ifExistEvent);

        //assertEquals(expectedEvent, returnedList);

        // verify mock expectations

    }

    @Test
    public void testEventsForDay() throws Exception {

        GregorianCalendar firstInputStartDate = new GregorianCalendar(2015, Calendar.APRIL,15,10, 00);
        GregorianCalendar firstInputEndDate = new GregorianCalendar(2015, Calendar.APRIL,15,11, 00);
        GregorianCalendar secondInputStartDate = new GregorianCalendar(2015, Calendar.APRIL,15,15, 00);
        GregorianCalendar secondInputEndDate = new GregorianCalendar(2015, Calendar.APRIL,15,16, 00);
        GregorianCalendar foundAllEventsOnDay = new GregorianCalendar(2015, Calendar.APRIL,15);
        String firstInputName = "firstEventForDay";
        String secondInputName = "secondEventForDay";
        String inputDescription = "sampleEventDescription";
        Person person = new Person.Builder().firstName("person").build();
        List<Person> attenders = Arrays.asList(person);

        Event firstEvent = new Event.Builder()
                .name(firstInputName)
                .description(inputDescription)
                .startTime(firstInputStartDate)
                .endTime(firstInputEndDate)
                .attenders(attenders)
                .build();
        Event secondEvent = new Event.Builder()
                .name(secondInputName)
                .description(inputDescription)
                .startTime(secondInputStartDate)
                .endTime(secondInputEndDate)
                .attenders(attenders)
                .build();
        // initialize mocks

        // initialize class to test
        CalendarDataStore dataStore = new CalendarDataStoreImpl();
        dataStore.publish(firstEvent);
        dataStore.publish(secondEvent);

        // invoke method on class to test
        Map<String, Event> returnedList = dataStore.foundEventsOnDay(foundAllEventsOnDay);
        // assert return value
        Map<String, Event> expectedList = new HashMap<>();
        expectedList.put(firstEvent.getName(),firstEvent);
        expectedList.put(secondEvent.getName(),secondEvent);

        System.out.println("foundAllEventsOnDay:= " + foundAllEventsOnDay);
        System.out.println("expectedList:= " + expectedList.toString());
        System.out.println("returnedList:= " + returnedList.toString());

        //System.out.println("ifExistEvent:= " + ifExistEvent);
        //assertTrue(ifExistEvent);

        assertEquals(expectedList, returnedList);

        // verify mock expectations

    }
}
