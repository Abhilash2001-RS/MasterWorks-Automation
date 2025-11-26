package com.aurigo.masterworks.testframework.utilities.models.grid;

import java.net.MalformedURLException;
import java.net.URL;

public class Host {
    private String ipAddress;
    private String port;

    public Host(String ipAddress, String port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public Host(URL url) {
        this(url.getHost(), Integer.toString(url.getPort()));
    }

    public Host(String url) throws MalformedURLException {
        this(new URL(url));
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public int getPort() {
        return Integer.parseInt(this.port);
    }

    public String toString() {
        return String.format("Host [%s] listening on port [%s]",  this.ipAddress, this.port);
    }
}
