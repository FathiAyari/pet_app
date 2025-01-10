package com.example.myapppeet;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity {

    private EditText reminderSubject, reminderDate;
    private Button btnSaveReminder, btnBack;
    private Calendar calendar;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder); // Use your layout here

        // Initialize views
        reminderSubject = findViewById(R.id.reminderSubject);
        reminderDate = findViewById(R.id.reminderDate);
        btnSaveReminder = findViewById(R.id.btnSaveReminder);
        btnBack = findViewById(R.id.btnBack);
        // Initialize the database helper
        database = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Initialize calendar
        calendar = Calendar.getInstance();

        // Set onClickListener for reminderDate EditText to show DatePickerDialog
        reminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Format the date as YYYY-MM-DD
                        String selectedDate = String.format("%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                        reminderDate.setText(selectedDate); // Set the selected date to the EditText
                    }
                }, year, month, day);

                datePickerDialog.show(); // Show the DatePickerDialog
            }
        });

        btnSaveReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = reminderSubject.getText().toString().trim();
                String date = reminderDate.getText().toString().trim();

                if (subject.isEmpty() || date.isEmpty()) {
                    Toast.makeText(AddReminderActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert reminder into the database
                long result = database.insertReminder(subject, date);
                if (result != -1) {
                    Toast.makeText(AddReminderActivity.this, "Reminder saved successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    Toast.makeText(AddReminderActivity.this, "Error saving reminder", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back Button listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to previous screen
            }
        });
    }
}
