package com.squarebaot.newsapp.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squarebaot.newsapp.R;
import com.squarebaot.newsapp.view.NewsDashboardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.squarebaot.newsapp.R.id.apply_button;
import static com.squarebaot.newsapp.R.id.country_list;

public class CountryDialogFragment extends DialogFragment {

    @BindView(country_list)
    RadioGroup countryRadioGroup;

    private NewsDashboardView newsDashboardView;

    public CountryDialogFragment(NewsDashboardView newsDashboardView) {
        this.newsDashboardView = newsDashboardView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_country, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] countries = getActivity().getResources().getStringArray(R.array.location);
        for (String country : countries) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setCompoundDrawables(null, null, getActivity().getResources().getDrawable(android.R.drawable.btn_radio), null);
            radioButton.setText(country);
            countryRadioGroup.addView(radioButton);
        }
    }

    @OnClick(apply_button)
    public void onApplyButtonClick() {
        int id = countryRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = countryRadioGroup.findViewById(id);
        newsDashboardView.onCountrySelect(radioButton.getText().toString());
        dismiss();
    }
}
