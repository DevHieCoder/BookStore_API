package com.java.bookStore_api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.java.bookStore_api.entity.Role;
import com.java.bookStore_api.entity.User;
import com.java.bookStore_api.serviceImp.CustomerServiceImp;
import com.java.bookStore_api.serviceImp.RoleServiceImp;
import com.java.bookStore_api.serviceImp.UserServiceImp;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserServiceImp userServiceImp;
	
	@Autowired
	RoleServiceImp roleServiceImp;
	
	@Autowired
	CustomerServiceImp customerServiceImp;
	
	@PostMapping("/register")
	public RedirectView registerUser(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		if (!userServiceImp.existsByEmail(email)) {
			User user = new User();
			user.setUsername(name);
			user.setEmail(email);
			user.setPassword(password);
			
			Role role = new Role();
			role.setId(3);
			user.setRole(role);
			
			userServiceImp.createUser(user);
		} else {
			return new RedirectView("http://localhost:8081/home/register?success=false");
		}
		
		return new RedirectView("http://localhost:8081/home/register?success=true");
	}
	
	@PostMapping("/login")
	public RedirectView login(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam(name = "remember", required = false) String remember, HttpServletResponse response) {

		User user = userServiceImp.findByEmail(email);

		if (user != null) {
			if (user.getPassword().equalsIgnoreCase(password)) {
				if (remember != null) {
					Cookie cookie = new Cookie("email", email);
					cookie.setMaxAge(3600);
					cookie.setPath("/");
					cookie.setDomain("localhost");
					response.addCookie(cookie);
				}
				
				if (user.getRole().getRoleName().equalsIgnoreCase("Admin")) {
					return new RedirectView("http://localhost:8081/adminHome");
				} else if (user.getRole().getRoleName().equalsIgnoreCase("Staff")) {
					return new RedirectView("http://localhost:8081/staffHome");
				} else if (user.getRole().getRoleName().equalsIgnoreCase("Library")) {
					return new RedirectView("http://localhost:8081/libraryHome");
				} 
				
				return new RedirectView("http://localhost:8081/home?userName=" + user.getUsername());
			}
		}
		
		return new RedirectView("http://localhost:8081/home/login?success=false");
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<?> getinfoUsers() {
		List<Map<String, ?>> listUser = userServiceImp.getInfoUsers();
		
		return new ResponseEntity<List<Map<String, ?>>>(listUser, HttpStatus.OK);
	}
	
	@GetMapping("/getUserById")
	public ResponseEntity<?> getUserById(@RequestParam("id") Integer id) {
		List<Map<String, ?>> listUserMaps = userServiceImp.getInfoUsers();

	    Optional<Map<String, ?>> matchedUser = listUserMaps.stream()
	        .filter(userMap -> id == (Integer) userMap.get("id"))
	        .findFirst();

	    return matchedUser.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
	        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/addUser")
	public RedirectView addUser(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
			@RequestParam("userEmail") String userEmail, @RequestParam("roleName") String roleName) {
		
		boolean isSuccess = false;
		
		if (!userServiceImp.existsByEmail(userEmail)) {
			isSuccess = true;
			User user = new User();
			user.setUsername(userName);
			user.setEmail(userEmail);
			user.setPassword(userPassword);
			
			Role role = roleServiceImp.findByRoleName(roleName).get();
			role.setRoleName(roleName);
			user.setRole(role);
			
			userServiceImp.createUser(user);
		}
		
		String redirectUrl = "http://localhost:8081/adminHome/addUser";
		if (!isSuccess) {
			redirectUrl += "?fail=true";
		} else {
			redirectUrl += "?success=true";
		}
		
		return new RedirectView(redirectUrl);
	}
	
	@PostMapping("/updateUser/{id}")
	public RedirectView updateUser(@PathVariable Integer id, @RequestParam("userName") String userName,
			@RequestParam("userPassword") String password, @RequestParam("userEmail") String userEmail, 
			@RequestParam("roleName") String roleName) {
		User user = userServiceImp.getUserById(id).get();
		
		if (!user.getUsername().equalsIgnoreCase(userName) && userServiceImp.existsByUsername(userName)) {
			String redirectUrl = "http://localhost:8081/adminHome/updateUser?id=" + id + "&fail=true";
			return new RedirectView(redirectUrl);
		}
		user.setUsername(userName);
		user.setPassword(password);
		user.setEmail(userEmail);
		Role role = roleServiceImp.findByRoleName(roleName).get();
		role.setRoleName(roleName);
		user.setRole(role);
		
		userServiceImp.createUser(user); 
		
		String redirectUrl = "http://localhost:8081/adminHome/users?updateSuccess=true";
		
		return new RedirectView(redirectUrl);
	}
	
	@Transactional
	@GetMapping("/delUser/{id}")
	public RedirectView delUser(@PathVariable Integer id) {
		customerServiceImp.deleteByUser_Id(id);
		userServiceImp.deleteById(id);
		
		String redirectUrl = "http://localhost:8081/adminHome/users?delSuccess=true";
		return new RedirectView(redirectUrl);
	}
	
	@PostMapping("/changePassword")
	public RedirectView changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
	        @RequestParam("userName") String userName) {
	    User user = userServiceImp.findByUsername(userName);
	    
	    if (user != null && user.getPassword().equals(oldPassword)) {
	        user.setPassword(newPassword);
	        userServiceImp.createUser(user);
	        return new RedirectView("http://localhost:8081/home/changePassword?successMessage=Change password successfully!&userName=" + userName);
	    }

	    return new RedirectView("http://localhost:8081/home/changePassword?errorMessage=The old password is incorrect. Please try again.&userName=" + userName);
	}


	
}
