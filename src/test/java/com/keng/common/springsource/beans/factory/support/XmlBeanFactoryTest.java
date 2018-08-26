package com.keng.common.springsource.beans.factory.support;

import com.keng.common.analyse.KengAnalyzable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Slf4j
public class XmlBeanFactoryTest {

    @Test
    public void xmlbeanTest() {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "application.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        KengAnalyzable bean = beanFactory.getBean(KengAnalyzable.class);

    }

}
