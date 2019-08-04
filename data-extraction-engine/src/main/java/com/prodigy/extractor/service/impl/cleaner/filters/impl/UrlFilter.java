package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class UrlFilter implements Filter<DataExtractionRequest> {

    @Override public boolean filter (DataExtractionRequest data) {
        return StringUtils.isEmpty(data.getUrl()) ;
    }

    @Override public void clean (DataExtractionRequest data) { data.setUrl(null); }

}
