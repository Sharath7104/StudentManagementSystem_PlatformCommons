package com.app.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	
	

}
