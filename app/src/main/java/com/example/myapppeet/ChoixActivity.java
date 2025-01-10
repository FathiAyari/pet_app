package com.example.myapppeet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        Button btnAnimals = findViewById(R.id.btnAnimals);
        Button btnEvents = findViewById(R.id.btnEvents);
        Button btnReminders = findViewById(R.id.btnReminders);
        Button btnStatistics = findViewById(R.id.btnStatistics);
        Button btnLogOut = findViewById(R.id.btnLogOut); // Log Out button

        // Redirection vers AddAnimalActivity
        btnAnimals.setOnClickListener(v -> {
            Intent intent = new Intent(ChoixActivity.this, AnimalsActivity.class);
            startActivity(intent);
        });

        // Redirection vers EventsActivity
        btnEvents.setOnClickListener(v -> {
            Intent intent = new Intent(ChoixActivity.this, EventsActivity.class);
            startActivity(intent);
        });

        // Redirection vers RemindersActivity
        btnReminders.setOnClickListener(v -> {
            Intent intent = new Intent(ChoixActivity.this, ReminderActivity.class);
            startActivity(intent);
        });

        btnStatistics.setOnClickListener(v -> {
            Intent intent = new Intent(ChoixActivity.this, NoteActivity.class);
            startActivity(intent);
        });

        // Log Out functionality
        btnLogOut.setOnClickListener(v -> {
            // Clear the stored user login data from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("Share_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // Clear all saved data
            editor.apply(); // Apply changes

            // Redirect to LoginActivity
            Intent intent = new Intent(ChoixActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish ChoixActivity to prevent returning to it
        });
    }
}
