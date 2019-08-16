package com.mazeed.lms.german.learning.app.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.contents.Grade;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.ContentsCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.contents.ContentsPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.contents.ContentsPresenterImp;
import com.mazeed.lms.german.learning.app.presentation.ui.activities.LessonsActivity;
import com.mazeed.lms.german.learning.app.presentation.ui.activities.VideoPlayerActivity;
import com.mazeed.lms.german.learning.app.presentation.ui.adapters.GradesAdapter;
import com.mazeed.lms.german.learning.app.presentation.ui.adapters.VideosAdapter;
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
public class GradesFragment extends BaseFragment implements ContentsCallback, OnListInteractionListener<Grade>, SearchView.OnQueryTextListener {
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
    private GradesAdapter adapter;
    private List<Grade> grades;
    private List<Grade> filteredGrades;
    private String searchText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GradesFragment() {
    }

    public static GradesFragment newInstance() {
        GradesFragment fragment = new GradesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
        ButterKnife.bind(this, view);

        presenter = new ContentsPresenterImp(this);

        if (savedInstanceState != null) {
            searchText = savedInstanceState.getCharSequence("searchText").toString();
            searchView.setQuery(searchText, true);
        } else {
            searchText = "";
        }

        grades = new ArrayList<>();
        grades = new ArrayList<>();
        filteredGrades = new ArrayList<>();
        adapter = new GradesAdapter(filteredGrades, this);
        manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(getContext(), R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        presenter.getAllGrades();

        refreshLayout.setColorSchemeResources(R.color.refreshColor1, R.color.refreshColor2,
                R.color.refreshColor3, R.color.refreshColor4);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAllGrades();
            }
        });

        return view;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchView.setQueryHint(getString(R.string.str_search_videos));
        searchView.setOnQueryTextListener(this);
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
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroyView();
    }

    @Override
    public void onGetGradesComplete(List<Grade> grades) {
        this.grades.clear();
        this.grades.addAll(grades);
        applyFilter("");
        searchView.setQuery("", false);
        if (!grades.isEmpty()) {
            emptyVideos.setVisibility(View.GONE);
        } else {
            emptyVideos.setVisibility(View.VISIBLE);
            message.setText(R.string.str_empty_videos);
        }
    }

    @Override
    public void onListInteraction(Grade grade) {
        Intent intent = new Intent(getContext(), LessonsActivity.class);
        intent.putExtra(Constants.KEY_GRADE, grade);
        startActivity(intent);
    }

    private void applyFilter(String filter) {
        this.filteredGrades.clear();
        if (!filter.isEmpty()) {
            showSearch(false);
            fillFilterList(filter);
        } else {
            showSearch(true);
            this.filteredGrades.addAll(grades);
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
        this.filteredGrades.clear();
        for (Grade grade : grades) {
            if (grade.getName().toLowerCase().contains(filter.toLowerCase())) {
                filteredGrades.add(grade);
            }
        }
        if (grades.isEmpty()) {
            emptyVideos.setVisibility(View.VISIBLE);
            message.setText(R.string.str_empty_videos);
        } else if (filteredGrades.isEmpty()) {
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
