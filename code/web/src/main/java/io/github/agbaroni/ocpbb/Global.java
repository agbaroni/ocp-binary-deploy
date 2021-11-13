package io.github.agbaroni.ocpbb;

import io.opentracing.Tracer;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.Traced;

@ApplicationScoped
@ManagedBean
@Traced
public class Global {

    @ConfigProperty(name = "properties.directory")
    @Inject
    private String propertiesDirectory;

    @ConfigProperty(name = "countries.file")
    @Inject
    private String countriesFile;

    @Inject
    Tracer tracer;

    private List<String> countries;

    @PostConstruct
    public void init() {
	try {
	    var fileSystem = FileSystems.getDefault();
	    var path = fileSystem.getPath(propertiesDirectory, countriesFile);

	    countries = Files.readAllLines(path);
	} catch (IOException e) {
	}
    }

    public List<String> getCountries() {
	return this.countries;
    }
}