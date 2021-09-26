package com.example.direx.Listner;

import com.example.direx.Modal.Item;

import java.util.List;

public interface IItemListner {
    void onItemLoadSuccess(List<Item> items);
    void onItemLoadFail(String message);
}
