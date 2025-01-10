package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDTOTester {
    private static  final Logger logger = LoggerFactory.getLogger(PaymentDTOTester.class);
    public static void main(String[] args) {

        UserDTO dto = new UserDTO();
        dto.setPhoneNo("(06)9911600");
        dto.setCellPhoneNo("0912345678");
        logger.debug("UserDTO: {}", dto);
    }
}
