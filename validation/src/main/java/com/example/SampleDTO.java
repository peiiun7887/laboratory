package com.example;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * Field validation with
 */
public class SampleDTO {
  @Pattern(regexp = "web|mobile", message = "not allow.")
  private String host;

  @Valid
  private PayData data;

  public static class PayData implements Serializable {

    @NotBlank(message = "is required.")
    private String orderNo = "";

    @Min(value = 0, message = "must grater than or equal to 0")
    private String amount = "";

    @Pattern(regexp = "1|2|3|4|5|20|21")
    private String authFlag = "";

    private String cardNo = "";
    private String expireDate = "";
    private String cvv = "";
    private String txNo = "";

    public String getOrderNo() {
      return orderNo;
    }

    public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
    }

    public String getAmount() {
      return amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    public String getAuthFlag() {
      return authFlag;
    }

    public void setAuthFlag(String authFlag) {
      this.authFlag = authFlag;
    }

    public String getCardNo() {
      return cardNo;
    }

    public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
    }

    public String getExpireDate() {
      return expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public String getCvv() {
      return cvv;
    }

    public void setCvv(String cvv) {
      this.cvv = cvv;
    }

    public String getTxNo() {
      return txNo;
    }

    public void setTxNoxNo(String txNo) {
      this.txNo = txNo;
    }
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public PayData getData() {
    return data;
  }

  public void setData(PayData data) {
    this.data = data;
  }



}
