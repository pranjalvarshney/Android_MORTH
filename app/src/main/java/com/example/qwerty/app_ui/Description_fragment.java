package com.example.qwerty.app_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class Description_fragment extends Fragment {

   SharedPrefs sharedPrefs;
   RequestQueue queue;
    Button buttonLogout = null;
    String url = "https://api.myjson.com/bins/7roy0";
    String mlat="",mlon="";
    TextView tv_project_paragraph, et_project_id, et_project_name,
            et_contractor_name, et_project_location, et_start_date,
            et_end_date, et_password, et_phone_no, et_email;
    WebView location_webview;
    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_description_fragment,container,false);


        sharedPrefs = new SharedPrefs(getActivity());
        queue = MySingleton.getInstance(getActivity()).getRequestQueue();

        tv_project_paragraph = view.findViewById(R.id.tv_project_paragraph);
        et_contractor_name = view.findViewById(R.id.et_contractor_name);
        et_project_id = view.findViewById(R.id.et_project_id);
        et_project_name = view.findViewById(R.id.et_project_name);
        et_project_location = view.findViewById(R.id.et_project_location);
        et_end_date = view.findViewById(R.id.et_end_date);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_project_password);
        et_start_date = view.findViewById(R.id.et_start_date);
        et_phone_no = view.findViewById(R.id.et_phone_no);


        dataparse();

       buttonLogout = view.findViewById(R.id.btn_logout);

        location_webview = view.findViewById(R.id.webview_location);
        WebSettings webSettings = location_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        location_webview.setVerticalScrollBarEnabled(true);



        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefs.writeLoginStatus(false, "", "");

                Intent i = new Intent(getActivity(), Contractor_login_activity.class);
                startActivity(i);
            }
        });


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



                et_project_id.setText("Project ID: "+projectid);
                et_project_name.setText("Project name: "+projectname);
                et_contractor_name.setText("Contractor: "+name);
                et_start_date.setText("Start date: "+startdate);
                et_end_date.setText("End Date: "+expectedenddate);
                tv_project_paragraph.setText(description);
                et_project_location.setText("Location: "+"Lat: "+lat+" "+"Lon: "+lon);
                et_phone_no.setText("Phone no: "+phone);
                et_email.setText("E-mail: "+email);
                location_webview.loadUrl("http://thisisnow.tk/ters/show_location.php?lat="+lat+"&lon="+lon);



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
