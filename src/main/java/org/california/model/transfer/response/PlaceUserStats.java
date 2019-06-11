package org.california.model.transfer.response;

import java.io.Serializable;

public class PlaceUserStats implements Serializable {

    private long userId;
    private long instancesAdded;
    private long instancesOpened;
    private long instancesDeleted;

    public PlaceUserStats() {}

    public PlaceUserStats(long userId, long instancesAdded, long instancesOpened, long instancesDeleted) {
        this.userId = userId;
        this.instancesAdded = instancesAdded;
        this.instancesOpened = instancesOpened;
        this.instancesDeleted = instancesDeleted;
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
