package com.customer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.omg.CORBA.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.customer.dto.ChangePasswordDTO;
import com.customer.dto.ForgotPasswordDTO;
import com.customer.dto.Role;
import com.customer.dto.UserDTO;
import com.customer.entity.Login;
import com.customer.entity.User;
import com.customer.entity.UserResponse;
import com.customer.enumValue.EnumData;
import com.customer.exception.RoleNotFoundException;
import com.customer.exception.UserNotFoundException;
import com.customer.repo.LoginRepository;
import com.customer.repo.UserRepo;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private LoginRepository loginrepo;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment env;
	@Autowired
	private RoleClient roleclient;
	

	@Override
	public UserResponse registerUserInfo(User user) {
		System.out.println("In Customer Service start");
		UserResponse userResponse = new UserResponse();
		boolean isExist = getUserbyEmail(user.getEmail());
		if (isExist) {
			userResponse.setUsername(user.getEmail());
			userResponse.setMessage("User already exist");
			return userResponse;
		}
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String strdate = formatter.format(date);
		System.out.println(strdate);
		user.setCreatedDate(strdate);
		boolean flag = false;
		EnumData status = EnumData.Active;
		if ("A".equals(status.getValue())) {
			flag = true;
		}
		user.setStatus(flag);
		user.setCreatedby(user.getUsername());
		
		Login login=new Login();
        login.setUsername(user.getUsername());
        login.setEmail(user.getEmail());
        login.setPassword(user.getPassword());
        user.setLogin(login);
        login.setUser(user);
        User user1 = userRepo.save(user);
		if (user1 != null) {
			userResponse.setUsername(user1.getUsername());
			userResponse.setMessage("Thank You!! User created Successfully");
		} else {
			userResponse.setMessage(" User not registered");
		}

		System.out.println("In Customer Service end");
		return userResponse;

	}

	private boolean getUserbyEmail(String email) {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;

	}

	@Override
	public ResponseEntity<?> getUserDataByEmail(String email) {
		System.out.println("In customer service getting user start");
		User user = userRepo.findByEmail(email);
		if (user != null) {
			UserDTO userDto= UserDTO.builder().id(user.getId()).fname(user.getFname()).lname(user.getLname())
					.email(user.getEmail()).build();
			UserDTO userDto2=user.convertusertouserdto(user);
			return new ResponseEntity<UserDTO>(userDto2, HttpStatus.OK);
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(email);
		userResponse.setMessage("Email does not exist");
		System.out.println("In customer service getting user end");
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserDataById(int id) {
		System.out.println("In customer service getting user by Id start");
		User user = userRepo.findById(id).get();
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setMessage("UserId does not exist");
		System.out.println("In customer service getting user by Id end");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserDataByUsername(String username) {
		System.out.println("In customer service getting user by Username start");
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(username);
		userResponse.setMessage("Username does not exist");
		System.out.println("In customer service getting user by Username end");
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> updateUserById(String username, User user) {
		System.out.println("In customer service updating user by Username start");
		User existinguser = userRepo.findByUsername(username);
		if (existinguser != null) {
			// existinguser.setAddress(user.getAddress());
			User newuser = userRepo.save(existinguser);
			return new ResponseEntity<User>(newuser, HttpStatus.OK);
		}
		System.out.println("In customer service updating user by username end");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> softdeleteUserById(int id) {
		System.out.println("In customer service soft deleting user by Username start");
		User user = userRepo.findById(id).get();
		if (user != null) {
			user.setStatus(false);
			userRepo.save(user);
			System.out.println("User set to non-active");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		UserResponse userresponse = new UserResponse();
		userresponse.setMessage("User does not exist");
		System.out.println("In customer service soft deleting user by Username end");
		return new ResponseEntity<UserResponse>(userresponse, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<UserResponse> forgotpassword(ForgotPasswordDTO forgot) {
		UserResponse userresponse = new UserResponse();
		User user=userRepo.findByUsernameAndEmail(forgot.getUsername(), forgot.getEmail());
		if(user == null) {
			userresponse.setMessage("User does not exist");
			return new ResponseEntity<UserResponse>(userresponse,HttpStatus.NOT_FOUND);
		}
		if(!forgot.getNewpassword().equals(forgot.getConfirmpassword())) {
			userresponse.setMessage("password does'nt match");
			return new ResponseEntity<UserResponse>(userresponse,HttpStatus.NOT_ACCEPTABLE);
		}
		user.setPassword(forgot.getNewpassword());
		userRepo.save(user);
		userresponse.setUsername(forgot.getUsername());
		userresponse.setMessage("Password changed successfully!!");
		return new ResponseEntity<UserResponse>(userresponse,HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<UserResponse> changePassword(ChangePasswordDTO change) {
		System.out.println("In customer service change password by Username and Email start");
		UserResponse response=new UserResponse();
		User user=userRepo.findByUsernameAndEmail(change.getUsername(), change.getEmail());
		if(user == null) {
			response.setMessage("user does not exist");
			return new ResponseEntity<UserResponse>(response,HttpStatus.NOT_FOUND);
		}
		if(!user.getPassword().equals(change.getOldpassword())) {
			response.setMessage("Old passsword does'nt match");
			return new ResponseEntity<UserResponse>(response,HttpStatus.NOT_ACCEPTABLE);
		}
		if(!change.getNewpassword().equals(change.getConfirmpassword())) {
			response.setMessage("new password doe'nt match with confirm password");
			return new ResponseEntity<UserResponse>(response,HttpStatus.NOT_ACCEPTABLE);	
		}
		if(user.getPassword().equals(change.getNewpassword())) {
			response.setMessage("new password cannot be same as old password");
			return new ResponseEntity<UserResponse>(response,HttpStatus.NOT_ACCEPTABLE);
		}
		user.setPassword(change.getNewpassword());
		userRepo.save(user);
		response.setUsername(change.getUsername());
		response.setMessage("Password changed successfullly!!");
		System.out.println("In customer service change password by Username and Email end");
		return new ResponseEntity<UserResponse>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> assignRoletoUser(String username, String rolename) {
			User user=userRepo.findByUsername(username);
			if(user == null || username.isEmpty()) {
				throw new UserNotFoundException("User not found");
			}
			Role role=roleclient.getRole(rolename);
			if(role==null || rolename.isEmpty()) {
				throw new RoleNotFoundException("Role does not exist");
			}
			user.setRolename(role.getRolename());
			userRepo.save(user);
			
		return new ResponseEntity<>("Role assigned successfully!!",HttpStatus.OK);
	}

	@Override
	public List<String> getAllUsernames() {
		List<String> usernames=loginrepo.findByAllUsernames();
		return usernames;
	}
	
}
