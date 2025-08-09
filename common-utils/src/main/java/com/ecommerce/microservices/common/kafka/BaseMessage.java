package com.ecommerce.microservices.common.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseMessage implements Serializable {
    private Object payload;
    private String additionalInfo;
}
