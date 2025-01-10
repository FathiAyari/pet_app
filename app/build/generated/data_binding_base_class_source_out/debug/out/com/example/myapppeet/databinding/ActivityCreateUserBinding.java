// Generated by view binder compiler. Do not edit!
package com.example.myapppeet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapppeet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCreateUserBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button backButton;

  @NonNull
  public final Button createUserButton;

  @NonNull
  public final Button deleteUserButton;

  @NonNull
  public final Button listMembersButton;

  @NonNull
  public final EditText passwordInput;

  @NonNull
  public final EditText usernameInput;

  private ActivityCreateUserBinding(@NonNull LinearLayout rootView, @NonNull Button backButton,
      @NonNull Button createUserButton, @NonNull Button deleteUserButton,
      @NonNull Button listMembersButton, @NonNull EditText passwordInput,
      @NonNull EditText usernameInput) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.createUserButton = createUserButton;
    this.deleteUserButton = deleteUserButton;
    this.listMembersButton = listMembersButton;
    this.passwordInput = passwordInput;
    this.usernameInput = usernameInput;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCreateUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCreateUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_create_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCreateUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButton;
      Button backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.createUserButton;
      Button createUserButton = ViewBindings.findChildViewById(rootView, id);
      if (createUserButton == null) {
        break missingId;
      }

      id = R.id.deleteUserButton;
      Button deleteUserButton = ViewBindings.findChildViewById(rootView, id);
      if (deleteUserButton == null) {
        break missingId;
      }

      id = R.id.listMembersButton;
      Button listMembersButton = ViewBindings.findChildViewById(rootView, id);
      if (listMembersButton == null) {
        break missingId;
      }

      id = R.id.passwordInput;
      EditText passwordInput = ViewBindings.findChildViewById(rootView, id);
      if (passwordInput == null) {
        break missingId;
      }

      id = R.id.usernameInput;
      EditText usernameInput = ViewBindings.findChildViewById(rootView, id);
      if (usernameInput == null) {
        break missingId;
      }

      return new ActivityCreateUserBinding((LinearLayout) rootView, backButton, createUserButton,
          deleteUserButton, listMembersButton, passwordInput, usernameInput);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}