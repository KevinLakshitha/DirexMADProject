package com.example.direx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.direx.Adaptar.ItemAdaptar;
import com.example.direx.Listner.ICartListner;
import com.example.direx.Listner.IItemListner;
import com.example.direx.Modal.Cart;
import com.example.direx.Modal.Item;
import com.example.direx.eventBus.MyUpdateCartEvent;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccessoriesActivity extends AppCompatActivity implements IItemListner, ICartListner {
    @BindView(R.id.item_recycle)
    RecyclerView item_recycle;
    @BindView(R.id.main_layout)
    RelativeLayout main_layout;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnCart)
    ImageView btnCart;
    @BindView(R.id.badge)
    NotificationBadge badge;

    IItemListner iitemListner;
    ICartListner icartListner;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class))
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event){
        countCartItems();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);

        init();
        loadItem();

        btnBack.setOnClickListener(v -> finish());
        btnCart.setOnClickListener(v -> startActivity(new Intent(AccessoriesActivity.this,CartActivity.class)));
    }
    public void init(){
        ButterKnife.bind(this);

        iitemListner = this;
        icartListner =this;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        item_recycle.setLayoutManager(linearLayoutManager);
        item_recycle.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));

    }


    private void loadItem() {
        List<Item> items = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Accessories")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                                Item item = itemSnapshot.getValue(Item.class);
                                items.add(item);
                            }
                            iitemListner.onItemLoadSuccess(items);
                        }else{
                            iitemListner.onItemLoadFail("Cant find items");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        iitemListner.onItemLoadFail(error.getMessage());
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        countCartItems();
    }
    private void countCartItems() {
        List<Cart> cartModels = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Cart").child("001")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot : snapshot.getChildren()){
                            Cart cartModel = cartSnapshot.getValue(Cart.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);
                        }
                        icartListner.onCartLoadSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        icartListner.onCartLoadFail(error.getMessage());
                    }
                });
    }
    @Override
    public void onItemLoadSuccess(List<Item> items) {
        ItemAdaptar adapter = new ItemAdaptar(this,items);
        item_recycle.setAdapter(adapter);
    }

    @Override
    public void onItemLoadFail(String message) {
        Snackbar.make(main_layout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<Cart> cart) {
        int cartSum = 0;
        for(Cart cartModal : cart)
            cartSum += cartModal.getQty();

        badge.setNumber(cartSum);
    }

    @Override
    public void onCartLoadFail(String message) {
        Snackbar.make(main_layout,message,Snackbar.LENGTH_LONG).show();
    }
}