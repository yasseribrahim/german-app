package com.mazeed.lms.german.learning.app.presentation.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnInteractionListener;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.CoordinationTypes;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.CoordinationModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LessonDetailsAdapter extends RecyclerView.Adapter<LessonDetailsAdapter.CoordinationBaseViewHolder> {
    private final List<CoordinationModel<Content>> contents;
    private OnInteractionListener<ArrayList<Content>> listener;

    public LessonDetailsAdapter(List<CoordinationModel<Content>> contents, OnInteractionListener<ArrayList<Content>> listener) {
        this.contents = contents;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return contents.get(position).getType();
    }

    @Override
    public CoordinationBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CoordinationTypes.TYPE_VIDEO:
                return new CoordinationButtonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coordination_header_button, parent, false));
            default:
                return new CoordinationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coordination_header, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final CoordinationBaseViewHolder holder, int position) {
        CoordinationModel<Content> content = this.contents.get(position);
        holder.content = content;
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public abstract class CoordinationBaseViewHolder extends RecyclerView.ViewHolder {

        View view;
        CoordinationModel<Content> content;

        public abstract void bind();

        public CoordinationBaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class CoordinationViewHolder extends CoordinationBaseViewHolder implements View.OnClickListener {
        @BindView(R.id.header)
        TextView header;
        @BindView(R.id.indicator)
        ImageView indicator;
        @BindView(R.id.contents)
        LinearLayout contents;

        public CoordinationViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public void bind() {
            header.setText(content.getTitle());
            if (content.getList() != null && !content.getList().isEmpty()) {
                add(content.getList());
            } else {
                content.setList(Arrays.asList(new Content(view.getResources().getString(R.string.str_empty_data))));
            }
        }

        public void add(List<Content> contents) {
            View tail;
            TextView view;
            LinearLayout.LayoutParams params;
            this.contents.removeAllViews();
            for (Content content : contents) {
                tail = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_coordination_tail, null, false);
                view = tail.findViewById(R.id.tail);
                view.setText(content.getText());
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                view.setOnClickListener(this);
                this.contents.addView(tail, params);
            }
            itemView.setOnClickListener(this);
            this.contents.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            if (contents.getVisibility() == View.VISIBLE) {
                contents.setVisibility(View.GONE);
                indicator.setImageResource(R.drawable.ic_action_arrow_right_gray);
            } else {
                contents.setVisibility(View.VISIBLE);
                indicator.setImageResource(R.drawable.ic_action_arrow_down_gray);
            }
        }
    }

    public class CoordinationButtonViewHolder extends CoordinationBaseViewHolder implements View.OnClickListener {
        @BindView(R.id.header)
        TextView header;

        public CoordinationButtonViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }


        @Override
        public void bind() {
            header.setText(content.getTitle());
        }

        @OnClick(R.id.btn_view)
        public void onClick(View view) {
            listener.onInteraction((ArrayList<Content>) content.getList());
        }
    }
}
