package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class MetaFilter implements Filter<DataExtractionRequest> {

    @Override public boolean filter (DataExtractionRequest data) {
        return StringUtils.isEmpty(data.getMeta()) ;
    }

    @Override public void clean (DataExtractionRequest data) { data.setMeta(null); }

}
