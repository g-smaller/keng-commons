package com.keng.common.analyse;

import java.util.function.Consumer;

public interface KengAnalyzable<R> {

    void analyze(Consumer<R> consumer) throws Exception;

}
