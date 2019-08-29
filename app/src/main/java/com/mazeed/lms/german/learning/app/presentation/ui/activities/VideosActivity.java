package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Content;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lesson;
import com.mazeed.lms.german.learning.app.presentation.ui.adapters.VideosAdapter;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnInteractionListener;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnPlayContentCallback;
import com.mazeed.lms.german.learning.app.presentation.ui.custom.CustomDividerItemDecoration;
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
 * Activities containing this fragment MUST implement the {@link OnInteractionListener}
 * interface.
 */
public class VideosActivity extends BaseActivity implements OnPlayContentCallback, SearchView.OnQueryTextListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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

    private VideosAdapter adapter;
    private List<Content> contents;
    private Lesson lesson;
    private GridLayoutManager manager;
    private List<Content> filteredContents;
    private String searchText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ButterKnife.bind(this);

        setupSupportedActionBar(toolbar);

        if (savedInstanceState != null) {
            searchText = savedInstanceState.getCharSequence("searchText").toString();
            searchView.setQuery(searchText, true);
        } else {
            searchText = "";
            contents = new ArrayList<>();
            filteredContents = new ArrayList<>();
            adapter = new VideosAdapter(filteredContents, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        contents = getIntent().getParcelableArrayListExtra(Constants.KEY_CONTENTS);
        lesson = getIntent().getParcelableExtra(Constants.KEY_LESSON);
        filteredContents = new ArrayList<>();
        load();
        adapter = new VideosAdapter(filteredContents, this);
        manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(this, R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        refreshLayout.setColorSchemeResources(R.color.refreshColor1, R.color.refreshColor2,
                R.color.refreshColor3, R.color.refreshColor4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });

        setActionBarTitle(lesson.getName());

        searchView.setQueryHint(getString(R.string.str_search_videos));
        searchView.setOnQueryTextListener(this);
    }

    private void load() {
        applyFilter("");
        refreshLayout.setRefreshing(false);
    }

    @OnCheckedChanged(R.id.preview_mode)
    public void onChangeMode(CompoundButton button, boolean checked) {
        if (checked) {
            manager.setSpanCount(1);
        } else {
            manager.setSpanCount(2);
        }
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

    public void onPlayContentCallback(Content content) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtra(Constants.KEY_CONTENT, content);
        startActivity(intent);
    }

    private void applyFilter(String filter) {
        this.filteredContents.clear();
        if (!filter.isEmpty()) {
            showSearch(false);
            fillFilterList(filter);
        } else {
            showSearch(true);
            this.filteredContents.addAll(contents);
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
        this.filteredContents.clear();
        for (Content content : contents) {
            if (content.getText().toLowerCase().contains(filter.toLowerCase())) {
                filteredContents.add(content);
            }
        }
        if (contents.isEmpty()) {
            emptyVideos.setVisibility(View.VISIBLE);
            message.setText(R.string.str_empty_videos);
        } else if (filteredContents.isEmpty()) {
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