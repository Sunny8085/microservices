package com.bank.bankcustomer.exceptionhandler;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
	private String errorDetails;
	private String details;
	private LocalDate timeStamp;
}
