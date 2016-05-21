package com.dao;

import com.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */

public interface ProductDao extends PagingAndSortingRepository<Product, String> {
}
