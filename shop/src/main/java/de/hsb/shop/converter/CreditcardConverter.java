package de.hsb.shop.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Dieser Konverter entfernt alle leerzeichen im Eingabefeld der Kreditkartennummer
 * @author Tobi
 */

@FacesConverter(value = "creditcardConverter")
public class CreditcardConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        String s = value;
        if (value.contains(" ")) {
            s = value.replace(" ", ""); // Leerzeichen entfernen
        }
        if (value.contains("-")) {
            s = value.replace("-", ""); // Trennstrich entfernen
        }
        return s;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (String) value;
    }

}
