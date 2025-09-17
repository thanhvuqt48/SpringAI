package com.thanhvu.springaidemo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"item_name", "unit", "quantity", "price", "sub_total"})
public record BillItem(String itemName,
                       String unit,
                       Integer quantity,
                       Double price,
                       Double subTotal) {
}
