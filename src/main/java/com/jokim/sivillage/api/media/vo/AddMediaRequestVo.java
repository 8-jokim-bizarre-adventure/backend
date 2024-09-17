package com.jokim.sivillage.api.media.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddMediaRequestVo {

    @JsonProperty("mediaUrl")
    private String url;

    @JsonProperty("mediaName")
    private String name;

    @JsonProperty("mediaType")
    private String type;

}
