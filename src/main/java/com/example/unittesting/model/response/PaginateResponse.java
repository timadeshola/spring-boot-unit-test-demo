package com.example.unittesting.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 4:32 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaginateResponse<T> implements Serializable {

    private List<T> content;
    private long totalElements;
}