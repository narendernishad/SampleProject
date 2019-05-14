package com.prominentdev.sampletproject_retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @SerializedName("next_page")
    @Expose
    private String mNextPage;

    public String getNextPage() {
        return mNextPage;
    }

    public void setNextPage(String mNextPage) {
        this.mNextPage = mNextPage;
    }

    @Override
    public String toString() {
        return "Data";
    }

}