
package com.sg.superherosightings.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author tylerbates
 */
@Data
public class Person {

    private int id;
    private String name;
    private String description;
    private int hero;
    private int villain;
    private List<Superpower> superpowers;
    
}
