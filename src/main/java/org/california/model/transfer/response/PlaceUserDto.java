package org.california.model.transfer.response;

import org.california.model.entity.Account;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class PlaceUserDto implements Serializable {

    public Long id;
    public String name;
    public Boolean status;
    public PlaceUserStats stats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceUserDto that = (PlaceUserDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlaceUserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }


    public static class Builder {

        private PlaceUserDto result = new PlaceUserDto();

        public NameSetter setId(@NotNull Long id) {
            Builder.this.result.id = id;
            return new NameSetter();
        }

        public NameSetter setId(@NotNull Account account) {
            return setId(account.getId());
        }

        class NameSetter {
            StatusSetter setName(@NotEmpty String name) {
                Builder.this.result.name = name;
                return new StatusSetter();
            }

            StatusSetter setName(@NotNull Account account) {
                return setName(account.getName());
            }
        }

        class StatusSetter {
            StatsSetter setStatus(boolean stauts) {
                Builder.this.result.status = stauts;
                return new StatsSetter();
            }
        }

        class StatsSetter {

            FinalBuilder setStats(PlaceUserStats stats) {
                Builder.this.result.stats = stats;
                return new FinalBuilder();
            }

        }

        class FinalBuilder {
            PlaceUserDto build() {
                return Builder.this.result;
            }
        }

    }

}
