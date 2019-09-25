package org.california.model.transfer.response.place;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public class PlaceUserDto implements Serializable {

    private Long id;
    private String name;
    private Boolean status;
    private PlaceUserStats stats;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            PlaceUserDto build();
        }

        public interface StatsSetter {
            FinalBuilder withStats(PlaceUserStats stats);
        }

        public interface IdSetter {
            NameSetter withId(Long id);
        }

        public interface NameSetter {
            StatusSetter withName(String name);
        }

        public interface StatusSetter {
            StatsSetter withStatus(Boolean status);
        }

        public static class InnerBuilder implements FinalBuilder, StatsSetter, IdSetter, NameSetter, StatusSetter {
            private PlaceUserDto result = new PlaceUserDto();

            public NameSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public StatusSetter withName(String name) {
                result.name = name;
                return this;
            }

            public StatsSetter withStatus(Boolean status) {
                result.status = status;
                return this;
            }

            public FinalBuilder withStats(PlaceUserStats stats) {
                result.stats = stats;
                return this;
            }

            public PlaceUserDto build() {
                return result;
            }
        }
    }


}