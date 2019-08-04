package com.prodigy.extractor.vo.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor()
@Data public class DataExtractionRequest {

    private String original_http_status;
    private String http_status;
    private String title;
    private String subcategory;
    private String description;
    private String url;
    private String source;
    private String meta;
    private String brand;
    private String no_reviews;
    private String size;
    private String category;
    private String available_price;
    private String features;
    private String color;
    private String description_more;
    private String gender;
    private String product_description_html;

}
