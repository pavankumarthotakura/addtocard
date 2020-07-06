package com.productcatalogue.Card.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

	@JsonProperty(value = "product_id")
	private Long id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "price")
	private Double price;

	@JsonProperty(value = "imageUrl")
	private String image;

	@JsonProperty(value = "size")
	private int size;

	@JsonProperty(value = "color")
	private String color;

	@JsonProperty(value = "brandName")
	private String brand;

	@JsonProperty(value = "addedToCard")
	private Boolean addedToCard;

}
