package com.example.myapppeet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddAnimalActivity extends AppCompatActivity {

    // Déclaration des vues
    EditText etAnimalName, etAnimalType, etAnimalAge;
    Button btnSaveAnimal, btnBack;

    // Déclaration de l'instance de Database
    Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        // Initialisation des vues
        etAnimalName = findViewById(R.id.etAnimalName);
        etAnimalType = findViewById(R.id.etAnimalType);
        etAnimalAge = findViewById(R.id.etAnimalAge);
        btnSaveAnimal = findViewById(R.id.btnSaveAnimal);
        btnBack = findViewById(R.id.btnBack);

        // Initialisation de la base de données
        dbHelper = new Database(this, "Reclamation Client", null, 1); // Utilisation de la classe Database

        // Gestion du clic sur le bouton "Save Animal"
        btnSaveAnimal.setOnClickListener(v -> {
            String name = etAnimalName.getText().toString();
            String type = etAnimalType.getText().toString();
            String age = etAnimalAge.getText().toString();

            // Vérification des champs vides
            if (name.isEmpty() || type.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Insertion de l'animal dans la base de données
                long result = dbHelper.insertAnimal(name, type, age);
                if (result != -1) {
                    Toast.makeText(this, "Animal enregistré avec succès", Toast.LENGTH_SHORT).show();
                    finish(); // Retour à l'activité précédente
                } else {
                    Toast.makeText(this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Gestion du clic sur le bouton "Back"
        btnBack.setOnClickListener(v -> finish());
    }

}