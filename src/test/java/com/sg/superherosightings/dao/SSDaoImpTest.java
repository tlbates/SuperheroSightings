/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Person;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Superpower;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tylerbates
 */
public class SSDaoImpTest {

    public SSDao dao;

    public SSDaoImpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("ssDao", SSDao.class);

        List<Sighting> sightings = dao.getAllSightings();
        sightings.forEach((current) -> {
            dao.deleteSighting(current.getId());
        });

        List<Organization> organizations = dao.getAllOrganizations();
        organizations.forEach((current) -> {
            dao.deleteOrganization(current.getId());
        });

        List<Location> locations = dao.getAllLocations();
        locations.forEach((current) -> {
            dao.deleteLocation(current.getId());
        });
        
        
        List<Person> people = dao.getAllPeople();
        people.forEach((current) -> {
            dao.deletePerson(current.getId());
        });
        
        List<Superpower> powers = dao.getAllSuperpowers();
        powers.forEach((current) -> {
            dao.deleteSuperpower(current.getId());
        });
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetSuperpower() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("Sonic Speed");

        dao.addSuperpower(superP);
        Superpower fromDaoById = dao.getSuperpowerByID(superP.getId());
        assertEquals(superP, fromDaoById);

        Superpower fromDaoByDescription = dao.getSuperpowerByDescription("Sonic Speed");
        assertEquals(superP, fromDaoByDescription);

    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("Sonic Speed");

        dao.addSuperpower(superP);

        Superpower newPower = new Superpower();
        newPower.setId(1);
        newPower.setDescription("Very Slow");

        dao.updateSuperpower(newPower);

