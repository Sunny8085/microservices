package com.bank.userReg.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveAccountDto {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "State is required")
	private String state;
	
	@NotBlank(message = "City is required")
	private String city;
	
	@NotBlank(message = "Pincode is required")
	private String pincode;
	
	@NotBlank(message = "Address is required")
	private List<HashMap<String,String>> address= new ArrayList<HashMap<String,String>>();
	
	@Past(message ="Date of birth must be in past")
	private LocalDate dob;
	
	@NotBlank(message = "Gender is required")
	private String gender;
	
	@Email(message = "Invalid email format")
	private String email;
	
	@NotNull(message = "Phone no must be required")
	private Integer phoneNo;
	
	@NotBlank(message = "Pan number must be required")
	private String panNo;
	
	@NotBlank(message = "Adhaar NO must be required" )
	private String AdhaarNo;
	
	@NotBlank(message = "User Login ID is required")
	private String userLoginID;
}
