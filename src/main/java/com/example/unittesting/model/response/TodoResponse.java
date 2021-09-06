package com.example.unittesting.model.response;

import lombok.*;

import java.io.Serializable;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 11:35 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TodoResponse implements Serializable {
    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;
}