package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SSDao;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Person;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.dto.Superpower;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tylerbates
 */
@Controller
public class SuperController {

    private SSDao dao;
    private static final int firstInDB = 1;

    @Inject
    public SuperController(SSDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<Sighting> recentSightings = dao.getTenRecentSightings();
        model.addAttribute("recentSightings", recentSightings);

        return "index";
    }

    //---------------HEROES--------------------------------
    @RequestMapping(value = "/displayHeroes", method = RequestMethod.GET)
    public String displayHeroesPage(Model model) {
        List<Person> people = dao.getAllPeople();
        model.addAttribute("people", people);
        return "displayHeroes";
    }

    @RequestMapping(value = "/heroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        Person personDet = dao.getPersonByID(id);
        List<Person> people = dao.getAllPeople();
        model.addAttribute("people", people);
        model.addAttribute("personDet", personDet);
        return "displayHeroes";
    }

    @RequestMapping(value = "/getAddHeroForm", method = RequestMethod.GET)
    public String getAddHeroForm(HttpServletRequest request, Model model) {
        List<Superpower> allSuperpowers = dao.getAllSuperpowers();
        boolean add = true;
        boolean edit = false;
        model.addAttribute("superpowers", allSuperpowers);
        model.addAttribute("add", add);
        model.addAttribute("edit", edit);
        return "addEditHero";
    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(HttpServletRequest request, Model model) {
        Person person = new Person();
        person.setName(request.getParameter("name"));
        person.setDescription(request.getParameter("description"));
        String[] powerIds = request.getParameterValues("superpowers");
        List<Superpower> powers = new ArrayList<>();
        for (String powerId : powerIds) {
            powers.add(dao.getSuperpowerByID(Integer.parseInt(powerId)));
        }
        person.setSuperpowers(powers);

        if (request.getParameter("heroorvillain").equals("villain")) {
            person.setVillain(1);
            person.setHero(0);
        } else {
            person.setHero(1);
            person.setVillain(0);
        }

        dao.addPerson(person);
        return "redirect:displayHeroes";
    }

    @RequestMapping(value = "/findHeroToEdit", method = RequestMethod.GET)
    public String findHeroToEdit(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        Person person = dao.getPersonByID(id);
        model.addAttribute("heroToEdit", person);
        boolean edit = true;
        boolean add = false;
        List<Superpower> allSuperpowers = dao.getAllSuperpowers(); 
        model.addAttribute("superpowers", allSuperpowers);
        model.addAttribute("edit", edit);
        model.addAttribute("add", add);
        return "addEditHero";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        Person person = dao.getPersonByID(id);
        String name = request.getParameter("name");
        if (name.length() != 0) {
            person.setName(name);
        }

        String description = request.getParameter("description");
        if (description.length() != 0) {
            person.setDescription(description);
        }

        String[] powerIds = request.getParameterValues("superpowers");
        if (powerIds[0] != null) {
            List<Superpower> powers = new ArrayList<>();
            for (String powerId : powerIds) {
                powers.add(dao.getSuperpowerByID(Integer.parseInt(powerId)));
            }
            person.setSuperpowers(powers);
        }
        
        if (request.getParameter("heroorvillain").equals("villain")) {
            person.setVillain(1);
            person.setHero(0);
        } else {
            person.setHero(1);
            person.setVillain(0);
        }

        dao.updatePerson(person);

        return "redirect:displayHeroes";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        dao.deletePerson(id);
        return "redirect:displayHeroes";
    }
    //------------------------------------------------------

    //----------------SUPERPOWERS---------------------------
    @RequestMapping(value = "/displaySuperpowers", method = RequestMethod.GET)
    public String displaySuperpowersPage(Model model) {
        List<Superpower> powers = dao.getAllSuperpowers();
        model.addAttribute("powers", powers);
        return "displaySuperpowers";
    }

    @RequestMapping(value = "/superpowerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("powerId"));
        Superpower powerDet = dao.getSuperpowerByID(id);
        model.addAttribute("powerDet", powerDet);
        List<Superpower> powers = dao.getAllSuperpowers();
        model.addAttribute("powers", powers);
        return "displaySuperpowers";
    }

    @RequestMapping(value = "/getAddPowerForm", method = RequestMethod.GET)
    public String getAddPowerForm(HttpServletRequest request, Model model) {
        boolean add = true;
        boolean edit = false;
        model.addAttribute("add", add);
        model.addAttribute("edit", edit);
        return "addEditPower";
    }

    @RequestMapping(value = "/createSuperpower", method = RequestMethod.POST)
    public String createSuperpower(HttpServletRequest request, Model model) {
        Superpower newPower = new Superpower();
        newPower.setDescription(request.getParameter("description"));
        dao.addSuperpower(newPower);
        return "redirect:displaySuperpowers";
    }

    @RequestMapping(value = "/findPowerToEdit", method = RequestMethod.GET)
    public String findPowerToEdit(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("powerId"));
        Superpower powerToEdit = dao.getSuperpowerByID(id);
        model.addAttribute("powerToEdit", powerToEdit);
        boolean edit = true;
        boolean add = false;
        model.addAttribute("edit", edit);
        model.addAttribute("add", add);
        return "addEditPower";
    }

    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(HttpServletRequest request, Model model) {
        Superpower editedPower
                = dao.getSuperpowerByID(
                        Integer.parseInt(request.getParameter("id")));
        String description = request.getParameter("description");
        if (description.length() != 0) {
            editedPower.setDescription(description);
            dao.updateSuperpower(editedPower);
        }
        return "redirect:displaySuperpowers";
    }

