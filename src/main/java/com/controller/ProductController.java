package com.controller;

import com.dao.ProductDao;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */

@RestController
@RequestMapping("/api/product")
@Transactional(readOnly = true)
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> findAll(Pageable page) {
        return productDao.findAll(page);
    }
}
