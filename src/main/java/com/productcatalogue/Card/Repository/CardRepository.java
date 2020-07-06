package com.productcatalogue.Card.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcatalogue.Card.Model.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
