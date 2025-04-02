package com.example.a409project;

import android.content.Context;

class Transaction {
    private int id;
    private boolean type;
    private double amount;
    private String description;
    private String date;
    private SQLiteDatabaseHelper dbHelper;

    public Transaction(Context context, boolean type, double amount, String description, String date) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
        this.dbHelper = new SQLiteDatabaseHelper(context);
        dbHelper.insertTransaction(this);
    }

    public Transaction(Context context, boolean type, int id, double amount, String description, String date) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
        this.dbHelper = new SQLiteDatabaseHelper(context);
        dbHelper.insertTransaction(this);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public boolean getType() {return type;}
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

    public void setType(boolean type) {
        this.type = type;
        dbHelper.updateTransaction(this);
    }
}