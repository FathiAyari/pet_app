// Generated by view binder compiler. Do not edit!
package com.example.myapppeet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapppeet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AnimalItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView AnimalAge;

  @NonNull
  public final TextView animalName;

  @NonNull
  public final TextView animalType;

  @NonNull
  public final Button btnDeleteAnimal;

  @NonNull
  public final Button btnEditAnimal;

  private AnimalItemBinding(@NonNull CardView rootView, @NonNull TextView AnimalAge,
      @NonNull TextView animalName, @NonNull TextView animalType, @NonNull Button btnDeleteAnimal,
      @NonNull Button btnEditAnimal) {
    this.rootView = rootView;
    this.AnimalAge = AnimalAge;
    this.animalName = animalName;
    this.animalType = animalType;
    this.btnDeleteAnimal = btnDeleteAnimal;
    this.btnEditAnimal = btnEditAnimal;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static AnimalItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AnimalItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.animal_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AnimalItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.AnimalAge;
      TextView AnimalAge = ViewBindings.findChildViewById(rootView, id);
      if (AnimalAge == null) {
        break missingId;
      }

      id = R.id.animalName;
      TextView animalName = ViewBindings.findChildViewById(rootView, id);
      if (animalName == null) {
        break missingId;
      }

      id = R.id.animalType;
      TextView animalType = ViewBindings.findChildViewById(rootView, id);
      if (animalType == null) {
        break missingId;
      }

      id = R.id.btnDeleteAnimal;
      Button btnDeleteAnimal = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteAnimal == null) {
        break missingId;
      }

      id = R.id.btnEditAnimal;
      Button btnEditAnimal = ViewBindings.findChildViewById(rootView, id);
      if (btnEditAnimal == null) {
        break missingId;
      }

      return new AnimalItemBinding((CardView) rootView, AnimalAge, animalName, animalType,
          btnDeleteAnimal, btnEditAnimal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}