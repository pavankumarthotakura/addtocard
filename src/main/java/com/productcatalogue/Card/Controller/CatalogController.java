package com.productcatalogue.Card.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogue.Card.Dto.DataDto;
import com.productcatalogue.Card.Service.CatalogService;

@RestController
public class CatalogController {

	private static final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

	@Autowired
	CatalogService catalogService;

	@RequestMapping(value = "/api/v1/data", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DataDto productCatalogue() {
		LOG.info("Request Triggered in ProductCatalog");
		return catalogService.productCatalogueJson();
	}
}
