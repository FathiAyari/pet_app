package com.example.myapppeet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapppeet.Database;
import com.example.myapppeet.EditNoteActivity;
import com.example.myapppeet.Note;
import com.example.myapppeet.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private Database database;
    private Context context;

    public NoteAdapter(List<Note> notes, Database database, Context context) {
        this.notes = notes;
        this.database = database;
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.subjectText.setText("Subject : "+note.getSubject());
        holder.noteText.setText("Note : "+note.getNote());

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditNoteActivity.class);
            intent.putExtra("NOTE_ID", note.getId());
            intent.putExtra("NOTE_SUBJECT", note.getSubject());
            intent.putExtra("NOTE_CONTENT", note.getNote());
            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            database.deleteNote(note.getId());
            notes.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            notifyItemRangeChanged(holder.getAdapterPosition(), notes.size());
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView subjectText;
        TextView noteText;
        Button btnEdit, btnDelete;

        public NoteViewHolder(View itemView) {
            super(itemView);
            subjectText = itemView.findViewById(R.id.noteSubject);
            noteText = itemView.findViewById(R.id.note);
            btnEdit = itemView.findViewById(R.id.btnEditNote);
            btnDelete = itemView.findViewById(R.id.btnDeleteNote);
        }
    }
}
