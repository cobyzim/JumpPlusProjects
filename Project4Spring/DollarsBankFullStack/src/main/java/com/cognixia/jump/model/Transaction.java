package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Customer customer;
	
	@NotBlank
	@Column(nullable = false)
	private String transaction;
	
	@NotBlank
	@Column(nullable = false)
	private Integer transCounter;

	public Transaction(Integer id, @NotBlank String transaction, @NotBlank Integer transCounter) {
		super();
		this.id = id;
		this.transaction = transaction;
		this.transCounter = transCounter;
	}
	
	

}
