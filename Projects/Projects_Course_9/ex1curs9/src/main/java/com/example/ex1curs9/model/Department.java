package com.example.ex1curs9.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String description;

	/*   inverse side of the relationship
		 this is a bidirectional one-to-many relationship
		 the optimal way to implement a one-to-many relationship:
		 1. unidirectional relationship with @ManyToOne on one entity and nothing on the other entity
		 2. bidirectional relationship with @ManyToOne on one entity and @OneToMany on the other entity
	 */
	@OneToMany(mappedBy = "department")
 	private List<Employee> employees;

	public Department() {
	}

	public Department(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
