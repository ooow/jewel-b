package com.jewel.job.jobapplication.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Deprecated
@Document(collection = "example")
public class Example {

    @Id
    @TextIndexed(weight = 2)
    // index for search using Id
    private String exampleId;

    @TextIndexed(weight = 1)
    private String textField;
}
