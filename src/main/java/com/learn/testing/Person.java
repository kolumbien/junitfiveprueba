package com.learn.testing;

public class Person {

    private String firstName;
    private String lastName;

    public Person() {
        this.firstName="";
        this.lastName="";
    }

    public Person(String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEmpty() {
        return this.firstName.isEmpty() && this.lastName.isEmpty();
    }

    public void setNames(String name, String lastname) {
        setFirstName(name);
        setLastName(lastname);
    }
}
