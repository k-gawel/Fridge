package org.california.model.validator;

public class AccountFormValidator extends Validator {

    private String password1;
    private String password2;


    public boolean validateName(String name) {

        if(!ValidationRegex.ALPHANUMERIC.check(name)) {
            setMessage("name.not_alphanumeric");
            return false;
        }

        return validateSingleString(name, "name", 5, 20);
    }


    public boolean validateEmail(String email) {
        //TODO
        return true;
    }


    public boolean validatePassword1(String password1) {
        this.password1 = password1;

        Boolean validationResult = validatePasswords();

        return validationResult != null ? validationResult : true;
    }


    public boolean validatePassword2(String password2) {
        this.password2 = password2;

        Boolean validationResult = validatePasswords();

        return validationResult != null ? validationResult : true;
        }


    public boolean validateSinglePassword(String password) {
        return validateSingleString(password, "password", 8, 40);
    }


    private Boolean validatePasswords() {
        if(password1 == null || password2 == null)
            return null;

        if(!validateSinglePassword(password1) || !validateSinglePassword(password2))
            return false;


        boolean result = password1.equals(password2);

        if(!result)
            setMessage("passwords.not_equal");

        return result;
    }

}
