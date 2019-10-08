package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.request.forms.PlaceForm;
import org.california.repository.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlaceService {


    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    public void save(Place place) {
        placeRepository.save(place);
    }


    public Place newPlace(Account account, PlaceForm placeForm) {
        Place place = new Place();

        place.getAccounts().add(account);
        place.setAdmin(account);
        place.setName(placeForm.name);
        place.setCreatedOn(new Date());
        createFirstContainer(place);

        place = placeRepository.save(place);

        if(place != null)
            account.getPlaces().add(place);

        return place;
    }

    private Container createFirstContainer(Place place) {
        Container firstContainer = new Container();
        firstContainer.setName("Common items");
        firstContainer.setPlace(place);
        place.getContainers().add(firstContainer);
        return firstContainer;
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
        place.getUnactiveAccounts().add(user);

        return placeRepository.save(place) != null;
    }


    public boolean newUser(Place place, Account newUser) {
        if(place == null || newUser == null)
            return false;

        if(place.getAccounts().contains(newUser))
            return false;

        newUser.getPlaces().add(place);
        place.getUnactiveAccounts().remove(newUser);
        place.getAccounts().add(newUser);

        return placeRepository.save(place) != null;
    }

}

