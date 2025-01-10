package com.example.myapppeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AnimalsActivity extends AppCompatActivity {
    FloatingActionButton fabAddAnimal;

    RecyclerView recyclerView;
    AnimalAdapter animalAdapter;
    TextView noAnimalsMessage;
    List<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        recyclerView = findViewById(R.id.animalsRecyclerView);
        noAnimalsMessage = findViewById(R.id.noAnimalsMessage);
        fabAddAnimal = findViewById(R.id.fabAddAnimal);

        // Get the list of animals from the database
        animals = getAnimalsFromDatabase();
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Set up the RecyclerView with an adapter
        animalAdapter = new AnimalAdapter(animals,db,this);
        recyclerView.setAdapter(animalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Check if there are no animals and show the message
        if (animals.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noAnimalsMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noAnimalsMessage.setVisibility(View.GONE);  // Hide the message
        }
        fabAddAnimal.setOnClickListener(v -> {
            Intent intent = new Intent(AnimalsActivity.this, AddAnimalActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);

        // Reload animals when the activity is resumed
        animals=getAnimalsFromDatabase();
        animalAdapter = new AnimalAdapter(animals,db,this);
        recyclerView.setAdapter(animalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Check if there are no animals and show the message
        if (animals.isEmpty()) {
            recyclerView.setVisibility(View.GONE);  // Hide the RecyclerView
            noAnimalsMessage.setVisibility(View.VISIBLE);  // Show the message
        } else {
            recyclerView.setVisibility(View.VISIBLE);  // Show the RecyclerView
            noAnimalsMessage.setVisibility(View.GONE);  // Hide the message
        }
    }

    // This method retrieves the list of animals from the database
    private List<Animal> getAnimalsFromDatabase() {
        // Example: Replace with actual logic to fetch data from the database
        Database db = new Database(getApplicationContext(), "Reclamation Client", null, 1);
        return db.getAllAnimals();  // This should return a List of Animal objects
    }

}
