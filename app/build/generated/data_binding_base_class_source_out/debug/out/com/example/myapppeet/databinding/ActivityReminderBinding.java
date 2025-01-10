// Generated by view binder compiler. Do not edit!
package com.example.myapppeet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapppeet.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityReminderBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final FloatingActionButton fabAddReminder;

  @NonNull
  public final TextView noRemindersMessage;

  @NonNull
  public final RecyclerView remindersRecyclerView;

  private ActivityReminderBinding(@NonNull RelativeLayout rootView,
      @NonNull FloatingActionButton fabAddReminder, @NonNull TextView noRemindersMessage,
      @NonNull RecyclerView remindersRecyclerView) {
    this.rootView = rootView;
    this.fabAddReminder = fabAddReminder;
    this.noRemindersMessage = noRemindersMessage;
    this.remindersRecyclerView = remindersRecyclerView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityReminderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityReminderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_reminder, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityReminderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fabAddReminder;
      FloatingActionButton fabAddReminder = ViewBindings.findChildViewById(rootView, id);
      if (fabAddReminder == null) {
        break missingId;
      }

      id = R.id.noRemindersMessage;
      TextView noRemindersMessage = ViewBindings.findChildViewById(rootView, id);
      if (noRemindersMessage == null) {
        break missingId;
      }

      id = R.id.remindersRecyclerView;
      RecyclerView remindersRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (remindersRecyclerView == null) {
        break missingId;
      }

      return new ActivityReminderBinding((RelativeLayout) rootView, fabAddReminder,
          noRemindersMessage, remindersRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}