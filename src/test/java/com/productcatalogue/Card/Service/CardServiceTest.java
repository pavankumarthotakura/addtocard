package com.productcatalogue.Card.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.productcatalogue.Card.Dto.CardDto;
import com.productcatalogue.Card.Model.Card;
import com.productcatalogue.Card.Model.Product;
import com.productcatalogue.Card.Repository.CardRepository;
import com.productcatalogue.Card.Repository.ProductRepository;

@SpringBootTest
public class CardServiceTest {

	@InjectMocks
	CardServiceImpl cardService;

	@Mock
	ProductRepository productRepository;

	@Mock
	CardRepository cardRepository;

	@BeforeEach
	void setUp() {
		ModelMapper modelMapper = new ModelMapper();
		ReflectionTestUtils.setField(cardService, "modelMapper", modelMapper);
	}

	@Test
	void addInCardTest() {
		CardDto cardDto = new CardDto();
		cardDto.setId(1234L);
		cardDto.setProduct_id(124L);
		Product product = new Product();
		product.setAddedToCard(false);
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
		cardService.addInCard(cardDto);
		Mockito.verify(cardRepository, atLeastOnce()).save(any());
	}

	@Test
	void UpdatetoCardTest() {
		CardDto cardDto = new CardDto();
		cardDto.setId(1234L);
		cardDto.setProduct_id(124L);
		cardDto.setQuantity(2);
		Product product = new Product();
		product.setPrice(23.00);
		product.setAddedToCard(true);
		Card card = new Card();
		card.setId(123L);
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
		when(cardRepository.findById(anyLong())).thenReturn(Optional.of(card));
		cardService.UpdatetoCard(cardDto);
		Mockito.verify(cardRepository, atLeastOnce()).save(any());
	}
}
