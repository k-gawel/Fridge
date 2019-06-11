package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.request.PlaceForm;
import org.california.model.validator.PlaceFormValidator;
import org.california.model.validator.Validator;
import org.california.repository.place.PlaceRepository;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlaceService {


    private PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    public void save(Place place) {
        placeRepository.save(place);
    }


    public Place newPlace(Account account, PlaceForm placeForm) {

        Validator validator = new PlaceFormValidator();
        if(!validator.validate(placeForm))
            throw new NotValidException(validator.getMessagesAsString());

        Place place = new Place();
        place.getAccounts().add(account);
        place.setAdmin(account);
        place.setName(placeForm.getName());
        place.setCreatedOn(new Date());

        Container firstContainer = new Container();
        firstContainer.setName("Common items");
        firstContainer.setPlace(place);
        place.getContainers().add(firstContainer);

        place = placeRepository.save(place);

        if(place != null)
            account.getPlaces().add(place);

        return place;
    }


    public boolean changeAdmin(Place place, Account newAdmin) {
        if(place == null || newAdmin == null)
            return false;
        if(place.getAdmin().equals(newAdmin) || !place.getAccounts().contains(newAdmin))
            return false;

        place.setAdmin(newAdmin);

        return placeRepository.save(place) != null;
    }


    public boolean removeUser(Place place, Account user) {
        if(place == null || user == null)
            return false;

        if(!place.getAccounts().contains(user))
            return false;

        if(place.getAdmin().equals(user) && place.getAccounts().size() != 1)
            return false;

        place.getAccounts().remove(user);
        place.getUnaactiveAccounts().add(user);

        return placeRepository.save(place) != null;
    }


    public boolean newUser(Place place, Account newUser) {
        if(place == null || newUser == null)
            return false;

        if(place.getAccounts().contains(newUser))
            return false;

        place.getUnaactiveAccounts().remove(newUser);
        place.getAccounts().add(newUser);

        return placeRepository.save(place) != null;
    }

}

