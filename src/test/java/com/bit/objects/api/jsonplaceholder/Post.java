package com.bit.objects.api.jsonplaceholder;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Post {

       private String title;
       private String body;
       private int userId;
       private int id;
}
