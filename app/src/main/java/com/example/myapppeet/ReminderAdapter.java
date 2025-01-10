package com.example.myapppeet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<Reminder> reminderList;
    private Database dbHelper;
    private Context context;

    public ReminderAdapter(List<Reminder> reminderList, Database dbHelper, Context context) {
        this.reminderList = reminderList;
        this.dbHelper = dbHelper;
        this.context = context;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);
        holder.subject.setText(reminder.getSubject());
        holder.date.setText(reminder.getDate());

        // Edit Reminder button click listener
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditReminderActivity.class);
            intent.putExtra("REMINDER_ID", reminder.getId());
            intent.putExtra("REMINDER_SUBJECT", reminder.getSubject());
            intent.putExtra("REMINDER_DATE", reminder.getDate());
            context.startActivity(intent);
        });

        // Delete Reminder button click listener
        holder.deleteButton.setOnClickListener(v -> {
            dbHelper.deleteReminder(reminder.getId());
            reminderList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public void updateReminderList(List<Reminder> newReminderList) {
        this.reminderList = newReminderList;
        notifyDataSetChanged();
    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder {

        TextView subject, date;
        Button editButton, deleteButton;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.reminderSubject);
            date = itemView.findViewById(R.id.reminderDate);
            editButton = itemView.findViewById(R.id.btnEditReminder);
            deleteButton = itemView.findViewById(R.id.btnDeleteReminder);
        }
    }
}
