package com.bit.objects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private int salary;
    private String lastName;
    private String firstName;
    private String email;
    private Adress adress;
}
