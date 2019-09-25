package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.Token;
import org.california.model.entity.item.Category;
import org.california.model.transfer.response.item.ProducerDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@EqualsAndHashCode @ToString
@Getter
public class InitialResponse implements Serializable {

    private long id;
    private String name;
    private String token;
    private Map<Long, String> places;
    private Collection<ProducerDto> producers;
    private Category root_category;
    
    public static class Builder {
        
        private InitialResponse result = new InitialResponse();
        
        public NameSetter setId(long id) {
            result.id = id;
            return new NameSetter();
        }
        
        public NameSetter setId(Account account) {
            return setId(account.getId());
        }
        
        public class NameSetter {
            
            public TokenSetter setName(String name) {
                Builder.this.result.name = name;
                return new TokenSetter();
            }
            
            public TokenSetter setName(Account account) {
                return setName(account.getName());
            }
            
        }
        
        public class TokenSetter {
            
            public PlacesSetter setToken(String token) {
                Builder.this.result.token = token;
                return new PlacesSetter();
            }
            
            public PlacesSetter setToken(Token token) {
                return setToken(token.getToken());
            }
            
        }
        
        public class PlacesSetter {
            
            public ProducersSetter setPlaces(Map<Long, String> places) {
                Builder.this.result.places = places;
                return new ProducersSetter();
            }
            
        }
        
        public class ProducersSetter {
            
            public RootCategorySetter setProducers(Collection<ProducerDto> producers) {
                Builder.this.result.producers = producers;
                return new RootCategorySetter();
            }
            
        }
        
        public class RootCategorySetter {
            
            public FinalBuilder setRootCategory(Category category) {
                Builder.this.result.root_category = category;
                return new FinalBuilder();
            }
            
        }
        
        public class FinalBuilder {
            
            public InitialResponse build() {
                return Builder.this.result;
            }
            
        }
        
    }

}
