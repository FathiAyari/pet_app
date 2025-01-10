package com.example.myapppeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    FloatingActionButton fabAddNote;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    TextView noNotesMessage;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        recyclerView = findViewById(R.id.notesRecyclerView);
        noNotesMessage = findViewById(R.id.noNotesMessage);
        fabAddNote = findViewById(R.id.fabAddNote);

        // Get the list of notes from the database
        notes = getNotesFromDatabase();
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Set up the RecyclerView with an adapter
        noteAdapter = new NoteAdapter(notes, db, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if there are no notes and show the message
        if (notes.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noNotesMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noNotesMessage.setVisibility(View.GONE);  // Hide the message
        }

        fabAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Reload notes when the activity is resumed
        notes = getNotesFromDatabase();
        noteAdapter = new NoteAdapter(notes, db, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if there are no notes and show the message
        if (notes.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noNotesMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noNotesMessage.setVisibility(View.GONE);  // Hide the message
        }
    }

    // This method retrieves the list of notes from the database
    private List<Note> getNotesFromDatabase() {
        // Example: Replace with actual logic to fetch data from the database
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);
        return db.getAllNotes();  // This should return a List of Note objects
    }
}
