package com.example.myapppeet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animals;
    private Database database; // Reference to your database class

    private Context context;

    public AnimalAdapter(List<Animal> animals,Database database,Context context) {
        this.animals = animals;
        this.database = database;
        this.context = context;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.animalNameText.setText(animal.getName());
        holder.animalType.setText(animal.getType());
        holder.animalAge.setText(String.valueOf(animal.getAge()));

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditAnimalActivity.class);
            intent.putExtra("ANIMAL_ID", animal.getId());
            intent.putExtra("ANIMAL_NAME", animal.getName());
            intent.putExtra("ANIMAL_TYPE", animal.getType());
            intent.putExtra("ANIMAL_AGE", animal.getAge());
            context.startActivity(intent);
        });


        holder.btnDelete.setOnClickListener(v -> {
            database.deleteAnimal(animal.getId());
            animals.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition(), animals.size());

        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView animalNameText;
        TextView animalType;
        TextView animalAge;
        Button btnEdit, btnDelete;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            animalNameText = itemView.findViewById(R.id.animalName);
            animalType = itemView.findViewById(R.id.animalType);
            animalAge = itemView.findViewById(R.id.AnimalAge);
            btnEdit = itemView.findViewById(R.id.btnEditAnimal);
            btnDelete = itemView.findViewById(R.id.btnDeleteAnimal);
        }
    }
}
