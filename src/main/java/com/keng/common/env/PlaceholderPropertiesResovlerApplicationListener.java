package com.keng.common.env;

import org.springframework.boot.context.config.RandomValuePropertySource;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 把环境变量中使用占位符的变量解析为实际值
 *
 * 这个功能是为了解决在框架中使用 {@link org.springframework.core.env.Environment#getProperty(String)} 获取不是真实的值
 *
 * @see {@link org.springframework.boot.SpringApplication#prepareContext(org.springframework.context.ConfigurableApplicationContext, org.springframework.core.env.ConfigurableEnvironment, org.springframework.boot.SpringApplicationRunListeners, org.springframework.boot.ApplicationArguments, org.springframework.boot.Banner)}
 * @see {@link org.springframework.boot.SpringApplicationRunListeners#contextLoaded(org.springframework.context.ConfigurableApplicationContext)}
 * @see {@link org.springframework.boot.SpringApplicationRunListener#contextLoaded(org.springframework.context.ConfigurableApplicationContext)}
 * @see {@link org.springframework.boot.context.event.EventPublishingRunListener#contextLoaded(org.springframework.context.ConfigurableApplicationContext)}
 */
public class PlaceholderPropertiesResovlerApplicationListener implements ApplicationListener<ApplicationPreparedEvent> {

    private final List<String> DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES = new ArrayList<String>(4);
    private List<String> IGNORE_PROPERTY_SOURCE_NAMES = new ArrayList<String>();

    public PlaceholderPropertiesResovlerApplicationListener() {
        /**
         * @see {@link StandardEnvironment#customizePropertySources(MutablePropertySources)}
         */
        DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES.add(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);
        DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES.add(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME);
        /**
         * @see {@link org.springframework.boot.SpringApplication#prepareEnvironment}
         * @see {@link org.springframework.boot.context.properties.source.ConfigurationPropertySources#attach(Environment)}
         */
        DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES.add("configurationProperties");
        /**
         * @see {@link org.springframework.boot.context.config.ConfigFileApplicationListener#addPropertySources(ConfigurableEnvironment, ResourceLoader)}
         * @see {@link RandomValuePropertySource#addToEnvironment(ConfigurableEnvironment)}
         */
        DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES.add(RandomValuePropertySource.RANDOM_PROPERTY_SOURCE_NAME);

        IGNORE_PROPERTY_SOURCE_NAMES.addAll(DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES);
    }

    public void addIgnorePropertySourceName(String name) {
        if (!StringUtils.isEmpty(name) && !IGNORE_PROPERTY_SOURCE_NAMES.contains(name)) {
            IGNORE_PROPERTY_SOURCE_NAMES.add(name);
        }
    }

    public void removePropertySourceName(String name) {
        if (!StringUtils.isEmpty(name) && !DEFAULT_IGNORE_PROPERTY_SOURCE_NAMES.contains(name)) {
            IGNORE_PROPERTY_SOURCE_NAMES.remove(name);
        }
    }

    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();

        for (PropertySource<?> propertySource : propertySources) {
            String name = propertySource.getName();
            if (IGNORE_PROPERTY_SOURCE_NAMES.contains(name)) {
                continue;
            }

            /**
             * @see {@link org.springframework.boot.env.PropertiesPropertySourceLoader#load(String, Resource)} )}
             * @see {@link org.springframework.boot.env.OriginTrackedMapPropertySource}
             */
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) propertySource;
                Map<String, Object> properties = mapPropertySource.getSource();
                String[] propertyNames = mapPropertySource.getPropertyNames();
                for (String propertyName : propertyNames) {
                    Object o = properties.get(propertyName);
                    if (o == null) {
                        continue;
                    }
                    String s = o.toString();
                    if (s.contains("${")) {
                        String placeholderStr = environment.resolvePlaceholders(s);
                        properties.put(propertyName, placeholderStr);
                    }
                }
            }
        }
    }
}
