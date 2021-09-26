package com.example.direx.Adaptar;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.direx.Listner.IrecycleClickListner;
import com.example.direx.Modal.Cart;
import com.example.direx.Modal.Item;
import com.example.direx.R;
import com.example.direx.eventBus.MyUpdateCartEvent;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItemAdaptar extends RecyclerView.Adapter<ItemAdaptar.MyItemHolder> {

    private Context context;
    private List<Item> items ;

    public ItemAdaptar(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyItemHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemHolder holder, int position) {
        Glide.with(context).load(items.get(position).getImage()).into(holder.imgPic);
        holder.txtPrice.setText(new StringBuffer("RS. ").append(items.get(position).getPrice()));
        holder.txtName.setText(new StringBuffer().append(items.get(position).getName()));
        holder.txtdscription.setText(new StringBuffer().append(items.get(position).getDescription()));

        holder.setListner((view, adaptorPosition) -> {
            addToCart(items.get(position));
        });
    }

    public void addToCart(Item item) {

        DatabaseReference cart = FirebaseDatabase.getInstance().getReference("Cart").child("001");

        cart.child(item.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    AlertDialog dialog = new AlertDialog.Builder(context)
                            .setTitle("Item already in cart")
                            .setMessage("Do you want to add another ! " )
                            .setNegativeButton("NO", (dialog1, which) -> dialog1.dismiss())
                            .setPositiveButton("YES", (dialog12, which) -> {


                                Cart cartmodel = snapshot.getValue(Cart.class);
                                Map<String,Object> updateData = new HashMap<>();
                                updateData.put("qty",cartmodel.getQty() + 1);
                                updateData.put("toatalPrice",(cartmodel.getQty() + 1) *Float.parseFloat(cartmodel.getPrice()));

                                cart.child(item.getKey()).updateChildren(updateData)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(context,"Item added to cart",Toast.LENGTH_SHORT).show();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(context,"Item added to cart fail !!",Toast.LENGTH_SHORT).show();
                                        });



                                dialog12.dismiss();
                            }).create();
                    dialog.show();


                }else{
                    Cart cartmodel = new Cart();
                    cartmodel.setKey(item.getKey());
                    cartmodel.setPrice(item.getPrice());
                    cartmodel.setName(item.getName());
                    cartmodel.setImage(item.getImage());
                    cartmodel.setQty(1);
                    cartmodel.setToatalPrice(Float.parseFloat(item.getPrice()));

                    cart.child(item.getKey()).setValue(cartmodel)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(context,"Item added to cart",Toast.LENGTH_SHORT).show();

                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(context,"Item added to cart fail !!",Toast.LENGTH_SHORT).show();
                            });
                }
                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imgPic)
        ImageView imgPic;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtprice)
        TextView txtPrice;
        @BindView(R.id.txtdscription)
        TextView txtdscription;

        IrecycleClickListner irecycleClickListner;

        public void setListner(IrecycleClickListner listner){
            this.irecycleClickListner = listner;
        }

        private Unbinder unbinder;

        public MyItemHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            irecycleClickListner.onRecycleClick(v,getAdapterPosition());
        }
    }
}

