package com.productcatalogue.Card.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogue.Card.Dto.DataDto;
import com.productcatalogue.Card.Service.CardService;

@RestController
public class CardController {

	@Autowired
	CardService cardService;

	@RequestMapping(value = "/data", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DataDto productCatalogue() {
		return cardService.productCatalogueJson();
	}




}
