package com.mazeed.lms.german.learning.app.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.video.Video;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnPlayVideoCallback;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.DatesUtils;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private final List<Video> videos;
    private final OnPlayVideoCallback callback;

    public VideosAdapter(List<Video> videos, OnPlayVideoCallback callback) {
        this.videos = videos;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.video = videos.get(position);
        holder.title.setText(holder.video.getName());
        holder.date.setText(DatesUtils.formatDateOnly(Calendar.getInstance().getTime()));

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != callback) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an order has been selected.
                    callback.onPlayVideoCallback(holder.video);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.indicator)
        ImageView indicator;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.play)
        ImageView play;

        View view;
        Video video;

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
