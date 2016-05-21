package com.controller;

import com.dao.DiscountDao;
import com.model.Discount;
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
@RequestMapping("/api/discount")
@Transactional(readOnly = true)
public class DiscountController {

    @Autowired
    private DiscountDao discountDao;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Discount> findAll(Pageable page) {
        return discountDao.findAll(page);
    }
}
