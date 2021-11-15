package io.github.agbaroni.ocpbb;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@ManagedBean
@RequestScoped
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 2168934927597L;

    @Inject
    private Global global;

    @EJB
    private Local local;

    private String region;
    private String response = "Hello! I don't know where I'am ...";

    public String getRegion() {
	return this.region;
    }

    public void setRegion(String region) {
	this.region = region;
    }

    public String getResponse() {
	return this.response;
    }

    public void setResponse(String response) {
	this.response = response;
    }

    public Object updateResponse() {
	response = String.format("Hello! I'm in %s, %s.",
				 global.getCountries().get(0),
				 local.getRegion(region).getCapitol());

	return null;
    }
}
