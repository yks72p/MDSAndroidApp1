package com.example.trafimau_app.activity.welcome_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.trafimau_app.MyApplication;
import com.example.trafimau_app.R;

public class LayoutPickerFragment extends Fragment {

    private MyApplication app;
    private boolean compactLayoutEnabled = false;
    private RadioButton standardLayoutRB;
    private RadioButton compactLayoutRB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        if (activity != null) {
            app = (MyApplication) getActivity().getApplication();
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_welcome_layout_picker, container, false);

        standardLayoutRB = rootView.findViewById(R.id.layoutPickerStandardRadioButton);
        compactLayoutRB = rootView.findViewById(R.id.layoutPickerCompactRadioButton);
        final View standardLayoutBlock = rootView.findViewById(R.id.layoutPickerStandardBlock);
        final View compactLayoutBlock = rootView.findViewById(R.id.layoutPickerCompactBlock);

        standardLayoutBlock.setOnClickListener(
                v -> onRadioButtonBlockClick(v, false));
        compactLayoutBlock.setOnClickListener(
                v -> onRadioButtonBlockClick(v, true));

        compactLayoutEnabled = app.isCompactLayoutEnabled();
        setRadioButtonsState();

        return rootView;
    }

    private void setRadioButtonsState() {
        standardLayoutRB.setChecked(!compactLayoutEnabled);
        compactLayoutRB.setChecked(compactLayoutEnabled);
    }

    private void onRadioButtonBlockClick(View v, boolean compactLayoutClicked) {
        if (compactLayoutClicked == compactLayoutEnabled) {
            return;
        }

        Log.d(MyApplication.LOG_TAG,
                "LayoutPickerFragment.onRadioButtonBlockClick: changing night mode state");

        compactLayoutEnabled = compactLayoutClicked;
        app.setCompactLayoutEnabled(compactLayoutEnabled);
        setRadioButtonsState();
    }
}
