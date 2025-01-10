package com.example.myapppeet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edNom, edMotdepasse;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences("Share_prefs", Context.MODE_PRIVATE);
        String loggedInName = sharedPreferences.getString("Nom", null);

        if (loggedInName != null) {
            // User is already logged in, redirect to ChoixActivity
            Intent intent = new Intent(LoginActivity.this, ChoixActivity.class);
            intent.putExtra("userName", loggedInName); // Optional: Pass the name
            startActivity(intent);
            finish(); // Finish LoginActivity to prevent back navigation
            return;
        }

        setContentView(R.layout.activity_login);

        edNom = findViewById(R.id.editTextTextPersonName);
        edMotdepasse = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.AjouterClient);
        tv = findViewById(R.id.textView3);

        // Handle Login Button Click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nom = edNom.getText().toString();
                String Motdepasse = edMotdepasse.getText().toString();
                Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

                if (Nom.length() == 0 || Motdepasse.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les détails", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(Nom, Motdepasse) == 1) {
                        Toast.makeText(getApplicationContext(), "Connexion réussie", Toast.LENGTH_SHORT).show();

                        // Save the user's name in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Nom", Nom);
                        editor.apply();

                        // Redirect to ChoixActivity
                        Intent intent = new Intent(LoginActivity.this, ChoixActivity.class);
                        intent.putExtra("userName", Nom); // Optional: Pass the name
                        startActivity(intent);
                        finish(); // Finish LoginActivity
                    } else {
                        Toast.makeText(getApplicationContext(), "Nom ou mot de passe invalide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Handle Register TextView Click
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
