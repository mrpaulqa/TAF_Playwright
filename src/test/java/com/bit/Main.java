package com.bit;

import com.bit.objects.Adress;
import com.bit.objects.User;
import com.bit.utils.SecretLogic;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

//@ExtendWith({SecretLogic.class})
public class Main {
    public static void main(String[] args) {

        User maria = User.builder()
                .username("maria")
                .salary(5000)
                .lastName("Smith")
                .firstName("maria")
                .email("maria_smith@gmail.com")
                .adress(new Adress("123 Main St", "Anytown"))
                .build();

        System.out.println(maria.toString());


    }
}
