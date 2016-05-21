package com.dao;

import com.model.Discount;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */
public interface DiscountDao extends PagingAndSortingRepository<Discount, String> {
    Discount findByCode(String sessionId);
}
