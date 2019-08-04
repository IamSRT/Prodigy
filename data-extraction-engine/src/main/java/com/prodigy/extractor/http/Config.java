package com.prodigy.extractor.http;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component public class Config {

    private String base = "http://localhost:5000";
    private String endpoint = "/push-attributes";

    public String getUrl () { return this.getBase() + this.getEndpoint(); }

}
