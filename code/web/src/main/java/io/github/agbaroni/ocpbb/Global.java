package io.github.agbaroni.ocpbb;

import io.opentracing.Tracer;

import java.io.Serializable;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@ManagedBean
public class Global implements Serializable {

    private static final long serialVersionUID = 232142442226213L;

    @ConfigProperty(name = "properties.directory")
    @Inject
    private String propertiesDirectory;

    @ConfigProperty(name = "countries.file")
    @Inject
    private String countriesFile;

    private List<String> countries;

    @PostConstruct
    public void init() {
	try {
	    FileSystem fileSystem = FileSystems.getDefault();
	    Path path = fileSystem.getPath(propertiesDirectory, countriesFile);

	    countries = Files.readAllLines(path);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public List<String> getCountries() {
	return this.countries;
    }
}
