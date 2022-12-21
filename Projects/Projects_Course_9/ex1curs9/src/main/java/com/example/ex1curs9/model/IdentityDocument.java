package com.example.ex1curs9.model;

import javax.persistence.*;

@Entity
public class IdentityDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String series;

	@Enumerated(EnumType.STRING) // vrem sa stocam in coloana documentType valorile de enum si nu ordinal-ul corespunzator
	private DocumentType documentType;

	@OneToOne(mappedBy = "identityDocument") // numele atributului din relatie care defineste FK
	//inverse side of the relationship - mappedBy
	private Employee employee;

	public IdentityDocument() {
	}

	public IdentityDocument(long id, String series) {
		this.id = id;
		this.series = series;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
}
