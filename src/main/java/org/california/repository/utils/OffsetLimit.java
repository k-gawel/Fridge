package org.california.repository.utils;

import lombok.ToString;
import org.hibernate.query.Query;

@ToString
public class OffsetLimit {

    public int offset;
    public int limit;

    public OffsetLimit() {
    }

    public OffsetLimit(int offset, int limit) {
        this.limit = limit;
        this.offset = offset;
    }


    public Query applyToQuery(Query query) {
        if (limit != 0)
            query.setMaxResults(limit);
        if (offset != 0)
            query.setFirstResult(offset);
        return query;
    }


}
