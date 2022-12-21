package com.example.ex1curs9.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    // inverse side of the relationship - mappedBy
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

}
