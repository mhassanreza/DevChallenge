package com.dev.hassan.devchallenge.model;

import java.io.Serializable;

/**
 * Created by Hassan on 2/22/2016.
 */
public class NameModel implements Serializable {
    private String FirstName;
    private String LastName;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }
}