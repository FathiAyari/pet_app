package com.example.myapppeet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText noteSubject, noteContent;
    private Button btnSaveNote, btnBack;

    private Database database;
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Initialize views
        noteSubject = findViewById(R.id.noteSubject);
        noteContent = findViewById(R.id.noteContent);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        btnBack = findViewById(R.id.btnBack);

        database = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Get the note details from the intent
        noteId = getIntent().getIntExtra("NOTE_ID", -1);
        String subject = getIntent().getStringExtra("NOTE_SUBJECT");
        String content = getIntent().getStringExtra("NOTE_CONTENT");

        // Populate the fields
        noteSubject.setText(subject);
        noteContent.setText(content);

        // Save Button Click Listener
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = noteSubject.getText().toString().trim();
                String content = noteContent.getText().toString().trim();

                if (subject.isEmpty() || content.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update note in the database
                database.updateNote(noteId, subject, content);
                Toast.makeText(EditNoteActivity.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
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
