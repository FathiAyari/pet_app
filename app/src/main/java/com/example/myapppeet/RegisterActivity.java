package com.example.myapppeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edNom, edEmail, edMotdepasse, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edNom = findViewById(R.id.editTextTextRegName);
        edEmail = findViewById(R.id.editTextTextRegEmail);
        edMotdepasse = findViewById(R.id.editTextTextRegPassword);
        edConfirm = findViewById(R.id.editTextTextRegConfirm);
        btn = findViewById(R.id.RegButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nom = edNom.getText().toString();
                String Email = edEmail.getText().toString();
                String Motdepasse =edMotdepasse.getText().toString();
                String Confirm =edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

                if (Nom.length() == 0 || Email.length() == 0 || Motdepasse.length() == 0 || Confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les détails", Toast.LENGTH_SHORT).show();
                } else {
                    if (Motdepasse.compareTo(Confirm) == 0) {
                        if (isValid(Motdepasse)) {
                            db.register(Nom, Email, Motdepasse);
                            Toast.makeText(getApplicationContext(), "Enregistrement réussi", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Le mot de passe doit contenir au moins 8 caractères, des lettres, des chiffres et des caractères spéciaux", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Méthode pour valider le mot de passe
    public static boolean isValid(String Motdepasse) {
        int f1 = 0, f2 = 0, f3 = 0;

        // Vérification de la longueur du mot de passe
        if (Motdepasse.length() < 8) {
            return false;
        } else {
            // Vérification des caractères alphabétiques
            for (int p = 0; p < Motdepasse.length(); p++) {
                if (Character.isLetter(Motdepasse.charAt(p))) {
                    f1 = 1;
                }
            }

            // Vérification des chiffres
            for (int r = 0; r < Motdepasse.length(); r++) {
                if (Character.isDigit(Motdepasse.charAt(r))) {
                    f2 = 1;
                }
            }

            // Vérification des caractères spéciaux
            for (int s = 0; s < Motdepasse.length(); s++) {
                char c = Motdepasse.charAt(s);
                if ((c >= 33 && c <= 46) || c == 64) {
                    f3 = 1;
                }
            }

            // Retourne true si toutes les conditions sont remplies
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }
}