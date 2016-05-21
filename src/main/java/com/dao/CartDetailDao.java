package com.dao;

import com.model.CartDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */
public interface CartDetailDao extends CrudRepository<CartDetail, String> {
}
