package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.item.Category;
import org.california.model.transfer.response.item.ProducerDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@EqualsAndHashCode
@ToString
@Getter
public class InitialResponse implements Serializable {

    private long id;
    private String name;
    private String token;
    private Map<Long, String> places;
    private Collection<ProducerDto> producers;
    private Category rootCategory;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            InitialResponse build();
        }

        public interface IdSetter {
            NameSetter withId(long id);
        }

        public interface RootCategorySetter {
            FinalBuilder withRootCategory(Category rootCategory);
        }

        public interface PlacesSetter {
            ProducersSetter withPlaces(Map<Long, String> places);
        }

        public interface NameSetter {
            TokenSetter withName(String name);
        }

        public interface ProducersSetter {
            RootCategorySetter withProducers(Collection<ProducerDto> producers);
        }

        public interface TokenSetter {
            PlacesSetter withToken(String token);
        }

        public static class InnerBuilder implements FinalBuilder, IdSetter, RootCategorySetter, PlacesSetter, NameSetter, ProducersSetter, TokenSetter {
            private InitialResponse result = new InitialResponse();

            public NameSetter withId(long id) {
                result.id = id;
                return this;
            }

            public TokenSetter withName(String name) {
                result.name = name;
                return this;
            }

            public PlacesSetter withToken(String token) {
                result.token = token;
                return this;
            }

            public ProducersSetter withPlaces(Map<Long, String> places) {
                result.places = places;
                return this;
            }

            public RootCategorySetter withProducers(Collection<ProducerDto> producers) {
                result.producers = producers;
                return this;
            }

            public FinalBuilder withRootCategory(Category rootCategory) {
                result.rootCategory = rootCategory;
                return this;
            }

            public InitialResponse build() {
                return result;
            }
        }
    }
}
