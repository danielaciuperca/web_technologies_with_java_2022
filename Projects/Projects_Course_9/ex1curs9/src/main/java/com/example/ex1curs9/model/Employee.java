package com.example.ex1curs9.model;

import javax.persistence.*;
import java.util.*;

@Entity
//@Table we define the physical mapping of the table in the db
@Table(name = "employees")
public class Employee {
	@Id // it will be mapped to PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // we'll use the auto_increment sequence of the db
	private long id;

	// with @Column we define the physical mapping of the column in the db
	@Column(name = "employee_name", unique = true, nullable = false)
	private String name;

	private String email;

	// direct side of the relationship - @JoinColumn -> FK
	@OneToOne
	@JoinColumn(name = "identityDocument_id")
	private IdentityDocument identityDocument;

	/* direct side of the relationship -> FK
	   this is a bidirectional one-to-many relationship
	   the optimal way to implement a one-to-many relationship is:
	   1. unidirectional relationship with @ManyToOne on one entity and nothing on the other entity
	   2. bidirectional relationship with @ManyToOne on one entity and @OneToMany on the other entity
	 */
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;


	/* bidirectional many-to-many relationship
	   direct side of the relationship - @JoinTable
	   if @JoinTable is not used, Hibernate will generate the join table with the default settings
	*/
	@ManyToMany
	@JoinTable(name = "employees_projects",
	joinColumns = @JoinColumn(name = "employee_id"),
	inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;

	public Employee() {
		// every entity should have the default constructor
	}

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
