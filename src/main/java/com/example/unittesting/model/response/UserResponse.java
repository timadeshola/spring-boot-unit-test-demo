package com.example.unittesting.model.response;

import lombok.*;

import java.io.Serializable;

/**
 * Project title: unit-testing
 *
 * @author johnadeshola
 * Date: 9/6/21
 * Time: 3:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponse implements Serializable {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String empId;
    private String status;
    private String dateCreated;
    private String dateUpdated;

}