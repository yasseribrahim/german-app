package com.mazeed.lms.german.learning.app.presentation.ui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.presentation.ui.communicator.OnItemBottomSheetClickCallback;
import com.mazeed.lms.german.learning.app.presentation.ui.adapters.BottomSheetAdapter;
import com.mazeed.lms.german.learning.app.presentation.ui.custom.CustomDividerItemDecoration;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.models.BottomSheetItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yasser.ibrahim on 5/13/2018.
 */

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment implements OnItemBottomSheetClickCallback {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private BottomSheetAdapter adapter;
    private List<BottomSheetItem> items;
    private OnItemBottomSheetClickCallback callback;

    public static CustomBottomSheetDialogFragment newInstance(String title, ArrayList<BottomSheetItem> items) {
        CustomBottomSheetDialogFragment fragment = new CustomBottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TITLE, title);
        bundle.putParcelableArrayList(Constants.KEY_BOTTOM_SHEET_LIST, items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        ButterKnife.bind(this, view);

        items = getArguments().getParcelableArrayList(Constants.KEY_BOTTOM_SHEET_LIST);
        String title = getArguments().getString(Constants.KEY_TITLE);
        this.title.setText(title);

        adapter = new BottomSheetAdapter(items);
        adapter.setCallback(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CustomDividerItemDecoration dividerItemDecoration = new CustomDividerItemDecoration(getContext(), R.dimen.divider_mid);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemBottomSheetClickCallback) {
            callback = (OnItemBottomSheetClickCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onClick(BottomSheetItem item) {
        if (callback != null) {
            callback.onClick(item);
        }
    }
}
