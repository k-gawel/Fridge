package org.california.service.model;

import org.apache.commons.lang3.StringUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.Token;
import org.california.model.transfer.response.InitialResponse;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
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

        return result;
    }


    public InitialResponse login(String username, String password) {
        if(StringUtils.isAnyBlank(username, password))
            throw new UnauthorizedException("datas.wrong");

        Account account = getAccount(username, password);

        tokenService.create(account);
        InitialResponse result;
        result = getInitialResponse(account);
        return result;
    }


    public void logout(String tokenString) {
        Token token = tokenService.getByToken(tokenString);
        tokenService.delete(token);
    }


    private InitialResponse getInitialResponse(Account account) {
        if(account == null)
            throw new NotValidException("account.null");

        return new InitialResponse.Builder().create()
                .withId(account.getId())
                .withName(account.getName())
                .withToken(getterService.tokens.getByAccount(account).getToken())
                .withPlaces(account.getPlaces().stream().collect(Collectors.toMap(Place::getId, Place::getName)))
                .withProducers(getterService.producers.getAll().stream().map(mapper::toDto).collect(Collectors.toList()))
                .withRootCategory(getterService.categories.getRootCategory())
                .build();
    }


    private Account getAccount(String username, String password)  {
        if(StringUtils.isAnyBlank(username, password))
            throw new NotValidException("username.blank|password.blank");

        Account account = getterService.accounts.getByName(username)
                .orElseThrow(() -> new NotValidException("account.doesn't_exists"));

        if (!account.getPassword().equals(password))
            throw new NotValidException("password.not_equal");
        else
            return account;
    }

}
