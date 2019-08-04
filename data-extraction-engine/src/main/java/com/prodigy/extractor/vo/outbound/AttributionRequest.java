package com.prodigy.extractor.vo.outbound;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prodigy.extractor.vo.inbound.DataExtractionRequest;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Data public class AttributionRequest {

    @JsonProperty("id") private int id;
    @JsonProperty("title") private String title;
    @JsonProperty("subcategory") private String subCategory;
    @JsonProperty("description") private String description;
    @JsonProperty("url") private String url;
    @JsonProperty("source") private String source;
    @JsonProperty("meta") private String meta;
    @JsonProperty("brand") private String brand;
    @JsonProperty("rating") private String rating;
    @JsonProperty("size") private String size;
    @JsonProperty("category") private String category;
    @JsonProperty("price") private String availablePrice;
    @JsonProperty("features") private String features;
    @JsonProperty("color") private String colour;
    @JsonProperty("gender") private String gender;


    public AttributionRequest (DataExtractionRequest request) {
        this.title = request.getTitle();
        this.subCategory = request.getSubcategory();
        this.description = mergeDescription(request);
        this.url = request.getUrl();
        this.source = request.getSource();
        this.meta = request.getMeta();
        this.brand = request.getBrand();
        this.rating = request.getNo_reviews();
        this.size = request.getSize();
        this.category = request.getCategory();
        this.availablePrice = getPrice(request);
        this.features = request.getFeatures();
        this.colour = request.getColor();
        this.gender = request.getGender();

        this.id = url.hashCode();
    }

    private String mergeDescription (DataExtractionRequest request) {
        if (null == request.getDescription()) {
            if (null == request.getDescription_more()) return request.getProduct_description_html();
            return request.getDescription_more() + ((null == request.getProduct_description_html()) ? "" : request.getProduct_description_html());
        }
        if (null == request.getDescription_more()) return request.getDescription() + ((null == request.getProduct_description_html()) ? "" : request.getProduct_description_html());
        return request.getDescription() + " " + request.getDescription_more() + ((null == request.getProduct_description_html()) ? "" : request.getProduct_description_html());
    }

    private String getPrice (DataExtractionRequest request) {
        if (null == request.getAvailable_price()) return null;
        float price;
        try {
            price = Float.parseFloat(request.getAvailable_price());
        } catch (NumberFormatException nfe) {
            return null;
        }
        return String.valueOf(price*69.0f);
    }

}
