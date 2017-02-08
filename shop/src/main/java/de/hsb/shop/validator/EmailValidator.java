package de.hsb.shop.validator;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;

        // Set the email pattern string
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

        // Match the given string with the pattern
        Matcher m = p.matcher(email);

        // Check whether match is found
        boolean matchFound = m.matches();

        if (email.length() > 0 && !matchFound) {
            ResourceBundle bundle = ResourceBundle.getBundle("bal.mared.localisation.messages", context.getViewRoot().getLocale());
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(bundle.getString("email_invalid"));
            message.setDetail(bundle.getString("email_invalid"));

            //context.addMessage(component.getClientId(), message);
            throw new ValidatorException(message);
        }
    }
}
