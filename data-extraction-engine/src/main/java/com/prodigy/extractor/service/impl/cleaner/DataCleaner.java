package com.prodigy.extractor.service.impl.cleaner;

import com.prodigy.extractor.service.Cleaner;
import com.prodigy.extractor.service.impl.cleaner.filters.Filter;
import com.prodigy.extractor.service.impl.cleaner.filters.impl.*;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;


/**
 * This services returns `true` if the data is to be cleared
 * from the pipeline as a result of some underlying filter
 * suggesting of corrupt data.
 */
@Qualifier ("dataCleaner")
@Service public class DataCleaner implements Cleaner {

    private List<Filter> filters;

    /**
     * This level indicates the input data is to be added
     * to error stream
     */
    private float hardWaterLevel = 0.5f;

    /**
     * This level indicates the input data has some missing fields
     * but is ok to be pushed for tag attribution
     */
    private float softWaterLevel = 0.1f;

    /**
     * This represents the total number of keys to be collected
     * by the service
     */
    private int waterLevels = 16;


    @Override public boolean clean (DataExtractionRequest data) {
        return shouldFilter(data);
    }

    /**
     * This utility function should return `true` if
     * current data object is to be filtered out from
     * further processing.
     */
    private boolean shouldFilter (DataExtractionRequest data) {
        int corrupt = 0;
        for (Filter filter : filters) {
            boolean bad = filter.filter(data);
            corrupt += bad ? filter.corruptVal() : 0;
            if (bad) filter.clean(data);
        }
        float corruption = ((float)corrupt/waterLevels);
        return corruption > hardWaterLevel;
    }


    @PostConstruct public void init () {
        filters = new LinkedList<>();
        filters.add(new BrandFilter());
        filters.add(new CategoryFilter());
        filters.add(new DescriptionFilter());
        filters.add(new HttpStatusFilter());
        filters.add(new MetaFilter());
        filters.add(new PriceFilter());
        filters.add(new RatingsFilter());
        filters.add(new SizeFilter());
        filters.add(new SourceFilter());
        filters.add(new StatusFilter());
        filters.add(new SubCategoryFilter());
        filters.add(new TitleFilter());
        filters.add(new UrlFilter());
        filters.add(new ColourFilter());
        filters.add(new FeaturesFilter());
        filters.add(new GenderFilter());
    }

}
