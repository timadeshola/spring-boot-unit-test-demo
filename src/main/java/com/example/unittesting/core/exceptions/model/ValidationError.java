package com.example.unittesting.core.exceptions.model;

import lombok.*;

import java.io.Serializable;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 5:03 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ValidationError implements Serializable {

    private Object rejectedValue;
    private String field;
    private String objectName;
}