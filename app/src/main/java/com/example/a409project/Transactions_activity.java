package com.example.a409project;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Transactions_activity extends AppCompatActivity {
    Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inialize evrything
        tools = new Tools();
        tools.InitateDB(this);


        // indents
        int Transaction_id = getIntent().getIntExtra("Transaction_id", -1);

        // Utilisez les données récupérées (par exemple, les afficher dans un Log)
        Log.d("Transactions_activity", "Value1: " + Transaction_id);



        if(Transaction_id != -1){
            Transaction transaction = null;
            for (Transaction t : tools.GetTransactionList(this)) {
                if (t.getId() == Transaction_id) {
                    transaction = t;
                    break;
                }
            }
            if (transaction == null) {
                Log.e("Transaction", "Transaction not found");
                return;
            }
            EditText montant_edit = findViewById(R.id.montant);
            EditText description_edit = findViewById(R.id.Desc);
            EditText date_edit = findViewById(R.id.date);

            montant_edit.setText(String.valueOf(abs(transaction.getAmount())));
            description_edit.setText(transaction.getDescription());
            date_edit.setText(transaction.getDate());

            if (transaction.getAmount() < 0){
                RadioGroup radioGroup = findViewById(R.id.radio_group);
                radioGroup.check(R.id.outcome);
            }
            else {
                RadioGroup radioGroup = findViewById(R.id.radio_group);
                radioGroup.check(R.id.income);
            }


        }

        //Button
        TextView back_t1o_main = findViewById(R.id.main_activity);
        back_t1o_main.setOnClickListener(v -> {
            backToMain(); // Termine l'activité actuelle et revient à MainActivity
        });

        Button submit = findViewById(R.id.Submit);
        submit.setOnClickListener(v -> {
            submitTransa(Transaction_id);
        });


        EditText dateEditText = findViewById(R.id.date);

        // Définir un OnClickListener pour afficher le DatePickerDialog
        dateEditText.setOnClickListener(v -> {
            // Obtenez la date actuelle
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Créez et affichez le DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Transactions_activity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Mettez à jour le champ avec la date sélectionnée FORMAT YYYY-MM-DD
                        String selectedDate = selectedYear + "-" +
                                String.format("%02d", selectedMonth + 1) + "-" +
                                String.format("%02d", selectedDay);
                        dateEditText.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });



    }

    protected double abs(double value) {
        return (value < 0) ? -value : value;
    }

    protected void submitTransa(int Transaction_id){
        EditText montant_edit = findViewById(R.id.montant);
        EditText description_edit = findViewById(R.id.Desc);
        EditText date_edit = findViewById(R.id.date);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        double montant = Double.parseDouble(montant_edit.getText().toString());
        if (selectedId == R.id.outcome) {
            // Do something for the first radio button
            montant = -montant;
        }
        String description = description_edit.getText().toString();
        String date = date_edit.getText().toString();
        if (Transaction_id == -1){
            // We will add a new transaction
            tools.AddTransaction(this, montant, description, date);
        }
        else {
            Transaction transaction = null;
            for (Transaction t : tools.GetTransactionList(this)) {
                if (t.getId() == Transaction_id) {
                    transaction = t;
                    break;
                }
            }
            if (transaction == null) {
                Log.e("Transaction", "Transaction not found");
                return;
            }
            transaction.setAmount(montant);
            transaction.setDescription(description);
            transaction.setDate(date);
            tools.UpdateTransaction(this, transaction);
            Log.i("Transaction", "Transaction updated: " + transaction.getId() + ", Montant: " + transaction.getAmount());
        }


        backToMain();
    }

    protected void backToMain(){
        Toast.makeText(this, "Transaction saved successfully!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
}