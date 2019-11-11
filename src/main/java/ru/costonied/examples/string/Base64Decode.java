package ru.costonied.examples.string;

import org.apache.commons.codec.binary.Base64;

public class Base64Decode {

    public static void main(String[] args) {
        String base64 = "MDBwMS0wMDAwMDAwMDAwMDAwODg3NDYzfEZpcnN0TmFtZT3Qr9C60L7QsnxTZWNvbmROYW1l" +
                "PdCn0LXRgNC90LjRh9C90YvQuXxMYXN0TmFtZT3QqS58UmVzdD0yODAuMDAwfERhdGU9MDEuMDEuMjAxOQ==";
        System.out.println(new String(Base64.decodeBase64(base64)));
    }
}
