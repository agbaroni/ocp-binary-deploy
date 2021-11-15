package io.github.agbaroni.ocpbb;

import io.opentracing.Tracer;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.eclipse.microprofile.opentracing.Traced;

@Stateless
@Traced
public class Local implements Serializable {

    private static final long serialVersionUID = 71381054425573L;

    @Inject
    private Tracer tracer;

    @PersistenceUnit(name = "default")
    private EntityManager entityManager;

    public Region getRegion(String name) {
	try {
	    return entityManager.find(Region.class, name);
	} catch (Exception e) {
	    tracer.activeSpan().log(String.format("ERROR: %s", e.toString()));

	    throw e;
	}
    }
}
