package com.example.direx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.direx.Modal.Item;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAccessoriesActivity extends AppCompatActivity {
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.txtItemDescription)
    EditText txtItemDescription;
    @BindView(R.id.txtItemTitle)
    EditText txtItemTitle;
    @BindView(R.id.txtItemPrice)
    EditText txtItemPrice;
    @BindView(R.id.imgItemImage)
    ImageView imgItemImage;

    Uri imguri,downUri;

    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accessories);

        ButterKnife.bind(this);

        storageRef = FirebaseStorage.getInstance().getReference("Accessories");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = txtItemTitle.getText().toString().trim();
                final String description = txtItemDescription.getText().toString().trim();
                final String price = txtItemPrice.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    txtItemTitle.setError("Title is Required.");
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    txtItemDescription.setError("Description is Required.");
                    return;
                }
                if (TextUtils.isEmpty(price)) {
                    txtItemPrice.setError("Price is Required.");
                    return;
                }
                saveAccessories(title,description,price);
            }
        });

        imgItemImage.setOnClickListener(v -> FileChooser());
        btnCancel.setOnClickListener(v -> finish());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        {
            imguri =data.getData();
            Glide.with(AddAccessoriesActivity.this).load(imguri).into(imgItemImage);
            Toast.makeText(this,imguri.toString(),Toast.LENGTH_SHORT).show();
            FileUploader();

        }
        else Toast.makeText(this, "You Haven't Select Image", Toast.LENGTH_SHORT).show();
    }



    public String  getExtension (Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void FileUploader() {
        StorageReference ref = storageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
        UploadTask uploadTask = ref.putFile(imguri);
        Task<Uri> urlTask = uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }

            return ref.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                 downUri = task.getResult();
            } else {

            }
        });
    }

    public void FileChooser() {
        Intent photoPicker=new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }

    public void saveAccessories(String title,String description,String price) {
        DatabaseReference reviewref = FirebaseDatabase.getInstance().getReference("Accessories");

        String key = reviewref.push().getKey();
        Item item = new Item();
        if (downUri != null){
            item.setImage(downUri.toString());
        }else{
            Toast.makeText(this, "Please wait image is uploading",Toast.LENGTH_SHORT).show();
            return;
        }
        item.setName(title);
        item.setKey(key);
        item.setPrice(price);
        item.setDescription(description);

        reviewref.push().setValue(item)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this,"Item added success",Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this,"Item added fail",Toast.LENGTH_SHORT).show();
                });
    }
}