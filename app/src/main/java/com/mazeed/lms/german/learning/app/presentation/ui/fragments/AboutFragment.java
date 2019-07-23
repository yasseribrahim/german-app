package com.mazeed.lms.german.learning.app.presentation.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.BuildConfig;
import com.mazeed.lms.german.learning.app.GermanApplication;
import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.presentation.ui.activities.WebViewActivity;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnListInteractionListener;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListInteractionListener}
 * interface.
 */
public class AboutFragment extends BaseFragment {
    @BindView(R.id.buildNumber)
    TextView buildNumber;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.versionNumber)
    TextView versionNumber;
    @BindView(R.id.copy_right)
    TextView copyRight;

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
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
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        versionNumber.setText(getResources().getString(R.string.str_application_version) + BuildConfig.VERSION_NAME);
        Long size = ((GermanApplication) getActivity().getApplication()).getApplicationSize();
        this.size.setText(size.toString() + " KB");
        buildNumber.setText(getResources().getString(R.string.str_application_build) + BuildConfig.APP_BUILD_NUM);
        return view;
    }

    @OnClick(R.id.copy_right)
    public void onCopyRightClicked() {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra(Constants.KEY_URL, BuildConfig.COPY_RIGHT_URL);
        startActivity(intent);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return null;
    }
}
