package com.example.myapppeet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events;
    private Database database; // Reference to your database class
    private Context context;

    public EventAdapter(List<Event> events, Database database, Context context) {
        this.events = events;
        this.database = database;
        this.context = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = events.get(position);

        holder.eventLabelText.setText("Evenet Label : " + event.getLabel());


        // Set up the Edit button
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditEventActivity.class);
            intent.putExtra("EVENT_ID", event.getId());
            intent.putExtra("EVENT_LABEL", event.getLabel());
            context.startActivity(intent);
        });

        // Set up the Delete button
        holder.btnDelete.setOnClickListener(v -> {
            database.deleteEvent(event.getId()); // Assuming you have a deleteEvent method in the Database class
            events.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition(), events.size());
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventLabelText;
        TextView eventDateText;
        Button btnEdit, btnDelete;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventLabelText = itemView.findViewById(R.id.eventLabel);
            btnEdit = itemView.findViewById(R.id.btnEditEvent);
            btnDelete = itemView.findViewById(R.id.btnDeleteEvent);
        }
    }
}
