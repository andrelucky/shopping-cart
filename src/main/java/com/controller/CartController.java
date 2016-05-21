package com.controller;

import com.dao.CartDao;
import com.dao.CartDetailDao;
import com.dao.DiscountDao;
import com.dao.ProductDao;
import com.exception.DataNotFoundException;
import com.model.Cart;
import com.model.CartDetail;
import com.model.Discount;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */

@RestController
@RequestMapping("/api/cart")
@Transactional(readOnly = true)
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DiscountDao discountDao;

    @RequestMapping(method = RequestMethod.GET)
    public Cart findBySessionId(HttpSession session) {
        Cart cart = cartDao.findBySessionId(session.getId());

        if (cart == null) {
            cart = Cart.getInstance(session.getId());
        }

        return cart;

    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public Cart create(HttpSession session, @RequestBody @Valid CartDetail cartDetail) {
        Cart cart = cartDao.findBySessionId(session.getId());
        Product product = productDao.findOne(cartDetail.getProductId());

        if (cart == null) {
            cart = Cart.getInstance(session.getId());
        } else {
            cart.setUpdatedDate(new Date());
        }

        Double totalPrice = cart.getTotalPrice() + (product.getPrice() * cartDetail.getQty());
        cart.setTotalPrice(totalPrice);
        cart.setTotalPriceActual(totalPrice);
        cartDao.save(cart);

        cartDetail.setPrice(product.getPrice());
        cartDetail.setCartId(cart.getId());
        cartDetail.setCreatedDate(new Date());
        cartDetailDao.save(cartDetail);

        return findBySessionId(session);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional(readOnly = false)
    public Cart delete(HttpSession session, @PathVariable("id") String id) {

        CartDetail cartDetail = cartDetailDao.findOne(id);

        if (cartDetail == null) {
            throw new DataNotFoundException("ID Not found");
        }

        Cart cart = cartDao.findOne(cartDetail.getCartId());

        Double totalPrice = cart.getTotalPrice() - (cartDetail.getPrice() * cartDetail.getQty());
        cart.setTotalPrice(totalPrice);
        cart.setTotalPriceActual(totalPrice);
        cart.setUpdatedDate(new Date());
        cartDao.save(cart);

        cartDetailDao.delete(cartDetail);

        return findBySessionId(session);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public Cart checkout(HttpSession session, @RequestBody @Valid Map map) {

        if (map == null || map.get("cartId") == null || map.get("name") == null || map.get("email") == null || map.get("address") == null) {
            throw new DataNotFoundException("Bad Request");
        }

        Cart cart = cartDao.findOne(map.get("cartId").toString());

        if (cart == null) {
            throw new DataNotFoundException("ID Not found");
        }

        cart.setName(map.get("name").toString());
        cart.setAddress(map.get("address").toString());
        cart.setEmail(map.get("email").toString());
        cart.setStatus(2);

        if (map.get("discountCode") != null) {
            Double totalPrice = cart.getTotalPrice();
            Double discountPrice = 0.0;
            Discount discount = discountDao.findByCode(map.get("discountCode").toString());

            if (discount == null) {
                throw new DataNotFoundException("Discount not found");
            }

            cart.setDiscountId(discount.getId());

            if (discount.getRate() != null) {
                discountPrice = totalPrice * discount.getRate() / 100;
            } else if (discount.getValue() != null) {
                discountPrice = (totalPrice > discount.getValue() ? discount.getValue() : totalPrice);
            }

            cart.setTotalPriceActual(totalPrice - discountPrice);
        }

        cartDao.save(cart);


        return findBySessionId(session);
    }
}
