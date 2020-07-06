package com.productcatalogue.Card.Dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataDto {

	@JsonProperty(value = "productList")
	List<ProductDto> productDtoList = new ArrayList<>();

	@JsonProperty(value = "CardData")
	CardDataDto cardDataDto;

}
