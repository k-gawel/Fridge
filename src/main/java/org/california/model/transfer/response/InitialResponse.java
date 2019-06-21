package org.california.model.transfer.response;

import org.california.model.entity.item.Category;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class InitialResponse implements Serializable {

    public long id;
    public String name;
    public String token;
    public Map<Long, String> places;
    public Collection<ProducerDto> producers;
    public Category root_category;

}
