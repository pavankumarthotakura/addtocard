package com.productcatalogue.Card.Service;

import com.productcatalogue.Card.Dto.CardDto;
import com.productcatalogue.Card.Dto.DataDto;

public interface CardService {

	DataDto productCatalogueJson();

	void addInCard(CardDto carddto);

	void UpdatetoCard(CardDto carddto);

	void DeleteInCard(CardDto carddto);
}
