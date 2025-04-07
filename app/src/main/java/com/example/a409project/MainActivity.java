package com.example.a409project;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tools = new Tools();

        tools.InitateDB(this);

        Button add_transa = findViewById(R.id.add_transa);
        EditText editText = findViewById(R.id.value);

        add_transa.setOnClickListener(v ->
                addTransa(this, Double.parseDouble(editText.getText().toString()), "Test", "2025-01-01"));

        

        UpdateAmount();
    }

    protected void addTransa(Context context, double amount, String description, String date){
        tools.AddTransaction(context, amount, description, date);
        UpdateAmount();
    }


    public void UpdateAmount(){
        double amount = tools.CountAmount(this);
        TextView montant = findViewById(R.id.montant);
        montant.setText(amount+"");
    }





}

