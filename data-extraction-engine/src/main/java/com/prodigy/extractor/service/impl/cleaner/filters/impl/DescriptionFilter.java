package com.prodigy.extractor.service.impl.cleaner.filters.impl;

import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.util.StringUtils;

public class DescriptionFilter implements Filter<DataExtractionRequest> {

    @Override public boolean filter (DataExtractionRequest data) {
        if (StringUtils.isEmpty(data.getDescription())) {
            if (StringUtils.isEmpty(data.getDescription_more())) return true;
            return false;
        }
        return true;
    }

    @Override public void clean (DataExtractionRequest data) {
        data.setDescription(null);
        data.setDescription_more(null);
    }

}

