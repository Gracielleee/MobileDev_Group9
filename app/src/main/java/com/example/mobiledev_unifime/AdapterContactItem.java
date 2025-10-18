package com.example.mobiledev_unifime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledev_unifime.model.Contact;

import java.util.List;

public class AdapterContactItem extends RecyclerView.Adapter<AdapterContactItem.ContactViewHolder> {

    private final List<Contact> items;
    private OnContactClickListener listener;

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    public AdapterContactItem(List<Contact> items) {
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
        if (items != null && position < items.size()) {
            holder.bind(items.get(position), listener);
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView cardNameTextView;
        private final ImageView cardImageView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNameTextView = itemView.findViewById(R.id.contact_name);
            cardImageView = itemView.findViewById(R.id.contact_profilePicture);
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