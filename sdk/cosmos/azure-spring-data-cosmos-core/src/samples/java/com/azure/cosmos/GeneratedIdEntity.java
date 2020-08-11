package com.azure.cosmos;

import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import org.springframework.data.annotation.Id;

public class GeneratedIdEntity {

    @Id
    @GeneratedValue
    public String id;

}
