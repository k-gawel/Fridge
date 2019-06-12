package org.california.model.transfer.response;

import java.io.Serializable;
import java.util.Map;

public class AccountDatas implements Serializable {

    private long id;
    private String name;
    private String token;
    private Map<Long, String> places;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, String> getPlaces() {
        return places;
    }

    public void setPlaces(Map<Long, String> places) {
        this.places = places;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
