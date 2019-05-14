package com.example.g2mdx.ui.fragment.calender;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.g2mdx.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderFragment extends Fragment implements CalenderView {

    @BindView(R.id.saveBTN)
    Button saveBTN;

    @BindView(R.id.beginTV)
    TextView beginTV;

    @BindView(R.id.endTV)
    TextView endTV;

    @BindView(R.id.titleET)
    EditText titleET;

    @BindView(R.id.dateTV)
    TextView dateTV;

    private CalenderPresenter presenter;

    private Calendar calendar;
    private String title = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        initCalender();
        initPresenter();
        initEditText();
    }

    private void initCalender() {
        calendar = Calendar.getInstance();
    }

    private void initPresenter() {
        presenter = new CalenderPresenter(this);
    }

    private void initEditText() {
        titleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title = s.toString();
                presenter.setTitle(title);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.beginTV)
    void setBeginTime() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePicker;
        timePicker = new TimePickerDialog(getContext(), (view, hourOfDay, minute1) -> {
            beginTV.setText((hourOfDay - 12) + ":" + minute1);
            presenter.setStartTime(hourOfDay + ":" + minute1);
        }, hour, minute, false);
        timePicker.setTitle("Select Time");
        timePicker.show();
    }

    @OnClick(R.id.endTV)
    void setEndTime() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePicker;
        timePicker = new TimePickerDialog(getContext(), (view, hourOfDay, minute1) -> {

            endTV.setText((hourOfDay - 12) + ":" + minute1);
            presenter.setEndTime(hourOfDay + ":" + minute1);
        }, hour, minute, false);
        timePicker.setTitle("Select Time");
        timePicker.show();
    }

    @OnClick(R.id.dateTV)
    void setDate() {
        if (getContext() != null) {
            Dialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    presenter.setDay(date);
                    dateTV.setText(date);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                    .get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }
    }


    @Override
    public void enableButton(boolean status) {
        saveBTN.setEnabled(status);
    }

    @OnClick(R.id.saveBTN)
    void getFullDate() {
        presenter.getFullDate();
    }

    @Override
    public void startPhoneCalender(String title, long startDate, long endDate) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", startDate);
        intent.putExtra("endTime", endDate);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
