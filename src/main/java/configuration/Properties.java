package configuration;

import configuration.model.Config;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Slf4j
public class Properties {
    YamlReader yamlReader = new YamlReader();
    private Config config;

    private Properties() {
        setConfigProperties();
    }

    public static Properties getInstance() {
        return Properties.PropertiesSingleton.INSTANCE;
    }


    private void setConfigProperties() {
        config = yamlReader.getConfig();
        Map<String, Object> dataProperties = config.getData();
        for (Map.Entry entry : dataProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            log.info("Load properties: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static class PropertiesSingleton {
        private static final Properties INSTANCE = new Properties();
    }

}
