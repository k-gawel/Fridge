package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.Token;
import org.california.repository.authorization.TokenRepository;
import org.california.util.exceptions.NotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class TokenService {

    private TokenRepository tokenRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public boolean delete(Token token) {
        return tokenRepository.delete(token);
    }


    public Token create(Account account) {
        Token accountToken = getByAccount(account);

        while(accountToken != null) {
            delete(accountToken);
            accountToken = getByAccount(account);
        }

        Token newToken = new Token();

        String token = account.getEmail().hashCode()
                     + account.getCreatedOn().hashCode()
                     + account.getName().hashCode()
                     + String.valueOf((new Random()).nextLong())
                     + (new Date()).hashCode();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);

        newToken.setAccount(account);
        newToken.setToken(token);
        newToken.setExpirationDate(calendar.getTime());

        return tokenRepository.save(newToken);
    }


    public Token getByAccount(Account account) {
        return tokenRepository.getByAccount(account);
    }


    public Account getAccountByToken(String tokenString) {
        Token token = tokenRepository.getByToken(tokenString);

        if(token == null)
            throw new NotValidException("token.notvalid");

        return token.getAccount();
    }


    public Token getByToken(String token) {
        return tokenRepository.getByToken(token);
    }


}
