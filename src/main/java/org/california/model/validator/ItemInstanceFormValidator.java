package org.california.model.validator;

import java.util.Date;

public class ItemInstanceFormValidator extends Validator {


    boolean validateItemId(Long itemId) {
        return validateId(itemId, "item_id");
    }


    boolean validateContainerId(Long containerId) {
        return validateId(containerId, "container_id");
    }


    boolean validateExpireDate(Date expireDate) {
        if(expireDate == null) {
            setMessage("expire_date.null");
            return false;
        }
        return true;
    }


    boolean validateComment(String comment) {
        return validateSingleString(comment, "comment", 0, 400);
    }


}
