package com.example.a409project;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Tools {
    SQLiteDatabaseHelper dbHelper;

    public void InitateDB(Context context){
        dbHelper = new SQLiteDatabaseHelper(context);
        Log.println(Log.INFO, "Database", "Database init OK");
    }

    List<Transaction> list_transa = new ArrayList<Transaction>();

    public int AddTransaction(Context context, double amount, String description, String date){
        try {
            list_transa.add(new Transaction(context, amount, description, date, true));
        } catch (Exception e) {
            Log.println(Log.ERROR, "Transactions", "Erreur lors de l'ajout de la transaction" + e);
            throw new RuntimeException(e);
        }
        return 1;

    }

    int AddTransaction(Context context, int id, double amount, String description, String date){
        try {
            list_transa.add(new Transaction(context, id, amount, description, date, true));
        } catch (Exception e) {
            Log.println(Log.ERROR, "Transactions", "Erreur lors de l'ajout de la transaction" + e);
            throw new RuntimeException(e);
        }
        return 1;
    }

    public double CountAmount(Context context){
        double amount = 0;
        list_transa = dbHelper.getAllTransactions(context);
        for (Transaction t : list_transa) {
            Log.d("Transaction", "ID: " + t.getId() + ", Montant: " + t.getAmount());
            amount += t.getAmount();
        }
        return amount;
    }

    public void UpdateTransaction(Context context, Transaction transaction){
        try {
            dbHelper.updateTransaction(transaction);
        } catch (Exception e) {
            Log.println(Log.ERROR, "Transactions", "Erreur lors de l'ajout de la transaction" + e);
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> GetTransactionList(Context context){
        list_transa = dbHelper.getAllTransactions(context);
        return list_transa;

    }
}
