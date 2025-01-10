package com.example.myapppeet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditEventActivity extends AppCompatActivity {


    private EditText eventLabel;
    private Button btnSaveEvent, btnBack;

    private Database database;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        // Initialize views
        eventLabel = findViewById(R.id.eventLabel);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);
        btnBack = findViewById(R.id.btnBack);

        database = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Get the animal details from the intent
        eventId = getIntent().getIntExtra("EVENT_ID", -1);
        String label = getIntent().getStringExtra("EVENT_LABEL");

        // Populate the fields
        eventLabel.setText(label);

        // Save Button Click Listener
        btnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = eventLabel.getText().toString().trim();

                if (label.isEmpty() ) {
                    Toast.makeText(EditEventActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update animal in the database
                database.updateEvent(eventId,label );
                Toast.makeText(EditEventActivity.this, "Event updated successfully", Toast.LENGTH_SHORT).show();
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