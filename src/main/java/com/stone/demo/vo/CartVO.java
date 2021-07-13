package com.stone.demo.vo;

import lombok.Data;

import java.util.List;

public class CartVO {
    /**
     * 购物项
     */
    private List<CartItemVO> cartItems;

    /**
     * 购物车总价格
     */
    private double totalAmount;

    /**
     * 总价格
     * @return
     */
    public double getTotalAmount() {
        return cartItems.stream().mapToDouble(CartItemVO::getTotalPrice).sum();
    }


    public List<CartItemVO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemVO> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


}
