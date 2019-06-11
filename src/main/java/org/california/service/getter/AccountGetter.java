package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.repository.account.AccountRepository;
import org.california.repository.authorization.TokenRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Service
public class AccountGetter {

    private AccountRepository accountRepository;
    private TokenRepository tokenRepository;

    @Autowired
    public AccountGetter(AccountRepository accountRepository, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
    }


    public Account getByKey(long id) {
        return accountRepository.getByKey(id);
    }


    public Collection<Account> getByIds(Collection<Long> userIds) {
        if(CollectionUtils.isEmpty(userIds))
            return Collections.emptySet();
        return accountRepository.getByIds(userIds);
    }


    public Account getByToken(String token) {
        try {
            return tokenRepository.getByToken(token).getAccount();
        } catch (Exception e) {
            return null;
        }
    }


    public Collection<Account> getAllByPlace(Place place) {
        return CollectionUtils.union(place.getAccounts(), place.getUnaactiveAccounts());
    }
    

    public Collection<Account> getActiveByPlaces(Collection<Place> places) {
        Collection<Account> result = new HashSet<>();
        
        for(Place place : places)
            result.addAll(place.getAccounts());
            
        return result;
    }


    public Collection<Account> getUnactiveByPlaces(Collection<Place> places) {
        Collection<Account> result = new HashSet<>();
        
        for(Place place : places)
            result.addAll(place.getUnaactiveAccounts());
        
        return result;
    }


    public Collection<Account> getAllByPlaces(Collection<Place> places) {
        Collection<Account> result = new HashSet<>();
        
        for(Place place : places)
            result.addAll(getAllByPlace(place));
        
        return result;
    }


    public Collection<Account> getAllByContainer(Container container) {
        return getAllByPlace(container.getPlace());
    }
    

    public Collection<Account> getActiveByContainers(Collection<Container> containers) {
        Collection<Account> result = new HashSet<>();
        
        for(Container container : containers)
            result.addAll(container.getPlace().getAccounts());
        
        return result;
    }


    public Collection<Account> getUnactiveByContainers(Collection<Container> containers) {
        Collection<Account> result = new HashSet<>();
        
        for(Container container : containers)
            result.addAll(container.getPlace().getUnaactiveAccounts());
    
        return result;
    }


    public Collection<Account> getAllByContainers(Collection<Container> containers) {
        Collection<Account> result = new HashSet<>();
        
        for(Container container : containers)
            result.addAll(getAllByPlace(container.getPlace()));

        return result;
    }


    public Account getByName(String username) {
        if(StringUtils.isAnyBlank(username))
            throw new NotValidException("name.blank");

        return accountRepository.getByName(username);
    }


    public Collection<Account> searchByName(String name) {
        if(StringUtils.isAnyBlank(name))
            throw new NotValidException("name.blank");

        return accountRepository.searchByName(name);
    }

}
