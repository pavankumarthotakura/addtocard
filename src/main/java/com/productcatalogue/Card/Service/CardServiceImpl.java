package com.productcatalogue.Card.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productcatalogue.Card.Dto.CardDataDto;
import com.productcatalogue.Card.Dto.CardDto;
import com.productcatalogue.Card.Dto.DataDto;
import com.productcatalogue.Card.Dto.ProductDto;
import com.productcatalogue.Card.Model.Card;
import com.productcatalogue.Card.Model.Product;
import com.productcatalogue.Card.Repository.CardRepository;
import com.productcatalogue.Card.Repository.ProductRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CardRepository cardRepository;

	@Transactional(readOnly = true)
	@Override
	public DataDto productCatalogueJson() {
		List<Product> productlist = StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
		List<ProductDto> productDtoList = modelMapper.map(productlist, new TypeToken<List<ProductDto>>(){}.getType());
		List<Card> cardlist = StreamSupport.stream(cardRepository.findAll().spliterator(), false).collect(Collectors.toList());
		Map<Long, Product> cardMap = cardlist.stream().collect(Collectors.toMap(card -> card.getId(), card-> card.getProduct()));
		List<CardDto> cardDtoList = modelMapper.map(cardlist, new TypeToken<List<CardDto>>(){}.getType());
		cardDtoList.forEach(card -> {
			if(cardMap.containsKey(card.getId())) {
				card.setProduct_id(cardMap.get(card.getId()).getId());
			}
		});
		Long total = cardRepository.totalValue().isPresent() ? cardRepository.totalValue().get() : 0L;
		CardDataDto cardListDto = new CardDataDto(cardDtoList, total);
		return new DataDto(productDtoList, cardListDto);
	}


	@Transactional(readOnly = false)
	@Override
	public void addInCard(CardDto carddto) {
		Optional<Product> product = productRepository.findById(carddto.getProduct_id());
		Card card = modelMapper.map(carddto, Card.class);
		if(product.isPresent() && !product.get().isAddedToCard()) {
			product.get().setAddedToCard(true);
			card.setProduct(product.get());
		} else {
			throw new RuntimeException("product not fount in DB");
		}
		cardRepository.save(card);
	}

	@Transactional(readOnly = false)
	@Override
	public void UpdatetoCard(CardDto carddto) {
		Optional<Product> product = productRepository.findById(carddto.getProduct_id());
		Optional<Card> card = cardRepository.findById(carddto.getId());
		if(product.isPresent() && product.get().isAddedToCard() && card.isPresent()) {
			Card cardVal = card.get();
			Product productVal = product.get();
			cardVal.setPrice(productVal.getPrice() * carddto.getQuantity());
			cardVal.setQuantity(carddto.getQuantity());
		} else {
			throw new RuntimeException("product not fount in DB");
		}
		cardRepository.save(card.get());
	}

	@Transactional(readOnly = false)
	@Override
	public void DeleteInCard(CardDto carddto) {
		Optional<Card> card = cardRepository.findById(carddto.getId());
		if(card.isPresent()) {
			Product product = card.get().getProduct();
			product.setAddedToCard(false);
			cardRepository.delete(card.get());
			productRepository.save(product);
		} else {
			throw new RuntimeException("product not fount in DB");
		}
	}

}
