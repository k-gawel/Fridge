package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.california.model.entity.Account;

import java.io.Serializable;
import java.util.function.Function;

@Getter @Setter
@EqualsAndHashCode @ToString
public class PlaceUserStats implements Serializable {

    private long userId;
    private long instancesAdded;
    private long instancesOpened;
    private long instancesDeleted;

    public PlaceUserStats() {}

    public PlaceUserStats(Account a) {
        this(a.getId(), null, null, null);
    }

    public PlaceUserStats(long userId, Long instancesAdded, Long instancesOpened, Long instancesDeleted) {
        Function<Long, Long> getLong = l -> l == null ? 0 : l;

        this.userId = userId;
        this.instancesAdded = getLong.apply(instancesAdded);
        this.instancesOpened = getLong.apply(instancesOpened);
        this.instancesDeleted = getLong.apply(instancesDeleted);
    }

}
