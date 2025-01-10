package com.example.myapppeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReminderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReminderAdapter reminderAdapter;
    private TextView noRemindersMessage;
    private FloatingActionButton fabAddReminder;

    private List<Reminder> reminderList;

    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        recyclerView = findViewById(R.id.remindersRecyclerView);
        noRemindersMessage = findViewById(R.id.noRemindersMessage);
        fabAddReminder = findViewById(R.id.fabAddReminder);

        // Initialize the database helper
        dbHelper = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Get the list of reminders from the database
        reminderList = dbHelper.getAllReminders();

        // Set up the RecyclerView with an adapter
        reminderAdapter = new ReminderAdapter(reminderList, dbHelper, this);
        recyclerView.setAdapter(reminderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if there are no reminders and show the message
        if (reminderList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noRemindersMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noRemindersMessage.setVisibility(View.GONE);  // Hide the message
        }

        // Add Reminder button click listener
        fabAddReminder.setOnClickListener(v -> {
            Intent intent = new Intent(ReminderActivity.this, AddReminderActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload reminders when the activity is resumed
        reminderList = dbHelper.getAllReminders();
        reminderAdapter.updateReminderList(reminderList);

        // Check if there are no reminders and show the message
        if (reminderList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noRemindersMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noRemindersMessage.setVisibility(View.GONE);  // Hide the message
        }
    }
}
