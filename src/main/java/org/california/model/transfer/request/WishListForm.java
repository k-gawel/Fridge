package org.california.model.transfer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class WishListForm implements Serializable {

    public final String name;
    public final long place;
    public final String description;
    public final long author;

    @JsonCreator
    public WishListForm(String name, long place, String description, long author) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.author = author;
    }
}
