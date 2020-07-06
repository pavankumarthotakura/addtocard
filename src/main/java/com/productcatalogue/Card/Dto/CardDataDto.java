package com.productcatalogue.Card.Dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDataDto {

	@JsonProperty(value = "cardList")
	List<CardDto> cardDto = new ArrayList<>();

	@JsonProperty(value = "total")
	Long total;
}