        assertNotSame(superP, dao.getSuperpowerByID(1));

    }

    @Test
    public void testGetAllAndDeleteSuperpower() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("Sonic Speed");

        dao.addSuperpower(superP);
        assertEquals(1, dao.getAllSuperpowers().size());

        Superpower newPower = new Superpower();
        newPower.setId(2);
        newPower.setDescription("Very Slow");
        dao.addSuperpower(newPower);
        assertEquals(2, dao.getAllSuperpowers().size());

        dao.deleteSuperpower(newPower.getId());
        assertEquals(1, dao.getAllSuperpowers().size());

        dao.deleteSuperpower(superP.getId());
        assertEquals(0, dao.getAllSuperpowers().size());

    }

    @Test
    public void testAddGetPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);

        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        person.setSuperpowers(superpower);
        dao.addPerson(person);


        Person fromDaoByID = dao.getPersonByID(person.getId());
        assertEquals(person, fromDaoByID);

        Person fromDaoByName = dao.getPersonByName(person.getName());
        assertEquals(person, fromDaoByName);
    }

    @Test
    public void testUpdatePerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        person.setSuperpowers(superpower);

        dao.addPerson(person);

        Person person2 = new Person();
        person2.setId(1);
        person2.setName("Aquaman");
        person2.setDescription("Lives underwater.");
        person2.setHero(1);
        person2.setVillain(0);
        Superpower superP2 = new Superpower();
        superP2.setId(2);
        superP2.setDescription("Swimming");
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superP2);
        dao.addSuperpower(superP2);
        person2.setSuperpowers(superpowers);

        dao.updatePerson(person2);
        Person updated = dao.getPersonByID(1);

        assertNotSame(person, updated);
    }

    @Test
    public void testGetAllAndDeletePerson() {

        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        person.setSuperpowers(superpower);

        dao.addPerson(person);
        assertEquals(1, dao.getAllPeople().size());

        Person newHero = new Person();
        newHero.setId(2);
        newHero.setName("Aquaman");
        newHero.setDescription("Lives underwater.");
        newHero.setHero(1);
        newHero.setVillain(0);
        
        Superpower superP2 = new Superpower();
        superP2.setId(2);
        superP2.setDescription("Swimming");
        List<Superpower> superpower2 = new ArrayList<>();
        superpower2.add(superP2);
        dao.addSuperpower(superP2);
        newHero.setSuperpowers(superpower2);

        dao.addPerson(newHero);
        assertEquals(2, dao.getAllPeople().size());

        dao.deletePerson(newHero.getId());
        assertEquals(1, dao.getAllPeople().size());

        dao.deletePerson(person.getId());
        assertEquals(0, dao.getAllPeople().size());

    }

    @Test
    public void testAddGetLocationOfSighting() {
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("West End Diner");
        newLoc.setDescription("One of the most expensive diners in LA.");
        newLoc.setLatitude(40.78098);
        newLoc.setLongitude(-73.970226);

        dao.addLocationOfSighting(newLoc);

        Location fromDaoById = dao.getLocationByName("West End Diner");
        assertEquals(newLoc, fromDaoById);

    }

    @Test
    public void testUpdateLocationOfSighting() {
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("West End Diner");
        newLoc.setDescription("One of the most expensive diners in LA.");
        newLoc.setLatitude(40.78098);
        newLoc.setLongitude(-73.970226);

        Location updated = new Location();
        updated.setId(1);
        updated.setName("East End Diner");
        updated.setDescription("Super cheap. Food Bad.");
        updated.setLatitude(40.73338);
        updated.setLongitude(-73.945626);

        dao.updateLocationOfSighting(updated);
        assertNotSame(newLoc, dao.getLocationByID(1));
    }

    @Test
    public void testAddGetLocationOfOrganization() {
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("The Haunted Few");
        newLoc.setDescription("Only join if you like to haunt people.");
        newLoc.setStreet("66 Haunted Avenue");
        newLoc.setCity("Tupelo");
        newLoc.setState("MS");
        newLoc.setCountry("USA");
        newLoc.setZip(455466);

        dao.addLocationOfOrganization(newLoc);
        Location fromDao = dao.getLocationByID(1);
        assertEquals(newLoc, fromDao);
    }

    @Test
    public void testUpdateLocationOfOrganization() {
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("The Haunted Few");
        newLoc.setDescription("Only join if you like to haunt people.");
        newLoc.setStreet("66 Haunted Avenue");
        newLoc.setCity("Tupelo");
        newLoc.setState("MS");
        newLoc.setCountry("USA");
        newLoc.setZip(455466);

        dao.addLocationOfOrganization(newLoc);

        Location updated = new Location();
        updated.setId(1);
        updated.setName("The Haunted Many");
        updated.setDescription("So much haunting honestly.");
        updated.setStreet("66 Haunted Avenue");
        updated.setCity("Pontotoc");
        updated.setState("MS");
        updated.setCountry("USA");
        updated.setZip(455777);

        dao.updateLocationOfOrganization(updated);
        assertNotSame(newLoc, dao.getLocationByID(1));
    }

    @Test
    public void testGetAllandDeleteLocation() {
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("The Haunted Few");
        newLoc.setDescription("Only join if you like to haunt people.");
        newLoc.setStreet("66 Haunted Avenue");
        newLoc.setCity("Tupelo");
        newLoc.setState("Mississippi");
        newLoc.setCountry("USA");
        newLoc.setZip(455466);
        dao.addLocationOfOrganization(newLoc);
        assertEquals(1, dao.getAllLocations().size());

        Location new2 = new Location();
        new2.setId(2);
        new2.setName("The Haunted Many");
        new2.setDescription("So much haunting honestly.");
        new2.setStreet("66 Haunted Avenue");
        new2.setCity("Pontotoc");
        new2.setState("Mississippi");
        new2.setCountry("USA");
        new2.setZip(455777);
        dao.addLocationOfOrganization(new2);
        assertEquals(2, dao.getAllLocations().size());

        dao.deleteLocation(new2.getId());
        assertEquals(1, dao.getAllLocations().size());

        dao.deleteLocation(newLoc.getId());
        assertEquals(0, dao.getAllLocations().size());
    }

    @Test
    public void testAddGetOrganization() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        
        
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        person.setSuperpowers(superpower);
        dao.addPerson(person);
        
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("The Haunted Few");
        newLoc.setDescription("Only join if you like to haunt people.");
        newLoc.setStreet("66 Haunted Avenue");
        newLoc.setCity("Tupelo");
        newLoc.setState("Mississippi");
        newLoc.setCountry("USA");
        newLoc.setZip(455466);
        dao.addLocationOfOrganization(newLoc);

        Organization org = new Organization();
        org.setId(1);
        org.setName("Evil Inc");
        org.setEmail("fun@sinister.com");
        org.setPhone("5556667766");
        org.setLocation(newLoc);
        List<Person> people = new ArrayList<>();
        people.add(person);
        org.setPeople(people);
        dao.addOrganization(org);

        Organization newOrg = dao.getOrganizationByID(1);
        assertEquals(org, newOrg);

        Organization byName = dao.getOrganizationByName("Evil Inc");
        assertEquals(org, byName);
    }

    @Test
    public void testUpdateOrganization() {
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("The Haunted Few");
        newLoc.setDescription("Only join if you like to haunt people.");
        newLoc.setStreet("66 Haunted Avenue");
        newLoc.setCity("Tupelo");
        newLoc.setState("Mississippi");
        newLoc.setCountry("USA");
        newLoc.setZip(455466);
        dao.addLocationOfOrganization(newLoc);
        
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        person.setSuperpowers(superpower);
        dao.addPerson(person);

        Organization org = new Organization();
        org.setId(1);
        org.setName("Evil Inc");
        org.setEmail("fun@sinister.com");
        org.setPhone("5556667766");
        org.setLocation(newLoc);
        List<Person> people = new ArrayList<>();
        people.add(person);
        org.setPeople(people);
        dao.addOrganization(org);

        Organization org2 = new Organization();
        org2.setId(1);
        org2.setName("Evil LLC");
        org2.setEmail("notfun@sinister.com");
        org2.setPhone("5552227766");
        org2.setLocation(newLoc);
        org2.setPeople(people);
        dao.updateOrganization(org2);

        assertNotSame(org, dao.getOrganizationByID(1));
    }

    @Test
    public void testGetAllandDeleteOrganization() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        person.setSuperpowers(superpower);
        dao.addPerson(person);
        
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("The Haunted Few");
        newLoc.setDescription("Only join if you like to haunt people.");
        newLoc.setStreet("66 Haunted Avenue");
        newLoc.setCity("Tupelo");
        newLoc.setState("Mississippi");
        newLoc.setCountry("USA");
        newLoc.setZip(455466);
        dao.addLocationOfOrganization(newLoc);

        Organization org = new Organization();
        org.setId(1);
        org.setName("Evil Inc");
        org.setEmail("fun@sinister.com");
        org.setPhone("5556667766");
        org.setLocation(newLoc);
        List<Person> people = new ArrayList<>();
        people.add(person);
        org.setPeople(people);
        dao.addOrganization(org);
        assertEquals(1, dao.getAllOrganizations().size());

        Organization org2 = new Organization();
        org2.setId(2);
        org2.setName("Evil LLC");
        org2.setEmail("notfun@sinister.com");
        org2.setPhone("5552227766");
        org2.setLocation(newLoc);
        org2.setPeople(people);
        dao.addOrganization(org2);
        assertEquals(2, dao.getAllOrganizations().size());

        dao.deleteOrganization(org.getId());
        assertEquals(1, dao.getAllOrganizations().size());

        dao.deleteOrganization(org2.getId());
        assertEquals(0, dao.getAllOrganizations().size());
    }

    @Test
    public void testAddGetSighting() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        person.setSuperpowers(superpower);
        dao.addPerson(person);
        
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("West End Diner");
        newLoc.setDescription("One of the most expensive diners in LA.");
        newLoc.setLatitude(40.78098);
        newLoc.setLongitude(-73.970226);
        dao.addLocationOfSighting(newLoc);

        Sighting sight = new Sighting();
        sight.setId(1);
        sight.setDate(LocalDate.of(2018, Month.MARCH, 10));
        sight.setLocation(dao.getLocationByID(1));
        List<Person> people = new ArrayList<>();
        people.add(person);
        sight.setPeople(people);
        dao.addSighting(sight);

        Sighting sightById = dao.getSightingByID(1);
        assertEquals(sight, sightById);

        List<Sighting> sightByDate = dao.getSightingByDate(LocalDate.of(2018, Month.MARCH, 10));
        assertEquals(sight, sightByDate.get(0));

    }

    @Test
    public void testUpdateSighting() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        person.setSuperpowers(superpower);
        dao.addPerson(person);
        
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("West End Diner");
        newLoc.setDescription("One of the most expensive diners in LA.");
        newLoc.setLatitude(40.78098);
        newLoc.setLongitude(-73.970226);
        dao.addLocationOfSighting(newLoc);

        Sighting sight = new Sighting();
        sight.setId(1);
        sight.setDate(LocalDate.of(2018, Month.MARCH, 10));
        sight.setLocation(dao.getLocationByID(1));
        List<Person> people = new ArrayList<>();
        people.add(person);
        sight.setPeople(people);
        dao.addSighting(sight);

        Sighting sight2 = new Sighting();
        sight2.setId(1);
        sight2.setDate(LocalDate.of(2019, Month.MARCH, 6));
        sight2.setLocation(dao.getLocationByID(1));
        sight2.setPeople(people);
        dao.updateSighting(sight2);

        assertNotSame(sight, dao.getSightingByID(1));
    }

    @Test
    public void testGetAllandDeleteSighting() {
        Superpower superP = new Superpower();
        superP.setId(1);
        superP.setDescription("VERY RICH");
        List<Superpower> superpower = new ArrayList<>();
        superpower.add(superP);
        dao.addSuperpower(superP);
        
        Person person = new Person();
        person.setId(1);
        person.setName("Batman");
        person.setDescription("The Dark Knight");
        person.setHero(1);
        person.setVillain(0);
        person.setSuperpowers(superpower);
        dao.addPerson(person);
        
        Location newLoc = new Location();
        newLoc.setId(1);
        newLoc.setName("West End Diner");
        newLoc.setDescription("One of the most expensive diners in LA.");
        newLoc.setLatitude(40.78098);
        newLoc.setLongitude(-73.970226);
        dao.addLocationOfSighting(newLoc);

        Sighting sight = new Sighting();
        sight.setId(1);
        sight.setDate(LocalDate.of(2018, Month.MARCH, 10));
        sight.setLocation(dao.getLocationByID(1));
        List<Person> people = new ArrayList<>();
        people.add(person);
        sight.setPeople(people);
        dao.addSighting(sight);
        assertEquals(1, dao.getAllSightings().size());

        Sighting sight2 = new Sighting();
        sight2.setId(2);
        sight2.setDate(LocalDate.of(2019, Month.MARCH, 6));
        sight2.setLocation(dao.getLocationByID(1));
        sight2.setPeople(people);
        dao.addSighting(sight2);
        assertEquals(2, dao.getAllSightings().size());

        dao.deleteSighting(sight2.getId());
        assertEquals(1, dao.getAllSightings().size());

        dao.deleteSighting(sight.getId());
        assertEquals(0, dao.getAllSightings().size());
    }

}
