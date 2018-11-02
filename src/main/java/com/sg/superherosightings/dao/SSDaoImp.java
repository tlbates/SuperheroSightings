package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Person;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tylerbates
 */
public class SSDaoImp implements SSDao {

    private JdbcTemplate jdbcTemplate;

    private static final class queries {

// _ __   ___ _ __ ___  ___  _ __  
//| '_ \ / _ \ '__/ __|/ _ \| '_ \ 
//| |_) |  __/ |  \__ \ (_) | | | |
//| .__/ \___|_|  |___/\___/|_| |_|
//|_|                     
        private static final String SELECT_PERSON_BY_ID
                = "select * from person where id = ?";
        private static final String SELECT_PERSON_BY_NAME
                = "select * from person where name = ?";
        private static final String SELECT_ALL_PEOPLE
                = "select * from person";
        private static final String DELETE_PERSON_BY_ID
                = "delete from person where id = ?";
        private static final String INSERT_PERSON_BY_ID
                = "insert into person "
                + "(id, name, description, hero, villain) "
                + "values (?,?,?,?,?)";
        private static final String INSERT_PERSON
                = "insert into person "
                + "(name, description, hero, villain) "
                + "values (?,?,?,?)";
        private static final String UPDATE_PERSON
                = "update person set "
                + "name = ?, description = ?, hero = ?, villain = ? "
                + "where id = ?";
// ___ _   _ _ __   ___ _ __ _ __   _____      _____ _ __ 
/// __| | | | '_ \ / _ \ '__| '_ \ / _ \ \ /\ / / _ \ '__|
//\__ \ |_| | |_) |  __/ |  | |_) | (_) \ V  V /  __/ |   
//|___/\__,_| .__/ \___|_|  | .__/ \___/ \_/\_/ \___|_|   
//          |_|             |_|                          
        private static final String SELECT_SUPERPOWER_BY_ID
                = "select * from superpower where id = ?";
        private static final String SELECT_SUPERPOWER_BY_DESCRIPTION
                = "select * from superpower where description = ?";
        private static final String SELECT_ALL_SUPERPOWERS
                = "select * from superpower";
        private static final String DELETE_SUPERPOWER_BY_ID
                = "delete from superpower where id = ?";
        private static final String INSERT_SUPERPOWER_BY_ID
                = "insert into superpower "
                + "(id, description)"
                + " values (?,?)";
        private static final String INSERT_SUPERPOWER
                = "insert into superpower "
                + "(description) "
                + "values (?)";
        private static final String UPDATE_SUPERPOWER
                = "update superpower set "
                + "description = ? "
                + "where id = ?";
//                             _          _   _             
//  ___  _ __ __ _  __ _ _ __ (_)______ _| |_(_) ___  _ __  
// / _ \| '__/ _` |/ _` | '_ \| |_  / _` | __| |/ _ \| '_ \ 
//| (_) | | | (_| | (_| | | | | |/ / (_| | |_| | (_) | | | |
// \___/|_|  \__, |\__,_|_| |_|_/___\__,_|\__|_|\___/|_| |_|
//           |___/                                          
        private static final String SELECT_ORGANIZATION_BY_ID
                = "select * from organization where id = ?";
        private static final String SELECT_ORGANIZATION_BY_NAME
                = "select * from organization where name = ?";
        private static final String SELECT_ALL_ORGANIZATIONS
                = "select * from organization";
        private static final String DELETE_ORGANIZATION_BY_ID
                = "delete from organization where id = ?";
        private static final String INSERT_ORGANIZATION_BY_ID
                = "insert into organization "
                + "(id, name, email, phone, locationid)"
                + " values (?,?,?,?,?)";
        private static final String INSERT_ORGANIZATION
                = "insert into organization "
                + "(name, email, phone, locationid) "
                + "values (?,?,?,?)";
        private static final String UPDATE_ORGANIZATION
                = "update organization set "
                + "name = ?, email = ?, phone = ?, locationid = ? "
                + "where id = ?";

// _                 _   _             
//| | ___   ___ __ _| |_(_) ___  _ __  
//| |/ _ \ / __/ _` | __| |/ _ \| '_ \ 
//| | (_) | (_| (_| | |_| | (_) | | | |
//|_|\___/ \___\__,_|\__|_|\___/|_| |_|
//                                    
        private static final String SELECT_LOCATION_BY_ID
                = "select * from location where id = ?";
        private static final String SELECT_LOCATION_BY_NAME
                = "select * from location where name = ?";
        private static final String SELECT_ALL_LOCATIONS
                = "select * from location";
        private static final String DELETE_LOCATION_BY_ID
                = "delete from location where id = ?";
        private static final String INSERT_LOCATION_OF_SIGHTING_BY_ID
                = "insert into location "
                + "(id, name, description, latitude, longitude)"
                + " values (?,?,?,?,?)";
        private static final String INSERT_LOCATION_OF_SIGHTING
                = "insert into location "
                + "(name, description, latitude, longitude) "
                + "values (?,?,?,?)";
        private static final String INSERT_LOCATION_OF_ORGANIZATION_BY_ID
                = "insert into location "
                + "(id, name, description, street, city, state, zip, country)"
                + " values (?,?,?,?,?,?,?,?)";
        private static final String INSERT_LOCATION_OF_ORGANIZATION
                = "insert into location "
                + "(name, description, street, city, state, zip, country) "
                + "values (?,?,?,?,?,?,?)";
        private static final String UPDATE_LOCATION_OF_SIGHTING
                = "update location set "
                + "name = ?, description = ?, latitude = ?, longitude = ? "
                + "where id = ?";
        private static final String UPDATE_LOCATION_OF_ORGANIZATION
                = "update location set "
                + "name = ?, description = ?, street = ?, city = ?, "
                + "state = ?, zip = ?, country = ? "
                + "where id = ?";

//     _       _     _   _             
// ___(_) __ _| |__ | |_(_)_ __   __ _ 
/// __| |/ _` | '_ \| __| | '_ \ / _` |
//\__ \ | (_| | | | | |_| | | | | (_| |
//|___/_|\__, |_| |_|\__|_|_| |_|\__, |
//       |___/                   |___/ 
        private static final String SELECT_SIGHTING_BY_ID
                = "select * from sighting where id = ?";
        private static final String SELECT_SIGHTINGS_BY_DATE
                = "select * from sighting where date = ?";
        private static final String SELECT_ALL_SIGHTINGS
                = "select * from sighting";
        private static final String DELETE_SIGHTING_BY_ID
                = "delete from sighting where id = ?";
        private static final String INSERT_SIGHTING_BY_ID
                = "insert into sighting "
                + "(id, date, locationid) "
                + "values (?,?,?)";
        private static final String INSERT_SIGHTING
                = "insert into sighting "
                + "(date, locationid) "
                + "values (?,?)";
        private static final String UPDATE_SIGHTING
                = "update sighting set "
                + "date = ?, locationid = ? "
                + "where id = ?";
// _          _                 
//| |__   ___| |_ __   ___ _ __ 
//| '_ \ / _ \ | '_ \ / _ \ '__|
//| | | |  __/ | |_) |  __/ |   
//|_| |_|\___|_| .__/ \___|_|   
//             |_|             
        private static final String SELECT_SUPERPOWERS_FOR_PERSON
                = "select s.* "
                + "from Superpower s "
                + "inner join PersonSuperpowers ps on s.id = ps.superpowerid "
                + "inner join Person p on ps.personid = p.id "
                + "where p.id = ?;";
        private static final String ASSOCIATE_POWERS_WITH_PEOPLE
                = "insert into PersonSuperpowers "
                + "(PersonID, SuperpowerID) "
                + "values (?,?)";
        private static final String DELETE_HERO_POWERS
                = "delete from PersonSuperpowers "
                + "where personId = ?";
        private static final String DELETE_POWER_FROM_HEROS
                = "delete from PersonSuperpowers "
                + "where superpowerId = ?";
        private static final String GET_LOCATION_FROM_SIGHTING
                = "select l.* "
                + "from Sighting s "
                + "inner join Location l on s.locationid = l.id "
                + "where s.id = ?";
        private static final String GET_LOCATION_FROM_ORGANIZATION
                = "select l.* "
                + "from organization o "
                + "inner join Location l on o.locationid = l.id "
                + "where o.id = ?";
        private static final String ASSOCIATE_PEOPLE_WITH_ORGANIZATION
                = "insert into OrganizationPeople "
                + "(PersonId, OrganizationId) "
                + "values (?,?)";
        private static final String ASSOCIATE_PEOPLE_WITH_SIGHTING
                = "insert into PersonSightings "
                + "(PersonId, SightingId) "
                + "values (?,?)";
        private static final String DELETE_PERSON_FROM_SIGHTING
                = "delete from PersonSightings "
                + "where sightingid = ?";
        private static final String DELETE_PERSON_FROM_ORGANIZATION
                = "delete from OrganizationPeople "
                + "where organizationid = ?";
        private static final String GET_PEOPLE_FOR_ORGANIZATION
                = "select p.* "
                + "from Organization o "
                + "inner join OrganizationPeople op on o.id = op.organizationid "
                + "inner join Person p on op.personid = p.id "
                + "where o.id = ?";
        private static final String GET_PEOPLE_FOR_SIGHTING
                = "select p.* "
                + "from Sighting s "
                + "inner join PersonSightings ps on s.id = ps.sightingid "
                + "inner join Person p on ps.personid = p.id "
                + "where s.id = ?";
        private static final String GET_ORGANIZATIONS_FOR_PERSON
                = "select o.* "
                + "from Organization o "
                + "inner join OrganizationPeople op on o.id = op.organizationid "
                + "inner join person p on op.personid = p.id "
                + "where p.name = ?";
        private static final String GET_TEN_RECENT_SIGHTINGS
                = "select * from sighting "
                + "order by sighting.date desc limit 0,10";
        //-------------------------------------------------------------
    }

// _ __   ___ _ __ ___  ___  _ __  
//| '_ \ / _ \ '__/ __|/ _ \| '_ \ 
//| |_) |  __/ |  \__ \ (_) | | | |
//| .__/ \___|_|  |___/\___/|_| |_|
//|_|                              
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person getPersonByID(int id) {
        try {
            Person person = jdbcTemplate.queryForObject(
                    queries.SELECT_PERSON_BY_ID,
                    new mappers.PersonMapper(),
                    id);
            person.setSuperpowers(getSuperheroPowers(person.getId()));
            return person;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract Person object using ID.");
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Person getPersonByName(String name) {
        try {
            Person person = jdbcTemplate.queryForObject(
                    queries.SELECT_PERSON_BY_NAME,
                    new mappers.PersonMapper(),
                    name);
            person.setSuperpowers(getSuperheroPowers(person.getId()));
            return person;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract Person object using Name.");
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Person> getAllPeople() {
        List<Person> people = jdbcTemplate.query(queries.SELECT_ALL_PEOPLE,
                new mappers.PersonMapper());
        people.forEach((current) -> {
            current.setSuperpowers(getSuperheroPowers(current.getId()));
        });
        return people;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPerson(Person person) {
        if (person.getId() != 0) {
            jdbcTemplate.update(queries.INSERT_PERSON_BY_ID,
                    person.getId(),
                    person.getName(),
                    person.getDescription(),
                    person.getHero(),
                    person.getVillain());
        } else {
            jdbcTemplate.update(queries.INSERT_PERSON,
                    person.getName(),
                    person.getDescription(),
                    person.getHero(),
                    person.getVillain());
            person.setId(getLastInsertId());
        }
        associatePowerWithPeople(person.getSuperpowers(), person.getId());
    }

    @Override
    public void updatePerson(Person person) {
        jdbcTemplate.update(queries.UPDATE_PERSON,
                person.getName(),
                person.getDescription(),
                person.getHero(),
                person.getVillain(),
                person.getId());

        deleteHeroPowers(person.getId());
        associatePowerWithPeople(person.getSuperpowers(), person.getId());
    }

    @Override
    public void deletePerson(int id) {
        deleteHeroPowers(id);
        jdbcTemplate.update(queries.DELETE_PERSON_BY_ID, id);
    }
// ___ _   _ _ __   ___ _ __ _ __   _____      _____ _ __ 
/// __| | | | '_ \ / _ \ '__| '_ \ / _ \ \ /\ / / _ \ '__|
//\__ \ |_| | |_) |  __/ |  | |_) | (_) \ V  V /  __/ |   
//|___/\__,_| .__/ \___|_|  | .__/ \___/ \_/\_/ \___|_|   
//          |_|             |_|                          

    @Override
    public Superpower getSuperpowerByID(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    queries.SELECT_SUPERPOWER_BY_ID,
                    new mappers.SuperpowerMapper(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract Superpower object using ID.");
            return null;
        }
    }

    @Override
    public Superpower getSuperpowerByDescription(String description) {
        try {
            return jdbcTemplate.queryForObject(
                    queries.SELECT_SUPERPOWER_BY_DESCRIPTION,
                    new mappers.SuperpowerMapper(),
                    description);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract Superpower object using description.");
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return jdbcTemplate.query(queries.SELECT_ALL_SUPERPOWERS,
                new mappers.SuperpowerMapper());
    }

    @Override
    public void addSuperpower(Superpower superpower) {
        if (superpower.getId() != 0) {
            jdbcTemplate.update(queries.INSERT_SUPERPOWER_BY_ID,
                    superpower.getId(),
                    superpower.getDescription());
        } else {
            jdbcTemplate.update(queries.INSERT_SUPERPOWER,
                    superpower.getDescription());
            superpower.setId(getLastInsertId());
        }
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(queries.UPDATE_SUPERPOWER,
                superpower.getDescription(),
                superpower.getId());
    }

    @Override
    public void deleteSuperpower(int id) {
        deletePowerFromPeople(id);
        jdbcTemplate.update(queries.DELETE_SUPERPOWER_BY_ID,
                id);
    }
//                             _          _   _             
//  ___  _ __ __ _  __ _ _ __ (_)______ _| |_(_) ___  _ __  
// / _ \| '__/ _` |/ _` | '_ \| |_  / _` | __| |/ _ \| '_ \ 
//| (_) | | | (_| | (_| | | | | |/ / (_| | |_| | (_) | | | |
// \___/|_|  \__, |\__,_|_| |_|_/___\__,_|\__|_|\___/|_| |_|
//           |___/                                          

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization getOrganizationByID(int id) {
        try {
            Organization org = jdbcTemplate.queryForObject(
                    queries.SELECT_ORGANIZATION_BY_ID,
                    new mappers.OrganizationMapper(),
                    id);
            org.setLocation(setLocationForOrg(org.getId()));
            org.setPeople(getPeopleFromOrganization(org));

            org.getPeople().forEach((current) -> {
                current.setSuperpowers(getSuperheroPowers(current.getId()));
            });

            return org;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract organization object using ID.");
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization getOrganizationByName(String name) {
        try {
            Organization org = jdbcTemplate.queryForObject(
                    queries.SELECT_ORGANIZATION_BY_NAME,
                    new mappers.OrganizationMapper(),
                    name);
            org.setLocation(setLocationForOrg(org.getId()));
            org.setPeople(getPeopleFromOrganization(org));

            org.getPeople().forEach((current) -> {
                current.setSuperpowers(getSuperheroPowers(current.getId()));
            });

            return org;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract Organization object using name.");
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Organization> getAllOrganizations() {
        List<Organization> orgs = jdbcTemplate.query(queries.SELECT_ALL_ORGANIZATIONS,
                new mappers.OrganizationMapper());

        orgs.forEach((current) -> {
            current.setLocation(setLocationForOrg(current.getId()));
            current.setPeople(getPeopleFromOrganization(current));
            current.getPeople().forEach((currentPerson) -> {
                currentPerson.setSuperpowers(getSuperheroPowers(currentPerson.getId()));
            });
        });

        return orgs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        if (organization.getId() != 0) {
            jdbcTemplate.update(queries.INSERT_ORGANIZATION_BY_ID,
                    organization.getId(),
                    organization.getName(),
                    organization.getEmail(),
                    organization.getPhone(),
                    organization.getLocation().getId());
        } else {
            jdbcTemplate.update(queries.INSERT_ORGANIZATION,
                    organization.getName(),
                    organization.getEmail(),
                    organization.getPhone(),
                    organization.getLocation().getId());
            organization.setId(getLastInsertId());
        }
        associateOrganizationWithPeople(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(queries.UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getEmail(),
                organization.getPhone(),
                organization.getLocation().getId(),
                organization.getId());

        deletePeopleFromOrganization(organization.getId());
        associateOrganizationWithPeople(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int id) {
        deletePeopleFromOrganization(id);
        jdbcTemplate.update(queries.DELETE_ORGANIZATION_BY_ID, id);
    }
// _                 _   _             
//| | ___   ___ __ _| |_(_) ___  _ __  
//| |/ _ \ / __/ _` | __| |/ _ \| '_ \ 
//| | (_) | (_| (_| | |_| | (_) | | | |
//|_|\___/ \___\__,_|\__|_|\___/|_| |_|
//                                    

    @Override
    public Location getLocationByID(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    queries.SELECT_LOCATION_BY_ID,
                    new mappers.LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract location object using ID.");
            return null;
        }
    }

    @Override
    public Location getLocationByName(String name) {
        try {
            return jdbcTemplate.queryForObject(
                    queries.SELECT_LOCATION_BY_NAME,
                    new mappers.LocationMapper(),
                    name);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract location object using name.");
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(queries.SELECT_ALL_LOCATIONS,
                new mappers.LocationMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocationOfSighting(Location location) {
        if (location.getId() != 0) {
            jdbcTemplate.update(queries.INSERT_LOCATION_OF_SIGHTING_BY_ID,
                    location.getId(),
                    location.getName(),
                    location.getDescription(),
                    location.getLatitude(),
                    location.getLongitude());
        } else {
            jdbcTemplate.update(queries.INSERT_LOCATION_OF_SIGHTING,
                    location.getName(),
                    location.getDescription(),
                    location.getLatitude(),
                    location.getLongitude());
            location.setId(getLastInsertId());
        }
    }

    @Override
    public void updateLocationOfSighting(Location location) {
        jdbcTemplate.update(queries.UPDATE_LOCATION_OF_SIGHTING,
                location.getName(),
                location.getDescription(),
                location.getLatitude(),
                location.getLongitude(),
                location.getId());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocationOfOrganization(Location location) {
        if (location.getId() != 0) {
            jdbcTemplate.update(queries.INSERT_LOCATION_OF_ORGANIZATION_BY_ID,
                    location.getId(),
                    location.getName(),
                    location.getDescription(),
                    location.getStreet(),
                    location.getCity(),
                    location.getState(),
                    location.getZip(),
                    location.getCountry());
        } else {
            jdbcTemplate.update(queries.INSERT_LOCATION_OF_ORGANIZATION,
                    location.getName(),
                    location.getDescription(),
                    location.getStreet(),
                    location.getCity(),
                    location.getState(),
                    location.getZip(),
                    location.getCountry());
            location.setId(getLastInsertId());
        }

    }

    @Override
    public void updateLocationOfOrganization(Location location) {
        jdbcTemplate.update(queries.UPDATE_LOCATION_OF_ORGANIZATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getCountry(),
                location.getId());
    }

    @Override
    public void deleteLocation(int id) {
        jdbcTemplate.update(queries.DELETE_LOCATION_BY_ID, id);
    }
//     _       _     _   _             
// ___(_) __ _| |__ | |_(_)_ __   __ _ 
/// __| |/ _` | '_ \| __| | '_ \ / _` |
//\__ \ | (_| | | | | |_| | | | | (_| |
//|___/_|\__, |_| |_|\__|_|_| |_|\__, |
//       |___/                   |___/ 

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting getSightingByID(int id) {
        try {
            Sighting sight = jdbcTemplate.queryForObject(
                    queries.SELECT_SIGHTING_BY_ID,
                    new mappers.SightingMapper(),
                    id);
            sight.setLocation(setLocationForSighting(sight.getId()));
            sight.setPeople(getPeopleFromSighting(sight));

            sight.getPeople().forEach((current) -> {
                current.setSuperpowers(getSuperheroPowers(current.getId()));
            });

            return sight;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract Sighting object using ID.");
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getSightingByDate(LocalDate date) {
        List<Sighting> sightings = jdbcTemplate.query(queries.SELECT_SIGHTINGS_BY_DATE,
                new mappers.SightingMapper(),
                date.toString());

        sightings.forEach((current) -> {
            current.setLocation(setLocationForSighting(current.getId()));
        });

        sightings.forEach((current) -> {
            current.setPeople(getPeopleFromSighting(current));
        });

        sightings.forEach((current) -> {
            current.getPeople().forEach((currentPerson) -> {
                currentPerson.setSuperpowers(getSuperheroPowers(currentPerson.getId()));
            });
        });

        return sightings;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbcTemplate.query(queries.SELECT_ALL_SIGHTINGS,
                new mappers.SightingMapper());

        sightings.forEach((current) -> {
            current.setLocation(setLocationForSighting(current.getId()));
        });

        sightings.forEach((current) -> {
            current.setPeople(getPeopleFromSighting(current));
        });

        sightings.forEach((current) -> {
            current.getPeople().forEach((currentPerson) -> {
                currentPerson.setSuperpowers(getSuperheroPowers(currentPerson.getId()));
            });
        });

        return sightings;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        if (sighting.getId() != 0) {
            jdbcTemplate.update(queries.INSERT_SIGHTING_BY_ID,
                    sighting.getId(),
                    sighting.getDate().toString(),
                    sighting.getLocation().getId());
        } else {
            jdbcTemplate.update(queries.INSERT_SIGHTING,
                    sighting.getDate().toString(),
                    sighting.getLocation().getId());
            sighting.setId(getLastInsertId());
        }
        associatePeopleWithSighting(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        deletePeopleFromSighting(sighting.getId());
        
        jdbcTemplate.update(queries.UPDATE_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getId(),
                sighting.getId());

        associatePeopleWithSighting(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int id) {
        deletePeopleFromSighting(id);
        jdbcTemplate.update(queries.DELETE_SIGHTING_BY_ID, id);
    }
    
    
// _          _                 
//| |__   ___| |_ __   ___ _ __ 
//| '_ \ / _ \ | '_ \ / _ \ '__|
//| | | |  __/ | |_) |  __/ |   
//|_| |_|\___|_| .__/ \___|_|   
//             |_|             

    public List<Superpower> getSuperheroPowers(int id) {
        List<Superpower> superheroPowers
                = jdbcTemplate.query(queries.SELECT_SUPERPOWERS_FOR_PERSON,
                        new mappers.SuperpowerMapper(),
                        id);

        return superheroPowers;
    }

    public void associatePowerWithPeople(List<Superpower> superpowers, int heroId) {
        superpowers.forEach((current) -> {
            jdbcTemplate.update(queries.ASSOCIATE_POWERS_WITH_PEOPLE,
                    heroId,
                    current.getId());
        });
    }

    public void deleteHeroPowers(int heroid) {
        jdbcTemplate.update(queries.DELETE_HERO_POWERS,
                heroid);
    }

    public void deletePowerFromPeople(int powerid) {
        jdbcTemplate.update(queries.DELETE_POWER_FROM_HEROS,
                powerid);
    }

    private Location setLocationForOrg(int id) {
        try {
            return jdbcTemplate.queryForObject(queries.GET_LOCATION_FROM_ORGANIZATION,
                    new mappers.LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract location object using organization: " + id + " id.");
            return null;
        }
    }

    private Location setLocationForSighting(int id) {
        try {
            return jdbcTemplate.queryForObject(queries.GET_LOCATION_FROM_SIGHTING,
                    new mappers.LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Could not extract location object using sighting: " + id + " id.");
            return null;
        }
    }

    private void associateOrganizationWithPeople(Organization org) {
        List<Person> people = org.getPeople();

        people.forEach((current) -> {
            jdbcTemplate.update(queries.ASSOCIATE_PEOPLE_WITH_ORGANIZATION,
                    current.getId(),
                    org.getId());
        });
    }

    private List<Person> getPeopleFromOrganization(Organization org) {
        List<Person> people = jdbcTemplate.query(queries.GET_PEOPLE_FOR_ORGANIZATION,
                new mappers.PersonMapper(),
                org.getId());
        return people;
    }

    private List<Person> getPeopleFromSighting(Sighting sight) {
        List<Person> people = jdbcTemplate.query(queries.GET_PEOPLE_FOR_SIGHTING,
                new mappers.PersonMapper(),
                sight.getId());
        return people;
    }

    private void associatePeopleWithSighting(Sighting sight) {
        List<Person> people = sight.getPeople();

        people.forEach((current) -> {
            jdbcTemplate.update(queries.ASSOCIATE_PEOPLE_WITH_SIGHTING,
                    current.getId(),
                    sight.getId());
        });
    }
    
    @Override
    public List<Organization> getOrganizationsForPerson(String name){
        List<Organization> orgsForPerson = 
                jdbcTemplate.query(queries.GET_ORGANIZATIONS_FOR_PERSON,
                        new mappers.OrganizationMapper(),
                        name);
        orgsForPerson.forEach((current) -> {
            current.setLocation(setLocationForOrg(current.getId()));
        });

        orgsForPerson.forEach((current) -> {
            current.setPeople(getPeopleFromOrganization(current));
        });

        orgsForPerson.forEach((current) -> {
            current.getPeople().forEach((currentPerson) -> {
                currentPerson.setSuperpowers(getSuperheroPowers(currentPerson.getId()));
            });
        });
        return orgsForPerson;
    }

    private void deletePeopleFromOrganization(int organizationid) {
        jdbcTemplate.update(queries.DELETE_PERSON_FROM_ORGANIZATION,
                organizationid);
    }

    private void deletePeopleFromSighting(int sightingid) {
        jdbcTemplate.update(queries.DELETE_PERSON_FROM_SIGHTING,
                sightingid);
    }

    // initialize jdbcTemplate
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // get the last inserted id in a table
    private int getLastInsertId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }
    
    @Override
    public List<Sighting> getTenRecentSightings() {
        List<Sighting> sightings = jdbcTemplate.query(queries.GET_TEN_RECENT_SIGHTINGS,
                new mappers.SightingMapper());

        sightings.forEach((current) -> {
            current.setLocation(setLocationForSighting(current.getId()));
        });

        sightings.forEach((current) -> {
            current.setPeople(getPeopleFromSighting(current));
        });

        sightings.forEach((current) -> {
            current.getPeople().forEach((currentPerson) -> {
                currentPerson.setSuperpowers(getSuperheroPowers(currentPerson.getId()));
            });
        });

        return sightings;
    }

//  /\/\   __ _ _ __  _ __   ___ _ __ 
// /    \ / _` | '_ \| '_ \ / _ \ '__|
/// /\/\ \ (_| | |_) | |_) |  __/ |   
//\/    \/\__,_| .__/| .__/ \___|_|   
//             |_|   |_|   
    private static final class mappers {

        public static final class PersonMapper implements RowMapper<Person> {

            @Override
            public Person mapRow(ResultSet rs, int i) throws SQLException {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setDescription(rs.getString("description"));
                person.setHero(rs.getInt("hero"));
                person.setVillain(rs.getInt("villain"));
                return person;
            }

        }

        public static final class SuperpowerMapper implements RowMapper<Superpower> {

            @Override
            public Superpower mapRow(ResultSet rs, int i) throws SQLException {
                Superpower superpower = new Superpower();
                superpower.setId(rs.getInt("id"));
                superpower.setDescription(rs.getString("description"));
                return superpower;
            }

        }

        public static final class OrganizationMapper implements RowMapper<Organization> {

            @Override
            public Organization mapRow(ResultSet rs, int i) throws SQLException {
                Organization org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
                org.setEmail(rs.getString("email"));
                org.setPhone(rs.getString("phone"));
                return org;
            }

        }

        public static final class LocationMapper implements RowMapper<Location> {

            @Override
            public Location mapRow(ResultSet rs, int i) throws SQLException {
                Location location = new Location();
                location.setId(rs.getInt("id"));
                location.setName(rs.getString("name"));
                location.setDescription(rs.getString("description"));
                location.setLatitude(rs.getDouble("latitude"));
                location.setLongitude(rs.getDouble("longitude"));
                location.setStreet(rs.getString("street"));
                location.setCity(rs.getString("city"));
                location.setState(rs.getString("state"));
                location.setZip(rs.getInt("zip"));
                location.setCountry(rs.getString("country"));
                return location;
            }

        }

        public static final class SightingMapper implements RowMapper<Sighting> {

            @Override
            public Sighting mapRow(ResultSet rs, int i) throws SQLException {
                Sighting sighting = new Sighting();
                sighting.setId(rs.getInt("id"));
                sighting.setDate(rs.getDate("date").toLocalDate());
                return sighting;
            }

        }
    }

}