    @RequestMapping(value = "/deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("powerId"));
        dao.deleteSuperpower(id);
        return "redirect:displaySuperpowers";
    }
    //--------------------------------------------------

    //---------------------SIGHTINGS--------------------
    @RequestMapping(value = "/displaySightings", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        List<Sighting> sightings = dao.getAllSightings();
        model.addAttribute("sightings", sightings);
        return "displaySightings";
    }

    @RequestMapping(value = "/sightingDetails", method = RequestMethod.GET)
    public String displaySightingsDetails(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("sightId"));
        Sighting sightDet = dao.getSightingByID(id);
        List<Sighting> sightings = dao.getAllSightings();
        model.addAttribute("sightings", sightings);
        model.addAttribute("sightDet", sightDet);
        return "displaySightings";
    }
    
    @RequestMapping(value = "/getAddSightingForm", method = RequestMethod.GET)
    public String getAddSightingForm(HttpServletRequest request, Model model) {
        List<Person> people = dao.getAllPeople();
        List<Location> allLocations = dao.getAllLocations();
        boolean add = true;
        boolean edit = false;
        model.addAttribute("locations", allLocations);
        model.addAttribute("people", people);
        model.addAttribute("add", add);
        model.addAttribute("edit", edit);
        return "addEditSight";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, Model model) {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("uuuu-MM-dd")));
        sighting.setLocation(dao.getLocationByID(Integer.parseInt(request.getParameter("location"))));
        String[] heroIds = request.getParameterValues("people");
        List<Person> heroes = new ArrayList<>();
        for (String heroId : heroIds) {
            heroes.add(dao.getPersonByID(Integer.parseInt(heroId)));
        }
        sighting.setPeople(heroes);
        
        dao.addSighting(sighting);
        return "redirect:displaySightings";
    }

    @RequestMapping(value = "/findSightToEdit", method = RequestMethod.GET)
    public String findSightToEdit(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("sightId"));
        Sighting sight = dao.getSightingByID(id);
        model.addAttribute("sightToEdit", sight);
        boolean edit = true;
        boolean add = false;
        List<Location> allLocations = dao.getAllLocations();
        List<Person> allPeople = dao.getAllPeople(); 
        model.addAttribute("locations", allLocations);
        model.addAttribute("people", allPeople);
        model.addAttribute("edit", edit);
        model.addAttribute("add", add);
        return "addEditSight";
    }

    @RequestMapping(value = "/editSight", method = RequestMethod.POST)
    public String editSight(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("sightId"));
        Sighting sight = dao.getSightingByID(id);
        String date = request.getParameter("date");
        if (date.length() != 0) {
            sight.setDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd")));
        }

        sight.setLocation(dao.getLocationByID(Integer.parseInt(request.getParameter("location"))));

        String[] heroIds = request.getParameterValues("people");
        if (heroIds[0] != null) {
            List<Person> heroes = new ArrayList<>();
            for (String heroId : heroIds) {
                heroes.add(dao.getPersonByID(Integer.parseInt(heroId)));
            }
            sight.setPeople(heroes);
        }
        

        dao.updateSighting(sight);

        return "redirect:displaySightings";
    }

    @RequestMapping(value = "/deleteSight", method = RequestMethod.GET)
    public String deleteSight(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("sightId"));
        dao.deleteSighting(id);
        return "redirect:displaySightings";
    }
    //---------------------------------------------------

    //-----------------------ORGANIZATIONS---------------
    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public String displayOrganizationsPage(HttpServletRequest request, Model model) {
        List<Organization> organizations = dao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "displayOrganizations";
    }

    @RequestMapping(value = "/organizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("orgId"));
        Organization orgDet = dao.getOrganizationByID(id);
        List<Organization> organizations = dao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        model.addAttribute("orgDet", orgDet);
        return "displayOrganizations";
    }
    
    @RequestMapping(value = "/getAddOrganizationForm", method = RequestMethod.GET)
    public String getAddOrganizationForm(HttpServletRequest request, Model model) {
        List<Person> people = dao.getAllPeople();
        List<Location> allLocations = dao.getAllLocations();
        boolean add = true;
        boolean edit = false;
        model.addAttribute("locations", allLocations);
        model.addAttribute("people", people);
        model.addAttribute("add", add);
        model.addAttribute("edit", edit);
        return "addEditOrganizations";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request, Model model) {
        Organization org = new Organization();
        org.setLocation(dao.getLocationByID(Integer.parseInt(request.getParameter("location"))));
        String[] heroIds = request.getParameterValues("people");
        List<Person> heroes = new ArrayList<>();
        for (String heroId : heroIds) {
            heroes.add(dao.getPersonByID(Integer.parseInt(heroId)));
        }
        org.setPeople(heroes);
        org.setName(request.getParameter("name"));
        org.setPhone(request.getParameter("phone"));
        org.setEmail(request.getParameter("email"));
        dao.addOrganization(org);
        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "/findOrgToEdit", method = RequestMethod.GET)
    public String findOrgToEdit(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("orgId"));
        Organization org = dao.getOrganizationByID(id);
        model.addAttribute("orgToEdit", org);
        boolean edit = true;
        boolean add = false;
        List<Location> allLocations = dao.getAllLocations();
        List<Person> allPeople = dao.getAllPeople(); 
        model.addAttribute("locations", allLocations);
        model.addAttribute("people", allPeople);
        model.addAttribute("edit", edit);
        model.addAttribute("add", add);
        return "addEditOrganizations";
    }

    @RequestMapping(value = "/editOrg", method = RequestMethod.POST)
    public String editOrg(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("orgId"));
        Organization org = dao.getOrganizationByID(id);
        String name = request.getParameter("name");
        if (name.length() != 0) {
            org.setName(name);
        }

        String email = request.getParameter("email");
        if (email.length() != 0) {
            org.setEmail(email);
        }
        
        String phone = request.getParameter("phone");
        if (name.length() != 0) {
            org.setPhone(phone);
        }
        
        org.setLocation(dao.getLocationByID(Integer.parseInt(request.getParameter("location"))));

        String[] heroIds = request.getParameterValues("people");
        if (heroIds[0] != null) {
            List<Person> heroes = new ArrayList<>();
            for (String heroId : heroIds) {
                heroes.add(dao.getPersonByID(Integer.parseInt(heroId)));
            }
            org.setPeople(heroes);
        }
        

        dao.updateOrganization(org);

        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("orgId"));
        dao.deleteOrganization(id);
        return "redirect:displayOrganizations";
    }
    //---------------------------------------------------

    //--------------------LOCATIONS----------------------
    @RequestMapping(value = "/locationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("locId"));
        Location locDet = dao.getLocationByID(id);
        List<Location> locations = dao.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("locDet", locDet);
        return "displayLocations";
    }

    @RequestMapping(value = "/displayLocations", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        List<Location> locations = dao.getAllLocations();
        model.addAttribute("locations", locations);
        return "displayLocations";
    }
    
    @RequestMapping(value = "/getAddLocationForm", method = RequestMethod.GET)
    public String getAddLocationForm(HttpServletRequest request, Model model) {
        boolean add = true;
        boolean edit = false;
        model.addAttribute("add", add);
        model.addAttribute("edit", edit);
        return "addEditLocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, Model model) {
        Location loc = new Location();
        loc.setName(request.getParameter("name"));
        loc.setDescription(request.getParameter("description"));
        try {
        loc.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        loc.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        loc.setZip(Integer.parseInt(request.getParameter("zip")));
        } catch (NumberFormatException e){}
        loc.setCity(request.getParameter("city"));
        loc.setState(request.getParameter("state"));
        loc.setStreet(request.getParameter("street"));
        loc.setCountry(request.getParameter("country"));
        
        if (loc.getCity() != null) {
            dao.addLocationOfOrganization(loc);
        } else {
            dao.addLocationOfSighting(loc);
        }
        
        return "redirect:displayLocations";
    }

    @RequestMapping(value = "/findLocToEdit", method = RequestMethod.GET)
    public String findLocToEdit(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("locId"));
        Location loc = dao.getLocationByID(id);
        model.addAttribute("locToEdit", loc);
        boolean edit = true;
        boolean add = false;
        model.addAttribute("edit", edit);
        model.addAttribute("add", add);
        return "addEditLocation";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("locId"));
        Location loc = dao.getLocationByID(id);
        String name = request.getParameter("name");
        if (name.length() != 0) {
            loc.setName(name);
        }

        String description = request.getParameter("description");
        if (description.length() != 0) {
            loc.setDescription(description);
        }
        
        String latitude = request.getParameter("latitude");
        if (latitude.length() != 0) {
            loc.setLatitude(Double.parseDouble(latitude));
        }
        
        String longitude = request.getParameter("longitude");
        if (longitude.length() != 0) {
            loc.setLongitude(Double.parseDouble(longitude));
        }
        
        String street = request.getParameter("street");
        if (street.length() != 0) {
            loc.setStreet(street);
        }
        
        String city = request.getParameter("city");
        if (city.length() != 0) {
            loc.setCity(city);
        }
        
        String state = request.getParameter("state");
        if (state.length() != 0) {
            loc.setState(state);
        }
        
        String zip = request.getParameter("zip");
        if (zip.length() != 0) {
            loc.setZip(Integer.parseInt(zip));
        }
        
        String country = request.getParameter("country");
        if (country.length() != 0) {
            loc.setCountry(country);
        }

        if (longitude.length() == 0 || latitude.length() == 0) {
            dao.updateLocationOfOrganization(loc);
        } else {
            dao.updateLocationOfSighting(loc);
        }

        return "redirect:displayLocations";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("locId"));
        dao.deleteLocation(id);
        return "redirect:displayLocations";
    }
    //----------------------------------------------------

}
