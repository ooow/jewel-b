package com.jewel.job.jobapplication.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude
public class AdModel implements Serializable {
    private String id;
    private String title;
    private Long createdAt;
    private boolean isActive;
    private String description;
    private String photoUrl;
}
