package com.example.a409project;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView navSettings, navHome, Balance;
    Tools tools;
    TableLayout tableLayout;

    @SuppressLint("SetTextI18n")
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


        Balance = findViewById(R.id.Balance);

        ImageButton change_activity = findViewById(R.id.add_transa);
        EditText transaction_id = null;
        change_activity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Transactions_activity.class);
            startActivityForResult(intent, 1);
            //TODDO : MUST BE CHANGE
            if (false) {
                intent.putExtra("Transaction_id", Integer.parseInt(transaction_id.getText().toString()));
            }
        });

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



        List<Transaction> transactions = tools.GetTransactionList(this);
        Collections.reverse(transactions);


        // Récupérer le conteneur dans le ScrollView
        ScrollView scrollView = findViewById(R.id.Scroll_view);
        tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));

        scrollView.addView(tableLayout);

// Ajouter dynamiquement les transactions
        for (Transaction transaction : transactions) {
            // Créer une nouvelle ligne (TableRow)
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
            tableRow.setPadding(20, 20, 20, 20);

            // Ajouter la date
            TextView dateView = new TextView(this);
            dateView.setText(transaction.getDate());
            dateView.setTextSize(18);
            dateView.setTextColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(dateView);

            // Ajouter la description
            TextView descriptionView = new TextView(this);
            descriptionView.setText(transaction.getDescription());
            descriptionView.setTextSize(18);
            descriptionView.setTextColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(descriptionView);

            // Ajouter le montant
            TextView amountView = new TextView(this);
            amountView.setText(String.format("%.2f €", transaction.getAmount()));
            amountView.setTextSize(18);
            amountView.setTextColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(amountView);

            // Ajouter un OnClickListener pour chaque ligne
            tableRow.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Transactions_activity.class);
                intent.putExtra("Transaction_id", transaction.getId());
                startActivity(intent);
            });

            // Ajouter la ligne au TableLayout
            tableLayout.addView(tableRow);
        }


        UpdateAmount();
    }


    protected void addTransa(Context context, double amount, String description, String date) {
        tools.AddTransaction(context, amount, description, date);
        UpdateAmount();
    }


    public void UpdateAmount() {
        double amount = tools.CountAmount(this);
        Balance.setText(amount + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            UpdateAmount(); // Met à jour le montant
            refreshTransactionList();
        }
    }


    // Méthode pour actualiser la liste des transactions
    private void refreshTransactionList() {
        // Récupérer la liste des transactions
        List<Transaction> transactions = tools.GetTransactionList(this);

        Collections.reverse(transactions);

        // Récupérer le TableLayout


        // Supprimer toutes les lignes existantes
        tableLayout.removeAllViews();

        // Ajouter dynamiquement les transactions
        for (Transaction transaction : transactions) {
            // Créer une nouvelle ligne (TableRow)
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
            tableRow.setPadding(20, 20, 20, 20);

            // Ajouter la date
            TextView dateView = new TextView(this);
            dateView.setText(transaction.getDate());
            dateView.setTextSize(18);
            dateView.setTextColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(dateView);

            // Ajouter la description
            TextView descriptionView = new TextView(this);
            descriptionView.setText(transaction.getDescription());
            descriptionView.setTextSize(18);
            descriptionView.setTextColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(descriptionView);

            // Ajouter le montant
            TextView amountView = new TextView(this);
            amountView.setText(String.format("%.2f €", transaction.getAmount()));
            amountView.setTextSize(18);
            amountView.setTextColor(getResources().getColor(R.color.dark_blue));
            tableRow.addView(amountView);

            // Ajouter un OnClickListener pour chaque ligne
            tableRow.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, Transactions_activity.class);
                intent.putExtra("Transaction_id", transaction.getId());
                startActivity(intent);
            });

            // Ajouter la ligne au TableLayout
            tableLayout.addView(tableRow);
        }
    }
}