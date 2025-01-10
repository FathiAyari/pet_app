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

public class EditReminderActivity extends AppCompatActivity {

    private EditText reminderSubject, reminderDate;
    private Button btnSaveReminder, btnBack;
    private Calendar calendar;

    private Database database;
    private int reminderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        // Initialize views
        reminderSubject = findViewById(R.id.reminderSubject);
        reminderDate = findViewById(R.id.reminderDate);
        btnSaveReminder = findViewById(R.id.btnSaveReminder);
        btnBack = findViewById(R.id.btnBack);

        database = new Database(getApplicationContext(), "Reclamation Client", null, 1);
        calendar = Calendar.getInstance();

        // Set onClickListener for reminderDate EditText to show DatePickerDialog

        // Get the reminder details from the intent
        reminderId = getIntent().getIntExtra("REMINDER_ID", -1);
        String subject = getIntent().getStringExtra("REMINDER_SUBJECT");
        String date = getIntent().getStringExtra("REMINDER_DATE");

        // Populate the fields with existing values
        reminderSubject.setText(subject);
        reminderDate.setText(date);
        reminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        // Save Reminder Button Click Listener
        btnSaveReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = reminderSubject.getText().toString().trim();
                String date = reminderDate.getText().toString().trim();

                if (subject.isEmpty() || date.isEmpty()) {
                    Toast.makeText(EditReminderActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update reminder in the database
                database.updateReminder(reminderId, subject, date);
                Toast.makeText(EditReminderActivity.this, "Reminder updated successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        });

        // Back Button Click Listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous screen
            }
        });
    }
}
