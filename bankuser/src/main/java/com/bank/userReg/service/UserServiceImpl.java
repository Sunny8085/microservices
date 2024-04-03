package com.bank.userReg.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bank.userReg.dto.UserDto;
import com.bank.userReg.exceptionhandler.OTPVerificationException;
import com.bank.userReg.exceptionhandler.ResourceFoundException;
import com.bank.userReg.exceptionhandler.UserNotFoundException;
import com.bank.userReg.exceptionhandler.UserNotVerifiedException;
import com.bank.userReg.model.OneTimePassword;
import com.bank.userReg.model.UserRegistration;
import com.bank.userReg.repository.OtpRepository;
import com.bank.userReg.repository.UserRepository;
import com.bank.userReg.repository.UserRoleRepository;
import com.bank.userReg.util.FileUploadHelper;
import com.bank.userReg.util.OTPGenerator;
import com.bank.userReg.util.UserDataTransfer;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private UserDataTransfer userDataTransfer;
	@Autowired
	private FileUploadHelper fileuploadhelper;
	
	@Override
	public ResponseEntity<String> insertUser(@Valid UserDto userDto) {
        userRepository.findById(userDto.getUserId()).ifPresent(duplicateUser -> {
            throw new ResourceFoundException("User already exists");
        });
        UserRegistration user = userDataTransfer.insertDataTransfer(userDto);
        UserRegistration userInserted = userRepository.save(user);
        return (userInserted != null && userInserted.getId() !=null) ? 
        		ResponseEntity.status(HttpStatus.CREATED).body("Success: User input was inserted."):
        		ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failure: User input was not inserted.");
	}
	
	@Override
	public ResponseEntity<String> genrateOtp(String userid,String email, String mobile) {
		OneTimePassword oneTimePassword = new OneTimePassword();
		oneTimePassword.setId(userid);
		oneTimePassword.setEmail(email);
		oneTimePassword.setEmailOtp(OTPGenerator.genrateOtp());
		oneTimePassword.setEmailOtpStatus("0");
		oneTimePassword.setMobile(mobile);
		oneTimePassword.setMobileOtp(OTPGenerator.genrateOtp());
		oneTimePassword.setMobileOtpStatus("0");
		oneTimePassword.setOtp_date(LocalDate.now());
		OneTimePassword otpInserted = otpRepository.save(oneTimePassword);
		return (otpInserted != null && otpInserted.getId() != null)?
				ResponseEntity.status(HttpStatus.CREATED).body("Success: OTP was successfully inserted!"):
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure: OTP was not inserted!");
	}
	
	@Override
	public ResponseEntity<String> verifyOtp(String userid,String emailotp,String mobileotp) {
		userRepository.findById(userid).orElseThrow(()-> new UserNotFoundException("User not found exception"));
		Optional<OneTimePassword> otp = otpRepository.findById(userid);
		String mobilestatus="1",emailstatus="1";
		if(otp.stream().allMatch((e)->emailotp.equals(e.getEmailOtp())&&mobileotp.equals(e.getMobileOtp()))) {
			otpRepository.updateOtpStatus(userid,emailstatus,mobilestatus);
			String userStatus="Y";
			userRepository.updateUserStatus(userid,userStatus);
			return ResponseEntity.status(HttpStatus.OK).body("OTP verification successful");
		}else {
			throw new OTPVerificationException("Incorrect Otp");
		}	
	}
	
	@Override
	public ResponseEntity<List<UserDto>> validUser(String userid, String password) {
//		Optional<UserRegistration> user=userRepository.checkUser(userid,password);
		Optional<UserRegistration> user = userRepository.findByIdAndPassword(userid, password);
		UserRegistration validUser=  user.orElseThrow(() -> new UserNotFoundException("User not found."));
		
		if(user.stream().allMatch((e)->e.getStatus().equals("Y"))) {
			return ResponseEntity.status(HttpStatus.OK).body(user.stream()
					.map(this::validUserModelToDto).collect(Collectors.toList()));
		}else if(user.stream().allMatch(e->e.getStatus().equals("N"))) {
			ResponseEntity<String> otp = genrateOtp(validUser.getId(),validUser.getEmail(),validUser.getMobile());
			if(otp.getBody().equals("Success: OTP was successfully inserted!")) {
				throw new UserNotVerifiedException("User not verified with OTP.");
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			}
		}
		return ResponseEntity.ok().build();
	}
	public UserDto validUserModelToDto(UserRegistration user) {
		UserDto userDto = new UserDto(user.getId(),user.getFname(),user.getMname(),user.getLname(),user.getMobile(),user.getEmail());
		userDto.setRoles(user.getRoles().stream().map(role ->{
					Map<String,String> userRole = new HashMap<>();
					userRole.put("roleId",role.getRoles().getId());
					userRole.put("userRole",role.getRoles().getRole_name());
					return userRole;
				}).collect(Collectors.toSet()));
		return userDto;
	}	
	
	@Override
	public ResponseEntity<String> resetPassword(String userid,String password) {
		Optional<UserRegistration> user = userRepository.findById(userid);
		user.orElseThrow( ()->new UserNotFoundException("User not found."));
		int result=userRepository.resetPassword(userid,password);
		return (result > 0) ? 
				ResponseEntity.status(HttpStatus.CREATED).body("Password reset successfully for user: "+userid):
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reset password for user: "+userid);
	}
	
	@Override
	public ResponseEntity<String> checkUser(String userid) {
		Optional<UserRegistration> user= userRepository.findById(userid);
		return (!user.isEmpty() || user != null) ? 
				ResponseEntity.status(HttpStatus.OK).body("User Exists.") :
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not Exists");
	}
	
	@Override
	public boolean upload(MultipartFile file) {
	   return fileuploadhelper.uploadFile(file);
	}
}












