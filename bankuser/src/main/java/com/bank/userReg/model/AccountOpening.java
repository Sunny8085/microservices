package com.bank.userReg.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "customer")
public class AccountOpening {
	@Id
	private String id;
	
	@NotNull(message="Name not be blank")
	private String name;
	
	@Field("account_type")
	@NotNull(message="Account type not be blank")
	private String accountType;
	
	@NotNull(message="Account branch not be blank")
	@Field("account_branch")
	private String accountBranch;
	
	@NotNull(message="Date of birth not be blank")
	private String dob;
	
	@NotNull(message="Gender not be blank")
	private String gender;
	
	@NotNull(message = "Email not be blank")
	private String email;
	
	@NotNull(message="Mobile number not be blank")
	private String contact;	
	
	@NotNull(message="Nationality not be blank")
	private String nationality;
	
	@NotNull(message="Salary not be blank")
	private String salary;
	
	//Address ->State,Street,City,Pin Code
	@NotNull(message= "Address not be blank")
	private List<HashMap<String,String>> address= new ArrayList<HashMap<String,String>>();
	
	@NotNull(message="Nominee not be blank")
	private String nominee;
	
	@NotNull(message = "Nominee Id not be blank")
	@Field("nominee_id")
	private String nomineeId;
	
	@NotNull(message = "Nominee proof not be blank")
	@Field("nominee_proof")
	private String nomineeProof;
	
	@NotNull(message = "Pan No not be blank")
	@Size(min = 10, max = 10, message="Enter valid Pan Card number")
	@Field("pan_no")
	private String panNo;
	
	@NotNull(message = "Aadhaar number not be blank")
	@Field("aadhaar_no")
	private String aadhaarNo;
	
	@NotNull(message = "Pan image not be blank")
	@Field("pan_image")
	private String panImage;
	
	@NotNull(message = "Aadhar image not be blank")
	@Field("aadhaar_image")
	private String aadhaarImage;
	
	@DBRef
	private UserRegistration userRegistration;
	
	@NotNull(message = "Profile image not be blank")
	private String profile;
	
	@NotNull(message = "Signature image not be blank")
	private String signature;
	
	@NotNull(message = "Account staus not be blank")
	@Field("account_status")
	private String accountStatus;
	
	@NotNull(message = "Apply date not be blank")
	@Field("apply_date")
	private LocalDate applyDate;
	
	@Transient
    private String userId;

	public AccountOpening(String userId) {
		super();
		this.userRegistration = new UserRegistration(userId);
	}
	
}
