package com.example.qwerty.app_ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Request_eot_fragment extends Fragment {

    Date dateTo, dateFrom;
    Button submit_request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_eot_request_fragment,container,false);

        final TextView et_date_to, et_date_from;

        Spinner spinner;
        String[] reason_list = {"--Select--","Heavy Rain", "Heavy Snowfall", "Floods", "Landslides", "Others"};

        spinner = view.findViewById(R.id.spinner_drop_down);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getActivity()), android.R.layout.simple_dropdown_item_1line,reason_list);
        spinner.setAdapter(adapter);

        et_date_from = view.findViewById(R.id.et_from_date);
        et_date_to = view.findViewById(R.id.et_to_date);


        submit_request = view.findViewById(R.id.submit_request);
        submit_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateTo.getTime() >= dateFrom.getTime()){



                }
                else{
                    Toast.makeText(getActivity(),"From date must be between To date and project start date ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        et_date_to.setOnClickListener(this::openDatePickerDialog);
        et_date_from.setOnClickListener(this::openDatePickerDialog);
        return view;
    }

    private void openDatePickerDialog(final View v) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()),(view, year, monthOfYear, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            switch (v.getId()) {
                case R.id.et_from_date:
                    ((TextView)v).setText(selectedDate);
                    try {
                        dateFrom = format.parse(selectedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    break;
                case (R.id.et_to_date):
                    ((TextView)v).setText(selectedDate);
                    try {
                        dateTo = format.parse(selectedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }
}
