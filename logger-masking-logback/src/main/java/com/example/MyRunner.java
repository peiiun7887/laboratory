package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {

        PaymentDTO dto = new PaymentDTO();
        PaymentDTO.PayData payData = new PaymentDTO.PayData();
        payData.setMaskCardNo("123456789******6");
        payData.setCardNo("1234567890123456");
        payData.setCvv("123");
        payData.setExpireDate("1230");
        payData.setTxNo("001");
        dto.setData(payData);
        logger.info("PaymentDTO: {}", dto);

        PaymentDTO dto2 = new PaymentDTO();
        PaymentDTO.PayData payData2 = new PaymentDTO.PayData();
        payData2.setMaskCardNo("123456789*****5");
        payData2.setCardNo("123456789012345"); //15
        payData2.setCvv("123");
        payData2.setExpireDate("1230");
        payData2.setTxNo("002");
        dto2.setData(payData2);
        logger.info("PaymentDTO: {}", dto2);

        PaymentDTO dto3 = new PaymentDTO();
        PaymentDTO.PayData payData3 = new PaymentDTO.PayData();
        payData3.setMaskCardNo("123456789****4");
        payData3.setCardNo("12345678901234"); //14
        payData3.setCvv("123");
        payData3.setExpireDate("1230");
        payData3.setTxNo("003");
        dto3.setData(payData3);
        logger.info("PaymentDTO: {}", dto3);

        PaymentDTO dto4= new PaymentDTO();
        PaymentDTO.PayData payData4 = new PaymentDTO.PayData();
        payData4.setMaskCardNo("123456789***3");
        payData4.setCardNo("1234567890123"); //13
        payData4.setCvv("123");
        payData4.setExpireDate("1230");
        payData4.setTxNo("004");
        dto4.setData(payData4);
        logger.info("PaymentDTO: {}", dto4);

        PaymentDTO dto5 = new PaymentDTO();
        PaymentDTO.PayData payData5 = new PaymentDTO.PayData();
        payData5.setMaskCardNo("123456789**2");
        payData5.setCardNo("123456789012"); //12
        payData5.setCvv("123");
        payData5.setExpireDate("1230");
        payData5.setTxNo("005");
        dto5.setData(payData5);
        logger.info("PaymentDTO: {}", dto5);

        PaymentDTO dto6 = new PaymentDTO();
        PaymentDTO.PayData payData6 = new PaymentDTO.PayData();
        payData6.setMaskCardNo("123456789**");
        payData6.setCardNo("12345678901"); //11
        payData6.setCvv("123");
        payData6.setExpireDate("1230");
        payData6.setTxNo("006");
        dto6.setData(payData6);
        logger.info("PaymentDTO: {}", dto6);

        UserDTO userDTO = new UserDTO();
        userDTO.setPhoneNo("(06)99116000");
        userDTO.setCellPhoneNo("0912345678");
        logger.info("UserDTO: {}", userDTO);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setPhoneNo("(06)9911600011");
        userDTO2.setCellPhoneNo("0912345678");
        logger.info("UserDTO: {}", userDTO2);
    }
}
