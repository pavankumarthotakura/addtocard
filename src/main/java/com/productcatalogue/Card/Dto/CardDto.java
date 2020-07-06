package com.productcatalogue.Card.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {

	@JsonProperty(value= "quantity")
	private int quantity;

	@JsonProperty(value= "price")
	private Double price;

	@JsonProperty(value= "name")
	private String name;

	@JsonProperty(value = "card_id")
	private Long id;

	@JsonProperty(value = "product_id")
	private Long product_id;

}
