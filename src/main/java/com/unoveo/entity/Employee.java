package com.unoveo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    private int Id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String city;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString()

    {
        return  "Employee [employee name="
                + name
                + " , age=" + age
                + " , city=" + city + "]";


    }
}
