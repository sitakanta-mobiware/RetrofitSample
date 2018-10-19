package com.example.mobiware_l_2.retrofitdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeeList {

    @SerializedName("employee")
    @Expose
    private ArrayList<Employee> employee = null;

    public ArrayList<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(ArrayList<Employee> employee) {
        this.employee = employee;
    }
}
