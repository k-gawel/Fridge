package org.california.repository.utils;

import org.hibernate.query.Query;

public class LimitAndOffset {

    public final int limit;
    public final int offset;

    public LimitAndOffset(int limit, int offset) {
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
