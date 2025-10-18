package com.example.mobiledev_unifime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledev_unifime.model.Contact;

import java.util.List;

public class AdapterContactItem extends RecyclerView.Adapter<AdapterContactItem.ContactViewHolder> {

    private final List<Contact> items;
    private OnContactClickListener listener;
    private final Context context;

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    public AdapterContactItem(Context context, List<Contact> items) {
        this.context = context;
        this.items = items;
    }

    public void setOnContactClickListener(OnContactClickListener listener) {
        this.listener = listener;
    }

    public void updateContacts(List<Contact> newContacts) {
        items.clear();
        items.addAll(newContacts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_contact,
                parent,
                false
        );
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = items.get(position);
        holder.bind(contact, listener);

        // Set onClickListener for the ImageButton
        holder.logButton.setOnClickListener(v -> {
            Toast.makeText(context, "Connected with " + contact.getName() + " today  |  Undo?", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView cardNameTextView;
        private final ImageView cardImageView;

        private final ImageButton logButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNameTextView = itemView.findViewById(R.id.contact_name);
            cardImageView = itemView.findViewById(R.id.contact_profilePicture);
            logButton = itemView.findViewById(R.id.log_btn);
        }

        public void bind(Contact item, OnContactClickListener listener) {
            cardNameTextView.setText(item.getName());
            cardImageView.setImageResource(item.getImageId());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onContactClick(item);
                }
            });
        }
    }
}