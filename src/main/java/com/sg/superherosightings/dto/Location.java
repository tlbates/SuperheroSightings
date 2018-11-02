
package com.sg.superherosightings.dto;

import lombok.Data;

/**
 *
 * @author tylerbates
 */
@Data
public class Location {

    private int id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String country;
    
}
