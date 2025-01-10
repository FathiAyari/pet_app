package com.example.myapppeet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditAnimalActivity extends AppCompatActivity {

    private EditText etAnimalName, etAnimalType, etAnimalAge;
    private Button btnSaveAnimal, btnBack;

    private Database database;
    private int animalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_animal);

        // Initialize views
        etAnimalName = findViewById(R.id.etAnimalName);
        etAnimalType = findViewById(R.id.etAnimalType);
        etAnimalAge = findViewById(R.id.etAnimalAge);
        btnSaveAnimal = findViewById(R.id.btnSaveAnimal);
        btnBack = findViewById(R.id.btnBack);

        database = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Get the animal details from the intent
        animalId = getIntent().getIntExtra("ANIMAL_ID", -1);
        String name = getIntent().getStringExtra("ANIMAL_NAME");
        String type = getIntent().getStringExtra("ANIMAL_TYPE");
        int age = getIntent().getIntExtra("ANIMAL_AGE", 0);

        // Populate the fields
        etAnimalName.setText(name);
        etAnimalType.setText(type);
        etAnimalAge.setText(String.valueOf(age));

        // Save Button Click Listener
        btnSaveAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etAnimalName.getText().toString().trim();
                String newType = etAnimalType.getText().toString().trim();
                String newAge = etAnimalAge.getText().toString().trim();

                if (newName.isEmpty() || newType.isEmpty() || newAge.isEmpty()) {
                    Toast.makeText(EditAnimalActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update animal in the database
                database.updateAnimal(animalId, newName, newType, Integer.parseInt(newAge));
                Toast.makeText(EditAnimalActivity.this, "Animal updated successfully", Toast.LENGTH_SHORT).show();
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
