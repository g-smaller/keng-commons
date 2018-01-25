package com.keng.common.dependency;

import com.keng.common.Words;
import com.keng.common.resolver.MetaInfResolver;
import com.keng.common.util.FileUtil;
import com.keng.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;

@Slf4j
public abstract class DependencyResolver implements MetaInfResolver<Dependency> {

    @Override
    public String metaInfFile() {
        return Words.EMPTY;
    }

    @Override
    public Dependency resolve(URL url) {
        if (url != null) {
            String filePath = url.toString();
            if (StringUtil.isNotEmpty(metaInfFile())) {
                filePath = joinFullFile(filePath);
            }
            try {
                return resolve(filePath);
            } catch (IOException e) {
                log.error("loading file [{}] 失败", filePath);
            }
        }
        return null;
    }

    protected abstract Dependency resolve(String filePath) throws IOException;

    protected boolean isNotEmpty(String groupId, String artifactId, String version) {
        return StringUtil.isNotEmpty(groupId) && StringUtil.isNotEmpty(artifactId) && StringUtil.isNotEmpty(version);
    }

    public String joinFullFile(String filePath) {
        if (StringUtil.isNotEmpty(filePath)) {
            String fileSeparator = FileUtil.getFileSeparator();
            String last = filePath.substring(filePath.length() - fileSeparator.length(), filePath.length());
            if (fileSeparator.equals(last)) {
                fileSeparator = "";
            }
            return filePath + fileSeparator + metaInfFile();
        }
        return null;
    }

}
