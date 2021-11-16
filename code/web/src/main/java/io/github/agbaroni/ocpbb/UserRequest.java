package io.github.agbaroni.ocpbb;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.text.WordUtils;

@ManagedBean
@RequestScoped
public class UserRequest implements Serializable {

    private static final String defaultResponse = "I don't know where I'am ...";
    private static final long serialVersionUID = 2168934927597L;

    @Inject
    private Global global;

    @EJB
    private Local local;

    private String country;
    private String response = defaultResponse;

    public String getCountry() {
	return this.country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public String getResponse() {
	return this.response;
    }

    public void setResponse(String response) {
	this.response = response;
    }

    public Object updateResponse() {
	String fixedCountry = global.getCountries()
	    .stream()
	    .filter(c -> c.equalsIgnoreCase(WordUtils.capitalizeFully(country)))
	    .findAny()
	    .orElse(null);

	response = null;

	if (fixedCountry != null) {
	    response = String.format("Hello! I'm in %s, %s.", fixedCountry,
				     local.getRegion(fixedCountry).getCapitol());
	}

	if (response == null) {
	    response = defaultResponse;
	}

	return null;
    }
}
