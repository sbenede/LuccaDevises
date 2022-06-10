package com.lucca.devise.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exchange {
	private String fromCurrency;
	private String toCurrency;
	private double rate;
}
