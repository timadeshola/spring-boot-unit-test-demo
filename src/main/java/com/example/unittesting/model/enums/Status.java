package com.example.unittesting.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 3:56 PM
 */
@Getter
@AllArgsConstructor
public enum Status {

    ACTIVE('0'),
    INACTIVE('1');

    private Character status;

}