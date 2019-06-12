package org.california.service.model;

import org.apache.commons.lang3.StringUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Token;
import org.california.model.transfer.response.AccountDatas;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthorizationService {

    private TokenService tokenService;
    private GetterService getterService;



    @Autowired
    public AuthorizationService(TokenService tokenService, GetterService getterService) {
        this.tokenService = tokenService;
        this.getterService = getterService;
    }


    public AccountDatas login(String token) {
        Account account = tokenService.getAccountByToken(token);

        if(account == null)
            throw new NotValidException("token.not_valid");

        AccountDatas result;
        result = getAccountDatas(account);
        result.setToken(token);


        return result;
    }


    public AccountDatas login(String username, String password) {
        if(StringUtils.isAnyBlank(username, password))
            throw new NullPointerException("username.blank|password.blank");

        Account account = getAccount(username, password);

        Token token = tokenService.create(account);
        AccountDatas result;
        result = getAccountDatas(account);
        result.setToken(token.getToken());
        return result;
    }


    public void logout(String tokenString) {
        Token token = tokenService.getByToken(tokenString);
        tokenService.delete(token);
    }


    private AccountDatas getAccountDatas(Account account) {
        if(account == null)
            throw new NotValidException("account.null");

        AccountDatas result = new AccountDatas();

        result.setId(account.getId());
        result.setName(account.getName());
        result.setPlaces(new HashMap<>());

        account.getPlaces().forEach(p -> result.getPlaces().put(p.getId(), p.getName()));

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
