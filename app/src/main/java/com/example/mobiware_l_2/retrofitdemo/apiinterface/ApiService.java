package com.example.mobiware_l_2.retrofitdemo.apiinterface;

import com.example.mobiware_l_2.retrofitdemo.model.EmployeeList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of EmployeeList
    */
    @GET("retrofit/json_object.json")
    Call<EmployeeList> getMyJSON();
}
