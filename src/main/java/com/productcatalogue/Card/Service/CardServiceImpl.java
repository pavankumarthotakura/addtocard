package com.productcatalogue.Card.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productcatalogue.Card.Dto.CardDto;
import com.productcatalogue.Card.Exception.CatalogBaseException;
import com.productcatalogue.Card.Model.Card;
import com.productcatalogue.Card.Model.Product;
import com.productcatalogue.Card.Repository.CardRepository;
import com.productcatalogue.Card.Repository.ProductRepository;

@Service
public class CardServiceImpl implements CardService {

	private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CardRepository cardRepository;


	@Transactional(readOnly = false)
	@Override
	public void addInCard(CardDto carddto) {
        LOG.info("adding card info with id {}.", carddto.getId());
		Optional<Product> product = productRepository.findById(carddto.getProduct_id());
		Card card = modelMapper.map(carddto, Card.class);
		if(!product.isPresent() || product.get().isAddedToCard()) {
			LOG.error("Error while adding to card with id {}.", carddto.getId());
			throw new CatalogBaseException("product not fount in DB");
		}
		Product product1 = product.get();
		product1.setAddedToCard(true);
		card.setProduct(product1);
		LOG.info("Saving product to card in DB");
		cardRepository.save(card);
	}

	@Transactional(readOnly = false)
	@Override
	public void UpdatetoCard(CardDto carddto) {
		LOG.info("update card info with id {}.", carddto.getId());
		Optional<Product> product = productRepository.findById(carddto.getProduct_id());
		Optional<Card> card = cardRepository.findById(carddto.getId());
		if(!product.isPresent() || !product.get().isAddedToCard() || !card.isPresent()) {
			LOG.error("Error while Updating to card with id {}.", carddto.getId());
			throw new CatalogBaseException("product not fount in DB");
		}
		Card cardVal = card.get();
		Product productVal = product.get();
		cardVal.setPrice(productVal.getPrice() * carddto.getQuantity());
		cardVal.setQuantity(carddto.getQuantity());
		LOG.info("Updating product in card, with id {}.", carddto.getId());
		cardRepository.save(cardVal);
	}

	@Transactional(readOnly = false)
	@Override
	public void DeleteInCard(CardDto carddto) {
		LOG.info("Delete card info with id {}.", carddto.getId());
		Optional<Card> card = cardRepository.findById(carddto.getId());
		if(!card.isPresent()) {
			LOG.error("Error while Deleting card with id {}.", carddto.getId());
			 throw new CatalogBaseException("product not fount in DB");
		}
		Product product = card.get().getProduct();
		product.setAddedToCard(false);
		LOG.info("Deleting product from card, with id {}.", carddto.getId());
		cardRepository.delete(card.get());
		productRepository.save(product);
	}

}
