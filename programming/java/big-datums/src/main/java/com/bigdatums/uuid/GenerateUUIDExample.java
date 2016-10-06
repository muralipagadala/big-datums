package com.bigdatums.uuid;

import java.util.UUID;

public class GenerateUUIDExample {

    public static void main(String[] args) {
        //create random uuid object
        UUID randomUUID = UUID.randomUUID();

        //print uuid hexadecimal representation along with variant and version
        System.out.println(randomUUID);
        System.out.println(randomUUID.variant());
        System.out.println(randomUUID.version());
    }

}
