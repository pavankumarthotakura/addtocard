package com.productcatalogue.Card.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogue.Card.Dto.CardDto;
import com.productcatalogue.Card.Service.CardService;

@RestController(value = "/v1/api")
public class CardController {

	private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

	@Autowired
	CardService cardService;


	@RequestMapping(value = "/addtocard", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addToCard(@RequestBody CardDto cardDto) {
		LOG.info("Request Triggered in addToCard");
		cardService.addInCard(cardDto);
	}

	@RequestMapping(value = "/addtocard", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateToCard(@RequestBody CardDto cardDto) {
		LOG.info("Request Triggered in updateToCard");
		cardService.UpdatetoCard(cardDto);
	}

	@RequestMapping(value = "/addtocard", method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void DeleteInCard(@RequestBody CardDto cardDto) {
		LOG.info("Request Triggered in DeleteInCard");
		cardService.DeleteInCard(cardDto);
	}

}
