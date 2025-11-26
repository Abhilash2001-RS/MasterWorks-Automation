package com.aurigo.masterworks.testframework.utilities.models.grid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NodesInfo {

    @SerializedName("nodes")
    @Expose
    private List<Node> nodes = null;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
