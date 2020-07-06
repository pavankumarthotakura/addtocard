package com.productcatalogue.Card.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogue.Card.Dto.CardDto;
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

	@RequestMapping(value = "/addtocard", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addToCard(@RequestBody CardDto cardDto) {
		cardService.addInCard(cardDto);
	}

	@RequestMapping(value = "/addtocard", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public void updateToCard(@RequestBody CardDto cardDto) {
		cardService.UpdatetoCard(cardDto);
	}

	@RequestMapping(value = "/addtocard", method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void DeleteInCard(@RequestBody CardDto cardDto) {
		cardService.DeleteInCard(cardDto);
	}



}
