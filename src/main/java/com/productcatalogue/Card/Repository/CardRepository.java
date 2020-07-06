package com.productcatalogue.Card.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.productcatalogue.Card.Model.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	@Query(value = "SELECT SUM(price) FROM Card", nativeQuery = true)
	Optional<Long> totalValue();

}
