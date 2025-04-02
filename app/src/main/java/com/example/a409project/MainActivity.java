package com.example.a409project;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabaseHelper dbHelper;

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
        dbHelper = new SQLiteDatabaseHelper(this);


        Transaction transaction = new Transaction(this, true, 100.0, "Achat", "2025-04-02");

        UpdateAmount();


    }

    protected double CountAmount(){
        double amount = 0;
        List<Transaction> transactions = dbHelper.getAllTransactions(this);
        for (Transaction t : transactions) {
            Log.d("Transaction", "ID: " + t.getId() + ", Montant: " + t.getAmount());
            if (t.getType() == false) {
                amount -= t.getAmount();

            } else {
                amount += t.getAmount();
            }
        }
        return amount;
    }
    protected void UpdateAmount(){
        TextView text = findViewById(R.id.montant);
        text.setText(CountAmount()+"");
    }
}

