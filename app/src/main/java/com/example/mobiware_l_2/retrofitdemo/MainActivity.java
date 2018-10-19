package com.example.mobiware_l_2.retrofitdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mobiware_l_2.retrofitdemo.apiinterface.ApiService;
import com.example.mobiware_l_2.retrofitdemo.model.Employee;
import com.example.mobiware_l_2.retrofitdemo.model.EmployeeList;
import com.example.mobiware_l_2.retrofitdemo.retrofit.EmployeeAdapter;
import com.example.mobiware_l_2.retrofitdemo.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Employee> employeeList;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private EmployeeAdapter eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Creating an object of our api interface
        ApiService api = RetrofitClient.getApiService();

        /**
         * Calling JSON
         */
        Call<EmployeeList> call = api.getMyJSON();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    employeeList = response.body().getEmployee();
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                    eAdapter = new EmployeeAdapter(employeeList);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                }
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                pDialog.dismiss();
            }
        });
    }
}
