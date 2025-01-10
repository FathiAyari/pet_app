package com.example.myapppeet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddEvent extends AppCompatActivity {

    // Déclaration des vues
    EditText eventLabel;
    Button btnSaveEvent, btnBack;

    // Déclaration de l'instance de Database
    Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // Initialisation des vues
        eventLabel = findViewById(R.id.eventLabel);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);
        btnBack = findViewById(R.id.btnBack);

        // Initialisation de la base de données
        dbHelper = new Database(this, "Reclamation Client", null, 1); // Utilisation de la classe Database

        // Gestion du clic sur le bouton "Save Animal"
        btnSaveEvent.setOnClickListener(v -> {
            String label = eventLabel.getText().toString();

            // Vérification des champs vides
            if (label.isEmpty() ) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Insertion de l'animal dans la base de données
                long result = dbHelper.insertEvent(label);
                if (result != -1) {
                    Toast.makeText(this, "Evenement enregistré avec succès", Toast.LENGTH_SHORT).show();
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