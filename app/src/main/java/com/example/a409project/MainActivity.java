package com.example.a409project;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView navSettings, navHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inialize evrything
        tools = new Tools();
        tools.InitateDB(this);


        //Button to add a transaction
        Button add_transa = findViewById(R.id.add_transa);
        EditText editText = findViewById(R.id.value);


        Button change_activity = findViewById(R.id.add_transa);
        EditText transaction_id = findViewById(R.id.Transa_a_modifier);
        change_activity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Transactions_activity.class);
            if (!transaction_id.getText().toString().isEmpty()) {
                intent.putExtra("Transaction_id", Integer.parseInt(transaction_id.getText().toString()));
            }


            startActivity(intent);
        });

        add_transa.setOnClickListener(v ->
                addTransa(this, Double.parseDouble(editText.getText().toString()), "Test", "2025-01-01"));

        

        UpdateAmount();
    }



    protected void addTransa(Context context, double amount, String description, String date){
        tools.AddTransaction(context, amount, description, date);
        UpdateAmount();
    }


        navSettings = findViewById(R.id.nav_settings);

        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, settings_activity.class);
                startActivity(intent);
            }
        });

        navHome = findViewById(R.id.nav_home);

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_home.class);
                startActivity(intent);
            }
        });
    }
}