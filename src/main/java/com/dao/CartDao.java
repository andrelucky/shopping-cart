package com.dao;

import com.model.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */
public interface CartDao extends CrudRepository<Cart, String> {
    Cart findBySessionId(String sessionId);
}
