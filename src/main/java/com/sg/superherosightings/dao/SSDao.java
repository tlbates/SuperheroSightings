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
import java.util.List;

/**
 *
 * @author tylerbates
 */
public interface SSDao {
    
    //PERSON========================================
    public Person getPersonByID(int id);
    public Person getPersonByName(String name);
    public List<Person> getAllPeople();
    public void addPerson(Person person);
    public void updatePerson(Person person);
    public void deletePerson(int id);
    //==============================================
    
    
    //SUPERPOWER=====================================
    public Superpower getSuperpowerByID(int id);
    public Superpower getSuperpowerByDescription(String description);
    public List<Superpower> getAllSuperpowers();
    public void addSuperpower(Superpower superpower);
    public void updateSuperpower(Superpower superpower);
    public void deleteSuperpower(int id);
    //==============================================
    
    
    //ORGANIZATION===================================
    public Organization getOrganizationByID(int id);
    public Organization getOrganizationByName(String name);
    public List<Organization> getAllOrganizations();
    public void addOrganization(Organization organization);
    public void updateOrganization(Organization organization);
    public void deleteOrganization(int id);
    //==============================================
    
    
    //LOCATION======================================
    public Location getLocationByID(int id);
    public Location getLocationByName(String name);
    public List<Location> getAllLocations();
    public void addLocationOfSighting(Location location);
    public void updateLocationOfSighting(Location location);
    public void addLocationOfOrganization(Location location);
    public void updateLocationOfOrganization(Location location);
    public void deleteLocation(int id);
    //=============================================
    
    //SIGHTING=====================================
    public Sighting getSightingByID(int id);
    public List<Sighting> getSightingByDate(LocalDate date);
    public List<Sighting> getAllSightings();
    public void addSighting(Sighting sighting);
    public void updateSighting(Sighting sighting);
    public void deleteSighting(int id);
    //=============================================
    
    //HELPER=======================================
    public List<Organization> getOrganizationsForPerson(String name);
    public List<Sighting> getTenRecentSightings();
    //=============================================
}
