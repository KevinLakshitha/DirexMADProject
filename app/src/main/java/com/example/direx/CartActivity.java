package com.example.direx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.direx.Adaptar.CartAdaptar;
import com.example.direx.Listner.ICartListner;
import com.example.direx.Modal.Cart;
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

public class CartActivity extends AppCompatActivity implements ICartListner {
    @BindView(R.id.recycle_cart)
    RecyclerView recycle_cart;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.btnBack)
    ImageView btnback;
    @BindView(R.id.txtTotal)
    TextView txtTotal;

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
        loadCart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();
        loadCart();
    }
    private void init(){
        ButterKnife.bind(this);

        icartListner =this;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycle_cart.setLayoutManager(linearLayoutManager);
        recycle_cart.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));

        btnback.setOnClickListener(v->finish());
    }
    private void loadCart() {
        List<Cart> cartList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Cart")
                .child("001").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        Cart cartModel = dataSnapshot.getValue(Cart.class);
                        cartModel.setKey(dataSnapshot.getKey());
                        cartList.add(cartModel);
                    }
                    icartListner.onCartLoadSuccess(cartList);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Cart empty add items!",
                            Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                icartListner.onCartLoadFail(error.getMessage());
            }
        });
    }

    @Override
    public void onCartLoadSuccess(List<Cart> cart) {
        double sum =0;
        for(Cart cartModal : cart){
            sum += cartModal.getToatalPrice();
        }
        txtTotal .setText(new StringBuffer("Total cost : Rs. ").append(sum));
        CartAdaptar adapter = new CartAdaptar(this,cart,this);
        recycle_cart.setAdapter(adapter);
    }

    @Override
    public void onCartLoadFail(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
}
