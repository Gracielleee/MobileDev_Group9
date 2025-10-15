package com.example.mobiledev_unifime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class adapter_contactgroup extends RecyclerView.Adapter<adapter_contactgroup.ViewHolder> {

    private List<String> contactGroups;
    private OnItemClickListener listener;
    private int selectedPosition = 0;  // Track selected item (default first item)

    public interface OnItemClickListener {
        void onItemClick(String groupName);
    }

    public adapter_contactgroup(List<String> contactGroups, OnItemClickListener listener) {
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
        String group = contactGroups.get(position);
        holder.textView.setText(group);

        // Change background based on selection
        if (selectedPosition == position) {
            holder.textView.setBackgroundResource(R.drawable.contactgroup_background_selected);
        } else {
            holder.textView.setBackgroundResource(R.drawable.contactgroup_background);
        }

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            // Refresh the previous and new selected items
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            listener.onItemClick(group);
        });
    }

    @Override
    public int getItemCount() {
        return contactGroups.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contactgroup);
        }
    }
}