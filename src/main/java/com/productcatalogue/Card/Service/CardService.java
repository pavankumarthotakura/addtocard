package com.productcatalogue.Card.Service;

import com.productcatalogue.Card.Dto.CardDto;

public interface CardService {

	void addInCard(CardDto carddto);

	void UpdatetoCard(CardDto carddto);

	void DeleteInCard(CardDto carddto);
}
