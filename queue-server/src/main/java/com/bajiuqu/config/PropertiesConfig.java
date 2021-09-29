package com.bajiuqu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "bajiuqu.queue")
public class PropertiesConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;

    public PropertiesConfig() {
    }

    public PropertiesConfig(String host, Integer port, String username, String password, String virtualHost) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.virtualHost = virtualHost;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    @Override
    public String toString() {
        return "PropertiesConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", virtualHost='" + virtualHost + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertiesConfig)) return false;
        PropertiesConfig that = (PropertiesConfig) o;
        return getHost().equals(that.getHost()) && getPort().equals(that.getPort()) && getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword()) && getVirtualHost().equals(that.getVirtualHost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHost(), getPort(), getUsername(), getPassword(), getVirtualHost());
    }

}
