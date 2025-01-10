package com.example.myapppeet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        editTextNote = findViewById(R.id.editTextNote);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = editTextNote.getText().toString();
                if (!note.isEmpty()) {
                    // Afficher un message de confirmation
                    Toast.makeText(StatisticsActivity.this, "Note saved!", Toast.LENGTH_SHORT).show();
                    // Effacer le champ de texte après enregistrement
                    editTextNote.setText("");
                } else {
                    Toast.makeText(StatisticsActivity.this, "Please enter a note.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}