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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountGetter extends BaseGetter<Account> {

    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    AccountGetter(AccountRepository accountRepository, TokenRepository tokenRepository) {
        super(accountRepository, Account.class);
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
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
        return places.stream()
                .map(Place::getAccounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Collection<Account> getUnactiveByPlaces(Collection<Place> places) {
        return places.stream()
                .map(Place::getUnaactiveAccounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Collection<Account> getAllByPlaces(Collection<Place> places) {
        Stream<Account> active = places.stream()
                .map(Place::getAccounts)
                .flatMap(Collection::stream);

        Stream<Account> unactive = places.stream()
                .map(Place::getAccounts)
                .flatMap(Collection::stream);

        return Stream.concat(active, unactive).collect(Collectors.toSet());
    }


    public Collection<Account> getAllByContainer(Container container) {
        return getAllByPlace(container.getPlace());
    }
    

    public Collection<Account> getActiveByContainers(Collection<Container> containers) {
        return containers.stream()
                .map(Container::getPlace)
                .map(Place::getAccounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Collection<Account> getUnactiveByContainers(Collection<Container> containers) {
        return containers.stream()
                .map(Container::getPlace)
                .map(Place::getUnaactiveAccounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Collection<Account> getAllByContainers(Collection<Container> containers) {
        return containers.stream()
                .map(Container::getPlace)
                .map(Place::getAccounts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }


    public Optional<Account> getByName(@NotBlank String username) {
        return accountRepository.getByName(username);
    }


    public Account getByEmail(@Email @NotBlank String email) {
        return accountRepository.getByEmail(email);
    }


    public Collection<Account> searchByName(String name) {
        if(StringUtils.isAnyBlank(name))
            throw new NotValidException("name.blank");

        return accountRepository.searchByName(name);
    }

}
