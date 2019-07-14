package com.tweetx.search.core.configuration;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.tweetx.search.core.configuration.ConfigConstants.CONFIG_FILE;


/**
 * Authored by dushan.p@viewqwest.com on 14/7/19.
 * http://dushan.lk
 */
@Component(immediate = true)
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    private ConfigurationAdmin configAdmin;

    private ConcurrentHashMap<String, String> configs = new ConcurrentHashMap<>();

    @Activate
    protected void activate(Map<String, Object> config) {
        try {
            final Dictionary<String, Object> properties = configAdmin.getConfiguration(CONFIG_FILE).getProperties();
            Collections.list(properties.keys()).forEach(key -> configs.put(key, properties.get(key).toString()));
        } catch (IOException e) {
            LOGGER.error("Unexpected error occurred", e);
        }
    }

    @Override
    public String get(String key) {
        return configs.get(key);
    }

    @Reference
    public void setConfigAdmin(ConfigurationAdmin configAdmin) {
        this.configAdmin = configAdmin;
    }
}
