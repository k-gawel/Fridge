package org.california.service.model;

import org.apache.commons.lang3.StringUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.Token;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.InitialResponse;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthorizationService {

    private final TokenService tokenService;
    private final GetterService getterService;
    private final EntityToDtoMapper mapper;


    @Autowired
    public AuthorizationService(TokenService tokenService, GetterService getterService, EntityToDtoMapper mapper) {
        this.tokenService = tokenService;
        this.getterService = getterService;
        this.mapper = mapper;
    }


    public InitialResponse login(String token) {
        Account account = tokenService.getAccountByToken(token);

        if(account == null)
            throw new NotValidException("token.not_valid");

        InitialResponse result;
        result = getInitialResponse(account);
        result.token = token;

        return result;
    }


    public InitialResponse login(String username, String password) {
        if(StringUtils.isAnyBlank(username, password))
            throw new NullPointerException("username.blank|password.blank");

        Account account = getAccount(username, password);

        Token token = tokenService.create(account);
        InitialResponse result;
        result = getInitialResponse(account);
        result.token = token.getToken();
        return result;
    }


    public void logout(String tokenString) {
        Token token = tokenService.getByToken(tokenString);
        tokenService.delete(token);
    }


    private InitialResponse getInitialResponse(Account account) {
        if(account == null)
            throw new NotValidException("account.null");

        InitialResponse result = new InitialResponse();

        result.id = account.getId();
        result.name = account.getName();
        result.places = account.getPlaces().stream()
                .collect(Collectors.toMap(Place::getId, Place::getName));
        result.producers = getterService.producers.getAll().stream()
                .map(mapper::toDto).collect(Collectors.toList());
        result.root_category = getterService.categories.getRootCategory();

        return result;
    }


    private Account getAccount(String username, String password)  {
        if(StringUtils.isAnyBlank(username, password))
            throw new NotValidException("username.blank|password.blank");

        Account account = getterService.accounts.getByName(username);

        if(account == null)
            throw new NotValidException("account.doesn't_exists");
        else if(!account.getPassword().equals(password))
            throw new NotValidException("password.not_equal");
        else
            return account;
    }

}
