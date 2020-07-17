package com.productcatalogue.Card.Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;

	@Lob
	@Column(name = "image")
	private String image;

	@Column(name = "size")
	private int size;

	@Column(name = "color")
	private String color;

	@Column(name = "brand")
	private String brand;

	@Column(name = "addedToCard")
	private boolean addedToCard;

}
