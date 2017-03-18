package de.hsb.shop.validator;

import de.hsb.shop.model.Member;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@ManagedBean
@RequestScoped
//@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = (String) value;
        System.out.println(em);
        Query query = em.createNamedQuery("SelectMember", Member.class);
        List<Member> memberList = query.getResultList();

        boolean invalid = false;
        for (Member m : memberList) {
            if (m.getUsername().toLowerCase().equals(username.toLowerCase())) {
                invalid = true;
            }
        }

        if (invalid) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "", "Der Name ist bereits vergeben");
            throw new ValidatorException(message);
        } else if (username.length() < 4) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "", "Benutzername muss mindestens 4 Zeichen haben");
            throw new ValidatorException(message);
        } else if (username.length() > 30) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "", "Benutzername darf höchstens 30 Zeichen haben");
            throw new ValidatorException(message);
        }
    }
}
