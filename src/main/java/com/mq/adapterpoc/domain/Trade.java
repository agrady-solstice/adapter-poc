package com.mq.adapterpoc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Trade {

    private Integer id;
    private String type;
    private String symbol;
    private String description;
    private String instruction;
    private Integer quantity;
}
