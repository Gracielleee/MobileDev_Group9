package com.example.mobiledev_unifime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledev_unifime.model.ContactGroup;

import java.util.List;

public class AdapterContactGroup extends RecyclerView.Adapter<AdapterContactGroup.ViewHolder> {

    private List<ContactGroup> contactGroups;
    private OnItemClickListener listener;
    private int selectedPosition = 0;

    public interface OnItemClickListener {
        void onItemClick(ContactGroup group);
    }

    public AdapterContactGroup(List<ContactGroup> contactGroups, OnItemClickListener listener) {
        this.contactGroups = contactGroups;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contactgroup, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (contactGroups != null && position < contactGroups.size()) {
            ContactGroup group = contactGroups.get(position);
            holder.bind(group, listener);

            if (selectedPosition == position) {
                holder.textView.setBackgroundResource(R.drawable.contactgroup_background_selected);
            } else {
                holder.textView.setBackgroundResource(R.drawable.contactgroup_background);
            }
        }
    }

    @Override
    public int getItemCount() {
        return contactGroups != null ? contactGroups.size() : 0;
    }

    public void setSelectedPosition(int position) {
        int previousPosition = selectedPosition;
        selectedPosition = position;
        notifyItemChanged(previousPosition);
        notifyItemChanged(selectedPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contactgroup);
        }

        public void bind(final ContactGroup group, final OnItemClickListener listener) {
            textView.setText(group.getDisplayName());
            itemView.setOnClickListener(v -> {
                setSelectedPosition(getAdapterPosition());
                listener.onItemClick(group); // Pass ContactGroup directly, not String
            });
        }
    }
}