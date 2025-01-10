package com.example.myapppeet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    // Declare views
    EditText noteSubject, noteContent;
    Button btnSaveNote, btnBack;

    // Declare database instance
    Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Initialize views
        noteSubject = findViewById(R.id.noteSubject);
        noteContent = findViewById(R.id.noteContent);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        btnBack = findViewById(R.id.btnBack);

        // Initialize the database helper
        dbHelper = new Database(this, "Reclamation Client", null, 1); // Use the Database class

        // Handle "Save Note" button click
        btnSaveNote.setOnClickListener(v -> {
            String subject = noteSubject.getText().toString();
            String content = noteContent.getText().toString();

            // Validate the fields to ensure both subject and content are provided
            if (subject.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please fill in both subject and content", Toast.LENGTH_SHORT).show();
            } else {
                // Insert the note into the database with subject and content
                long result = dbHelper.insertNote(subject, content);
                if (result != -1) {
                    Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the current activity and return to the previous one
                } else {
                    Toast.makeText(this, "Error while saving the note", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle "Back" button click to finish the activity
        btnBack.setOnClickListener(v -> finish());
    }
}
