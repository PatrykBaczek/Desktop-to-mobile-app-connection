package com.example.databaseconn;

import static com.example.databaseconn.R.color.cyberblue;
import static com.example.databaseconn.R.color.cyberbrown;
import static com.example.databaseconn.R.color.gray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private Button btnTrue, btnFalse, btnActivity;
    protected boolean permission = false;

    private String uid = "1";
    private String path = "users/" + uid + "/message/permission";

    FirebaseDatabase users = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = users.getReference(path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFalse = findViewById(R.id.btnFalse);
        btnActivity = findViewById(R.id.btnActivity);
        btnTrue = findViewById(R.id.btnTrue);

        getSupportActionBar().hide();

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean value = snapshot.getValue(Boolean.class);
                Log.d("Value: ", "Value of bool: " + value);

                if(value) {
                    permission = true;
                    btnTrue.setEnabled(false);
                    btnTrue.setBackgroundColor(getResources().getColor(gray));
                }else{
                    permission = false;
                    btnFalse.setEnabled(false);
                    btnFalse.setBackgroundColor(getResources().getColor(gray));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Value: ", "onCancelled: ", error.toException());
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValOfBool(true);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValOfBool(false);
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });
    }
    private void ValOfBool(boolean value){
        if(value == true) {
            permission = true;
            btnTrue.setEnabled(false);
            btnTrue.setBackgroundColor(getResources().getColor(gray));
            btnFalse.setEnabled(true);
            btnFalse.setBackgroundColor(getResources().getColor(cyberbrown));
            usersRef.setValue(permission);
        }else {
            permission = false;
            btnFalse.setEnabled(false);
            btnFalse.setBackgroundColor(getResources().getColor(gray));
            btnTrue.setEnabled(true);
            btnTrue.setBackgroundColor(getResources().getColor(cyberbrown));
            usersRef.setValue(permission);
        }
    }
}
