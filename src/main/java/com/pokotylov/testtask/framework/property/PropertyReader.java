package com.pokotylov.testtask.framework.property;

import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Optional;

public class PropertyReader {
    private static final String PROPERTIES_PATH = "config.properties";

    private PropertyReader() {
    }

    public static String getProperty(String name) {
        try {
            return Optional.ofNullable(new Configurations()
                    .properties(PROPERTIES_PATH)
                    .getString(name))
                    .orElseThrow(() -> new IllegalArgumentException("Could not read property with key " + name));
        } catch (ConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }
}
