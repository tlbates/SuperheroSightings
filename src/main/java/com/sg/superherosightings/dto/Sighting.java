
package com.sg.superherosightings.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 *
 * @author tylerbates
 */
@Data
public class Sighting {
    
    private int id;
    private LocalDate date;
    private Location location;
    private List<Person> people;
}
