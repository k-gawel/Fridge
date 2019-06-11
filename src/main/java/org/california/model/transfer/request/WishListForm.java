package org.california.model.transfer.request;

import java.io.Serializable;

public class WishListForm implements Serializable {

    private String name;
    private long place;
    private String description;
    private long author;

    public WishListForm() {}

    public WishListForm(String name, long place, String description) {
        this.name = name;
        this.place = place;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPlace() {
        return place;
    }

    public void setPlace(long place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
