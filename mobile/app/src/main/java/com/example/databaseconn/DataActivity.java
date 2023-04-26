package com.example.databaseconn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {

    private Button copyBtn, backBtn;
    private TextView tvLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        getSupportActionBar().hide();

        copyBtn = findViewById(R.id.CopyBtn);
        backBtn = findViewById(R.id.BackBtn);
        tvLink = findViewById(R.id.tvLink);

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("link", tvLink.getText().toString());
                String msg = "Copied text to clipboard";
                Toast.makeText(DataActivity.this, msg, Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}