package com.example.dto;

import org.openqa.selenium.WebElement;

public class RowWithPrice {
    private WebElement row;
    private int price;

    public RowWithPrice(WebElement row, int price) {
        this.row = row;
        this.price = price;
    }

    public WebElement getRow() {
        return row;
    }

    public void setRow(WebElement row) {
        this.row = row;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
