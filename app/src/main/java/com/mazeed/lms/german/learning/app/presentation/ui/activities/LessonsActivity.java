package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Grade;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lesson;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.ContentsCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.contents.ContentsPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.contents.ContentsPresenterImp;
import com.mazeed.lms.german.learning.app.presentation.ui.adapters.LessonsAdapter;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnListInteractionListener;
import com.mazeed.lms.german.learning.app.presentation.ui.custom.CustomDividerItemDecoration;
import com.mazeed.lms.german.learning.app.presentation.ui.dialogs.CustomBottomSheetDialogFragment;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListInteractionListener}
 * interface.
 */
public class LessonsActivity extends BaseActivity implements ContentsCallback, OnListInteractionListener<Lesson>, SearchView.OnQueryTextListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.image_search)
    ImageView imageViewSearch;
    @BindView(R.id.image_cancel)
    ImageView imageViewCancel;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.empty_videos)
    RelativeLayout emptyVideos;
    @BindView(R.id.message)
    TextView message;

    private CustomBottomSheetDialogFragment sheet;
    private GridLayoutManager manager;
    private ContentsPresenter presenter;
    private LessonsAdapter adapter;
    private Grade grade;
    private List<Lesson> lessons;
    private List<Lesson> filteredLessons;
    private String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        ButterKnife.bind(this);

        grade = getIntent().getParcelableExtra(Constants.KEY_GRADE);
        presenter = new ContentsPresenterImp(this);

        if (savedInstanceState != null) {
            searchText = savedInstanceState.getCharSequence("searchText").toString();
            searchView.setQuery(searchText, true);
        } else {
            searchText = "";
        }

        lessons = new ArrayList<>();
        lessons = new ArrayList<>();
        filteredLessons = new ArrayList<>();
        adapter = new LessonsAdapter(filteredLessons, this);
        manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(this, R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        presenter.getLessons(grade.getId().intValue());

        refreshLayout.setColorSchemeResources(R.color.refreshColor1, R.color.refreshColor2,
                R.color.refreshColor3, R.color.refreshColor4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getLessons(grade.getId().intValue());
            }
        });

        searchView.setQueryHint(getString(R.string.str_search_videos));
        searchView.setOnQueryTextListener(this);

        setActionBarTitle(grade.getName());
    }

    @OnCheckedChanged({R.id.radio_double, R.id.radio_single})
    public void onChangeMode(CompoundButton button, boolean checked) {
        if (checked) {
            switch (button.getId()) {
                case R.id.radio_double:
                    manager.setSpanCount(2);
                    break;
                case R.id.radio_single:
                    manager.setSpanCount(1);
                    break;
            }
        }
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
    public void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence("searchText", searchText);
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.image_cancel)
    public void onClickCancel() {
        searchView.setQuery("", false);
        showSearch(true);
        hideKeyBoard();
        searchView.clearFocus();
    }

    @OnClick(R.id.search_close_btn)
    public void onClickClose() {
        searchView.setQuery("", false);
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
    public void onGetLessonsComplete(List<Lesson> lessons) {
        this.lessons.clear();
        this.lessons.addAll(lessons);
        applyFilter("");
        searchView.setQuery("", false);
        if (!lessons.isEmpty()) {
            emptyVideos.setVisibility(View.GONE);
        } else {
            emptyVideos.setVisibility(View.VISIBLE);
            message.setText(R.string.str_empty_videos);
        }
    }

    @Override
    public void onListInteraction(Lesson lesson) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtra(Constants.KEY_CONTENT, intent);
        startActivity(intent);
    }

    private void applyFilter(String filter) {
        this.filteredLessons.clear();
        if (!filter.isEmpty()) {
            showSearch(false);
            fillFilterList(filter);
        } else {
            showSearch(true);
            this.filteredLessons.addAll(lessons);
        }
        adapter.notifyDataSetChanged();
    }

    private void showSearch(boolean show) {
        if (show) {
            imageViewCancel.setVisibility(View.INVISIBLE);
            imageViewSearch.setVisibility(View.VISIBLE);
        } else {
            imageViewCancel.setVisibility(View.VISIBLE);
            imageViewSearch.setVisibility(View.INVISIBLE);
        }
    }

    private void fillFilterList(String filter) {
        this.filteredLessons.clear();
        for (Lesson lesson : lessons) {
            if (lesson.getName().toLowerCase().contains(filter.toLowerCase())) {
                filteredLessons.add(lesson);
            }
        }
        if (lessons.isEmpty()) {
            emptyVideos.setVisibility(View.VISIBLE);
            message.setText(R.string.str_empty_videos);
        } else if (filteredLessons.isEmpty()) {
            emptyVideos.setVisibility(View.VISIBLE);
            message.setText(R.string.str_not_matched_videos);
        } else {
            emptyVideos.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        applyFilter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        applyFilter(s);
        return false;
    }
}
