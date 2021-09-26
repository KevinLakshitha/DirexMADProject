package com.example.direx.Listner;

import com.example.direx.Modal.Cart;

import java.util.List;

public interface ICartListner {
    void onCartLoadSuccess(List<Cart> cart);
    void onCartLoadFail(String message);
}
