package com.productcatalogue.Card.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.productcatalogue.Card.Model.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

}
