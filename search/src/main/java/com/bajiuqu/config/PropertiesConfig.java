package com.bajiuqu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "bajiuqu.elasticsearch")
public class PropertiesConfig {

    private String hostname;
    private Integer port;
    private String scheme;
    private String username;
    private String password;

    public PropertiesConfig() {
    }

    public PropertiesConfig(String hostname, Integer port, String scheme, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.scheme = scheme;
        this.username = username;
        this.password = password;
    }

    public PropertiesConfig(String hostname, Integer port, String scheme) {
        this.hostname = hostname;
        this.port = port;
        this.scheme = scheme;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getPort() {
        return port;
    }

    public String getScheme() {
        return scheme;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PropertiesConfig{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", scheme='" + scheme + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertiesConfig)) return false;
        PropertiesConfig that = (PropertiesConfig) o;
        return getHostname().equals(that.getHostname()) && getPort().equals(that.getPort()) && getScheme().equals(that.getScheme()) && getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHostname(), getPort(), getScheme(), getUsername(), getPassword());
    }

}
