package com.packtpub.onlineauction.etlbatchprocess.users;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String state;
    private String country;
}
