package com.productcatalogue.Card.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.productcatalogue.Card.Dto.DataDto;
import com.productcatalogue.Card.Model.Card;
import com.productcatalogue.Card.Model.Product;
import com.productcatalogue.Card.Repository.CardRepository;
import com.productcatalogue.Card.Repository.ProductRepository;

@SpringBootTest
public class CataLogServiceTest {

	@InjectMocks
	CatalogServiceImpl catalogService;

	@Mock
	ProductRepository productRepository;

	@Mock
	CardRepository cardRepository;

	@BeforeEach
	void setUp() {
		ModelMapper modelMapper = new ModelMapper();
		ReflectionTestUtils.setField(catalogService, "modelMapper", modelMapper);
	}

	@Test
	void productCatalogueJsonTest() {
		Product product = new Product();
		product.setPrice(23.00);
		product.setAddedToCard(true);
		product.setId(123L);
		Card card = new Card();
		card.setId(123L);
		card.setProduct(product);
		when(productRepository.findAll()).thenReturn(Arrays.asList(product));
		when(cardRepository.findAll()).thenReturn(Arrays.asList(card));
		DataDto dataDto = catalogService.productCatalogueJson();
		assertNotNull(dataDto);
		assertEquals(1, dataDto.getProductDtoList().size());
	}

}
