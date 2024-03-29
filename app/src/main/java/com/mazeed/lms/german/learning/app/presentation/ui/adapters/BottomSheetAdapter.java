package com.mazeed.lms.german.learning.app.presentation.ui.adapters;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnItemBottomSheetClickCallback;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.BottomSheetItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {
    private final List<BottomSheetItem> items;
    private OnItemBottomSheetClickCallback callback;
    private int selected;

    public BottomSheetAdapter(List<BottomSheetItem> items, int selected) {
        this.items = items;
        this.selected = selected;
    }

    public void setCallback(OnItemBottomSheetClickCallback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_sheet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        holder.content.setText(holder.item.getContent());
        if (holder.item.getId() == selected) {
            holder.selected.setImageDrawable(holder.getIcon(R.drawable.ic_radio_button_checked));
        } else {
            holder.selected.setImageDrawable(holder.getIcon(R.drawable.ic_radio_button_unchecked));
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != callback) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an order has been selected.
                    callback.onClick(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.selected)
        ImageView selected;

        View view;
        BottomSheetItem item;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        public Drawable getIcon(int icon) {
            return ResourcesCompat.getDrawable(view.getContext().getResources(), icon, null);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + content.getText() + "'";
        }
    }
}
