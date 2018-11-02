
package com.sg.superherosightings.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author tylerbates
 */
@Data
public class Organization {

    private int id;
    private String name;
    private String email;
    private String phone;
    private Location location;
    private List<Person> people;
    
}
