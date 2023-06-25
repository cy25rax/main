package com.java.avito.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "advertisement")
@NoArgsConstructor
public class Advertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
