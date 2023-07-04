package com.java.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "advertisement_users")
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
//	@OneToMany(mappedBy = "user")
//	private List<Advertisement> advertisements = new ArrayList<>();
	
}
