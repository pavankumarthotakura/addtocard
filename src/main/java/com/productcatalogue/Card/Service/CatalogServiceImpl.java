package com.productcatalogue.Card.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CatalogServiceImpl implements CatalogService {

	private static final Logger LOG = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CardRepository cardRepository;

	@Transactional(readOnly = true)
	@Override
	public DataDto productCatalogueJson() {
		LOG.info("Getting Catalog information from db");
		List<Product> productList = StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
		List<ProductDto> productDtoList = modelMapper.map(productList, new TypeToken<List<ProductDto>>(){}.getType());
		List<Card> cardList = StreamSupport.stream(cardRepository.findAll().spliterator(), false).collect(Collectors.toList());
		Map<Long, Product> cardMap = cardList.stream().collect(Collectors.toMap(card -> card.getId(), card-> card.getProduct()));
		List<CardDto> cardDtoList = modelMapper.map(cardList, new TypeToken<List<CardDto>>(){}.getType());
		cardDtoList.forEach(card -> {
			if(cardMap.containsKey(card.getId())) {
				card.setProduct_id(cardMap.get(card.getId()).getId());
			}
		});
		Long total = cardRepository.totalValue().isPresent() ? cardRepository.totalValue().get() : 0L;
		CardDataDto cardListDto = new CardDataDto(cardDtoList, total);
		LOG.info("Formated Response to Dto");
		return new DataDto(productDtoList, cardListDto);
	}

}
