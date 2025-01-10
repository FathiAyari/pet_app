package com.example.myapppeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EventsActivity extends AppCompatActivity {

    FloatingActionButton fabAddEvent;

    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    TextView noEventsMessage;
    List<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.eventsRecyclerView);
        noEventsMessage = findViewById(R.id.noEventsMessage);
        fabAddEvent = findViewById(R.id.fabAddEvent);

        // Get the list of animals from the database
        events = getEventsFromDatabase();
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Set up the RecyclerView with an adapter
        eventAdapter = new EventAdapter(events,db,this);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if there are no animals and show the message
        if (events.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noEventsMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noEventsMessage.setVisibility(View.GONE);  // Hide the message
        }
        fabAddEvent.setOnClickListener(v -> {
            Intent intent = new Intent(EventsActivity.this, AddEvent.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Reload animals when the activity is resumed
        events=getEventsFromDatabase();
        eventAdapter = new EventAdapter(events,db,this);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Check if there are no animals and show the message
        if (events.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noEventsMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noEventsMessage.setVisibility(View.GONE);  // Hide the message
        }
    }
    private List<Event> getEventsFromDatabase() {
        // Example: Replace with actual logic to fetch data from the database
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);
        return db.getAllEvents();  // This should return a List of Animal objects
    }
}