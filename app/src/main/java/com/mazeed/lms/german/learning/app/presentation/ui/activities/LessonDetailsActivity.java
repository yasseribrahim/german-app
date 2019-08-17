package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lesson;
import com.mazeed.lms.german.learning.app.domain.models.contents.LessonDetails;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.ContentsCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.contents.ContentsPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.contents.ContentsPresenterImp;
import com.mazeed.lms.german.learning.app.presentation.ui.adapters.LessonDetailsAdapter;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnInteractionListener;
import com.mazeed.lms.german.learning.app.presentation.ui.custom.CustomDividerItemDecoration;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.CoordinationTypes;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.CoordinationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonDetailsActivity extends BaseActivity implements ContentsCallback, OnInteractionListener<ArrayList<Content>> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.message)
    TextView message;

    private ContentsPresenter presenter;
    private LessonDetailsAdapter adapter;
    private List<CoordinationModel<Content>> contents;
    private Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        lesson = getIntent().getParcelableExtra(Constants.KEY_LESSON);
        presenter = new ContentsPresenterImp(this);

        contents = new ArrayList<>();
        adapter = new LessonDetailsAdapter(contents, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(this, R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        presenter.getLessonDetails(lesson.getId().intValue());

        refreshLayout.setColorSchemeResources(R.color.refreshColor1, R.color.refreshColor2,
                R.color.refreshColor3, R.color.refreshColor4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getLessonDetails(lesson.getId().intValue());
            }
        });

        setActionBarTitle(lesson.getName());
    }

    @Override
    public void onShowProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void onHideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return recyclerView;
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onGetLessonDetailsComplete(LessonDetails details) {
        this.contents.clear();
        details.prepare();
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_videos, details.getContents().getVideo().size()), details.getContents().getVideo(), CoordinationTypes.TYPE_VIDEO));
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_exams), details.getContents().getExam(), CoordinationTypes.TYPE_TEXTUAL));
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_explain), details.getContents().getExplain(), CoordinationTypes.TYPE_TEXTUAL));
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_situations), details.getContents().getSituations(), CoordinationTypes.TYPE_TEXTUAL));
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_text_messages), details.getContents().getTextMessage(), CoordinationTypes.TYPE_TEXTUAL));
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_topics_expression), details.getContents().getTopicsExpression(), CoordinationTypes.TYPE_TEXTUAL));
        this.contents.add(new CoordinationModel<>(getString(R.string.content_type_words), details.getContents().getWord(), CoordinationTypes.TYPE_TEXTUAL));
        if (!details.isEmpty()) {
            empty.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.VISIBLE);
            message.setText(R.string.str_empty_data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onInteraction(ArrayList<Content> item) {
        if (!item.isEmpty()) {
            Intent intent = new Intent(this, VideosActivity.class);
            intent.putExtra(Constants.KEY_LESSON, lesson);
            intent.putParcelableArrayListExtra(Constants.KEY_CONTENTS, item);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.str_empty_videos, Toast.LENGTH_LONG).show();
        }
    }
}