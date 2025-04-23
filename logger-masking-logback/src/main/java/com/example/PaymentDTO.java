package com.example;

import java.io.Serializable;
import lombok.Data;

@Data
public class PaymentDTO {
  private String host;
  private PayData data;

  @Data
  public static class PayData implements Serializable {
    private String maskCardNo;
    private String cardNo;
    private String expireDate;
    private String cvv;
    private String txNo;
  }

}
