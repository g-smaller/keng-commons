package com.keng.common.resolver;

import java.net.URL;

/**
 *
 * @param <R>
 */
public interface ResourceResolver<R> {

    R resolve(URL url);

}
