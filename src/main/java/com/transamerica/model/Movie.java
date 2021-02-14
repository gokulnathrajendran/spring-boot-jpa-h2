package com.transamerica.model;

import javax.persistence.*;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name="movie")
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "title")
	@NonNull
	private String title;
	
	@Column(name = "description")
	@NonNull
	private String description;
	
	@Column(name = "available")
	@NonNull
	private boolean available;
	
	@Override
	public String toString() {
		return "Movie [id = " + id +" title = "+title+" description = "+description+" available = "+ available +"]";		
	}
	
	

}
