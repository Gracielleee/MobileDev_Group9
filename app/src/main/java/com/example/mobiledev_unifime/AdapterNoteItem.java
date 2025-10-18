package com.example.mobiledev_unifime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledev_unifime.R;
import com.example.mobiledev_unifime.model.Note;

import java.util.List;

public class AdapterNoteItem extends RecyclerView.Adapter<AdapterNoteItem.NoteViewHolder> {
    private final List<Note> notes;
    private final Context context;

    public AdapterNoteItem(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);

        holder.titleEditView.setText(note.getTitle());
        holder.descEditView.setText(note.getDescription());
        holder.contactImageView.setImageResource(note.getImageId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ActivityNoteView.class);
            intent.putExtra("TITLE", note.getTitle());
            intent.putExtra("DESC", note.getDescription());
            intent.putExtra("IMAGE", note.getImageId());
            intent.putExtra("Tag", note.getTag());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleEditView;
        TextView descEditView;
        ImageView contactImageView;
        Button tagButton;

        NoteViewHolder(View itemView) {
            super(itemView);
            titleEditView = itemView.findViewById(R.id.note_item_title);
            descEditView = itemView.findViewById(R.id.note_item_desc);
            contactImageView = itemView.findViewById(R.id.note_item_contact_img);
            tagButton = itemView.findViewById(R.id.note_item_tag);
        }
    }
}
