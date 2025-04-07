package com.example.a409project;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

class Transaction {
    private int id;
    private boolean type;
    private double amount;
    private String description;
    private String date;
    private SQLiteDatabaseHelper dbHelper;

    public Transaction(Context context, double amount, String description, String date, boolean toInsert) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.dbHelper = new SQLiteDatabaseHelper(context);
        if(toInsert){
            dbHelper.insertTransaction(this);
        }
    }

    public Transaction(Context context, int id, double amount, String description, String date, boolean toInsert) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.dbHelper = new SQLiteDatabaseHelper(context);
        if(toInsert){
            dbHelper.insertTransaction(this);
        }

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getDate() { return date; }

    public void setAmount(double amount) {
        this.amount = amount;
        dbHelper.updateTransaction(this);
    }

    public void setDescription(String description) {
        this.description = description;
        dbHelper.updateTransaction(this);
    }

    public void setDate(String date) {
        this.date = date;
        dbHelper.updateTransaction(this);
    }




}