package com.example.qwerty.app_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Home_fragment extends Fragment {

    TextView tv_no_of_days, et_project_id, et_project_name, et_contractor_name, et_project_location, et_start_date, et_end_date;
    String url = "http://thisisnow.tk/ters/getprojectdata.php?projectid=MORTH000111&pass=root@123";

    Button btn_eot_history;
    RequestQueue queue;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        queue = MySingleton.getInstance(getActivity()).getRequestQueue();

        btn_eot_history = view.findViewById(R.id.btn_eot_history);

        et_project_id = view.findViewById(R.id.et_project_id);
        et_project_name = view.findViewById(R.id.et_project_name);
        et_contractor_name = view.findViewById(R.id.et_contractor_name);
        et_project_location = view.findViewById(R.id.et_project_location);
        et_start_date = view.findViewById(R.id.et_start_date);
        et_end_date = view.findViewById(R.id.et_end_date);
        tv_no_of_days = view.findViewById(R.id.tv_no_of_days);

        btn_eot_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Fragment fragment = new Eot_history_fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_view, fragment).commit();
            }
        });

        dataparse();

        return view;
    }

    public void dataparse(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            try {
                JSONObject object_data = response.getJSONObject("data");

                    String projectid = object_data.getString("projectID");

                JSONObject projectdescription = object_data.getJSONObject("projectDescription");
                    String projectname = projectdescription.getString("projectName");
                    String remainingdays = projectdescription.getString("remainingDays");
                    String description = projectdescription.getString("description");
                    String startdate = projectdescription.getString("startDate");
                    String expectedenddate = projectdescription.getString("expectedEndDate");
                    String projectsupervisor = projectdescription.getString("projectSupervisor");
                JSONObject contractordata = object_data.getJSONObject("contractorData");
                    String name = contractordata.getString("name");
                    String email = contractordata.getString("email");
                    String phone = contractordata.getString("phone");
                JSONObject projectlocation = object_data.getJSONObject("projectLocation");
                    String lat = projectlocation.getString("lat");
                    String lon = projectlocation.getString("lon");


                et_project_id.setText(          "Project ID: "+projectid);
                et_project_name.setText(        "Project name: "+projectname);
                et_contractor_name.setText(     "Contractor: "+name);
                et_project_location.setText(    "Location: "+"Lat: "+lat+" "+"Lon: "+lon);
                et_start_date.setText(          "Start date: "+startdate);
                et_end_date.setText(            "End date: "+expectedenddate);
                tv_no_of_days.append(remainingdays);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        });

            MySingleton.getInstance(getActivity()).addToRequestQueue(request);

    }
}