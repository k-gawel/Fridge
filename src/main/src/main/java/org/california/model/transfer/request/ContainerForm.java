package org.california.model.transfer.request;

import org.json.JSONObject;

import java.io.Serializable;

public class ContainerForm implements Serializable {

    private Long placeId;
    private String name;

    public ContainerForm() {}

    public ContainerForm(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        this.placeId = json.getLong("placeId");
        this.name = json.getString("name");
    }

    public boolean parse() {
        if(placeId == null || name == null) return false;
        if(name.equals("")) return false;
        return true;
    }

    public boolean validate() {
        if(placeId == null || name == null) return false;
        if(name.equals("")) return false;
        return true;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
