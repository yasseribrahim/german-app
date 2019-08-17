package com.mazeed.lms.german.learning.app.presentation.ui.adapters;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnPlayContentCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private final List<Content> contents;
    private final OnPlayContentCallback callback;

    public VideosAdapter(List<Content> contents, OnPlayContentCallback callback) {
        this.contents = contents;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.content = contents.get(position);
        holder.title.setText(holder.content.getText());
        Glide.with(holder.itemView.getContext())
                .load(holder.content.getImage())
                .placeholder(ResourcesCompat.getDrawable(holder.itemView.getContext().getResources(), R.drawable.default_image, null))
                .error(ResourcesCompat.getDrawable(holder.itemView.getContext().getResources(), R.drawable.default_image, null))
                .dontAnimate()
                .into(holder.image);
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != callback) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an order has been selected.
                    callback.onPlayContentCallback(holder.content);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.play)
        ImageView play;

        View view;
        Content content;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
