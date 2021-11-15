package io.github.agbaroni.ocpbb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "REGIONS")
public class Region implements Serializable {

    private static final long serialVersionUID = 9968715619684L;

    @Column(name = "NAME")
    @Id
    private String name;

    @Column(name = "CAPITOL")
    private String capitol;

    @Column(name = "VERSION")
    @Version
    private int version;

    public String getCapitol() {
	return this.capitol;
    }

    public String getName() {
	return this.name;
    }
}
