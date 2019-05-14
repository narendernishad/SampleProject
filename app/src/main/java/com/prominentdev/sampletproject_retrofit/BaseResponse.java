package com.prominentdev.sampletproject_retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success='" + success + '\'' +
                ", error='" + error + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
