package com.direx.direxcamerarent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class DeleteLens extends AppCompatActivity {

    EditText lensid;
    Button deletelens;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_lens);

        lensid = findViewById(R.id.etDeletelens);
        deletelens = findViewById(R.id.btnDeletelens);

        deletelens.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Lensid = lensid.getText().toString().trim();
                if (!Lensid.isEmpty()){
                    deleteData(Lensid);
                }else{
                    Toast.makeText(DeleteLens.this,"Please Enter DroneID",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void deleteData(String lensid) {
        reference = FirebaseDatabase.getInstance().getReference("drone").child(lensid);
        reference.removeValue();
        Toast.makeText(DeleteLens.this,"Successfully Deleted.",Toast.LENGTH_SHORT).show();
    }
}