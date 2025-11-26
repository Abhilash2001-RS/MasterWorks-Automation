package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.utilities.models.grid.Host;

public class GridManager {
    private static final ThreadLocal<Host> currentNode=new ThreadLocal<>();

    /**
     * Gets the current node where the test is executing
     *
     * @return current node
     */
    public static Host getNode(){return currentNode.get();}

    /**
     * Sets the value of current node assigned by grid
     *
     * @param node node value to be set
     */
    public static void setNode(Host node){currentNode.set(node);}
}
