package com.prodigy.extractor.service.impl.cleaner.filters;

public interface Filter<T> {

    public boolean filter (T data);

    public void clean (T data);

    default int corruptVal () { return 1; }

}
