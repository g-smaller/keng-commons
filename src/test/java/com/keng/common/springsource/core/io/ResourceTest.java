package com.keng.common.springsource.core.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ResourceTest {

    String resourceName = "";

    Resource resource = null;

    String filePath = "config-test.properties";

    @Test
    public void classPathResourceTest(){
        resource = new ClassPathResource(filePath);
    }

    @Test
    public void fileSystemResourceTest() {
        resource = new FileSystemResource(filePath);
    }

    @Test
    public void fileTest() {
        File file = new File(filePath);
        System.out.println(file.toURI().toString());
    }

    @After
    public void setDown() {
        if (resource != null) {
            resourceName = resource.getClass().getName();
            existsTest();
            isReadable();
            isOpen();
            getURI();
            getURL();
            getFile();
            contentLength();
            lastModified();
            createRelative();
            getFilename();
            getDescription();
        }
    }

    public void existsTest() {
        log.info("[{}] - exists [{}]", resourceName, resource.exists());
    }


    public void isReadable() {
        log.info("[{}] - isReadable [{}]", resourceName, resource.isReadable());
    }


    public void isOpen() {
        log.info("[{}] - isOpen [{}]", resourceName, resource.isOpen());
    }


    public void getURL() {
        try {
            log.info("[{}] - getURL [{}]", resourceName, resource.getURL().toString());
        } catch (IOException e) {
            log.error("[{}] - getURL 失败", resourceName);
        }
    }

    public void getURI() {
        try {
            log.info("[{}] - getURI [{}]", resourceName, resource.getURI().getPath());
        } catch (IOException e) {
            log.error("[{}] - getURI 失败", resourceName);
        }
    }


    public void getFile() {
        try {
            log.info("[{}] - getFile [{}]", resourceName, resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error("[{}] - getFile 失败", resourceName);
        }
    }

    public void contentLength() {
        try {
            log.info("[{}] - contentLength [{}]", resourceName, resource.contentLength());
        } catch (IOException e) {
            log.error("[{}] - contentLength 失败", resourceName);
        }
    }


    public void lastModified() {
        try {
            log.info("[{}] - lastModified [{}]", resourceName, resource.lastModified());
        } catch (IOException e) {
            log.error("[{}] - lastModified 失败", resourceName);
        }
    }

    public void createRelative() {
        log.info("[{}] - createRelative []", resourceName);
    }

    public void getFilename() {
        log.info("[{}] - getFilename [{}]", resourceName, resource.getFilename());
    }

    public void getDescription() {
        log.info("[{}] - getDescription [{}]", resourceName, resource.getDescription());
    }

}
