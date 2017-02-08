package de.hsb.shop.validator;

import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailVerificationValidator")
public class EmailVerificationValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String emailVerification = (String) value;
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        ResourceBundle bundle = ResourceBundle.getBundle("bal.mared.localisation.messages", context.getViewRoot().getLocale());

        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        message.setSummary(bundle.getString("email_match"));
        message.setDetail(bundle.getString("email_match"));

        if (params.get("email") != null) {
            String email = params.get("email").toString();

            if (emailVerification != null && !emailVerification.contentEquals(email)) {

                //context.addMessage(component.getClientId(), message);

                /*
            context.addMessage(uiInputEmail.getClientId(), message);
            uiInputEmail.setValid(false);
            uiInputEmail.setSubmittedValue(email);
                 */
                throw new ValidatorException(message);
            }
        } else {
            throw new ValidatorException(message);
        }

    }
}
