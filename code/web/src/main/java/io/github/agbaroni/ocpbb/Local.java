package io.github.agbaroni.ocpbb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Local implements Serializable {

    private static final long serialVersionUID = 71381054425573L;

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public Region getRegion(String name) {
	try {
	    return entityManager.find(Region.class, name);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return null;
    }
}
