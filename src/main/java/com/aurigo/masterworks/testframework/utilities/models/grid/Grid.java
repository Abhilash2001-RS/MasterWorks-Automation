package com.aurigo.masterworks.testframework.utilities.models.grid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grid {
    @SerializedName("data")
    @Expose
    private GridData data;

    public GridData getData() {
        return data;
    }

    public void setData(GridData data) {
        this.data = data;
    }
}
