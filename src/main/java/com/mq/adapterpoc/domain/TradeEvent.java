package com.mq.adapterpoc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TradeEvent {

    private Integer eventId;
    private Trade trade;

}
