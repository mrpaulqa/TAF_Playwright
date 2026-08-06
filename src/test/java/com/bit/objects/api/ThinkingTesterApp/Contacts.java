package com.bit.objects.api.ThinkingTesterApp;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Contacts {
        private String firstName;
        private String lastName;
        private String birthdate;
        private String email;
        private String phone;
        private String street1;
        private String city;
        private String owner;
}
