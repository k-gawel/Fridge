package org.california.model.transfer.response;

import org.california.model.entity.Account;

import java.io.Serializable;
import java.nio.channels.AcceptPendingException;
import java.util.function.Function;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInstancesAdded() {
        return instancesAdded;
    }

    public void setInstancesAdded(long instancesAdded) {
        this.instancesAdded = instancesAdded;
    }

    public long getInstancesOpened() {
        return instancesOpened;
    }

    public void setInstancesOpened(long instancesOpened) {
        this.instancesOpened = instancesOpened;
    }

    public long getInstancesDeleted() {
        return instancesDeleted;
    }

    public void setInstancesDeleted(long instancesDeleted) {
        this.instancesDeleted = instancesDeleted;
    }

    @Override
    public String toString() {
        return "uID: " + userId + ", added: " + instancesAdded + ", opened: " + instancesOpened + ", deleted: " + instancesDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceUserStats that = (PlaceUserStats) o;

        if (userId != that.userId) return false;
        if (instancesAdded != that.instancesAdded) return false;
        if (instancesOpened != that.instancesOpened) return false;
        return instancesDeleted == that.instancesDeleted;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (instancesAdded ^ (instancesAdded >>> 32));
        result = 31 * result + (int) (instancesOpened ^ (instancesOpened >>> 32));
        result = 31 * result + (int) (instancesDeleted ^ (instancesDeleted >>> 32));
        return result;
    }
}
