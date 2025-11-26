package com.aurigo.masterworks.testframework.utilities.models.grid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GridData {
    @SerializedName("nodesInfo")
    @Expose
    private NodesInfo nodesInfo;

    public NodesInfo getNodesInfo() {
        return nodesInfo;
    }

    public void setNodesInfo(NodesInfo nodesInfo) {
        this.nodesInfo = nodesInfo;
    }
}
