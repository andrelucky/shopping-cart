package com.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */
@Entity
@Table(name = "cart")
public class Cart {

    private Cart() {
    }

    public static Cart getInstance(String sessionId) {
        Cart cart = new Cart();
        cart.sessionId = sessionId;
        cart.totalPrice = 0;
        cart.totalPriceActual = 0;
        cart.status = 1;
        cart.createdDate = new Date();
        return cart;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String sessionId;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String address;

    @Email
    private String email;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = true)
    private String discountId;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private double totalPriceActual;

    //0=cancel/deleted 1=active 2=complete
    @NotNull
    @Min(0)
    @Max(2)
    @Column(nullable = false)
    private Integer status;

    private Date createdDate;

    private Date updatedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private List<CartDetail> cartDetails = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getTotalPriceActual() {
        return totalPriceActual;
    }

    public void setTotalPriceActual(double totalPriceActual) {
        this.totalPriceActual = totalPriceActual;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
