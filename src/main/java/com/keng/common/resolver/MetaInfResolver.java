package com.keng.common.resolver;

public interface MetaInfResolver<R> extends ResourceResolver<R> {

    /**
     * 设置 META-INF 目录下的文件
     * @return
     */
    String metaInfFile();

}
