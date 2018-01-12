package com.io.invoices.invoiceshibernate.user;

import com.io.invoices.invoiceshibernate.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.HEADER_STRING;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;


@RestController
@RequestMapping("/users")
public class UserController {

	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	public UserController(ApplicationUserRepository applicationUserRepository,
						  BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user, HttpServletResponse res) {
		if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
			throw new IllegalArgumentException("Username already exists!");
		}
		if (applicationUserRepository.findByEmail(user.getEmail()) != null) {
			throw new IllegalArgumentException("User with provided email already exists!");
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUsername(user.getUsername());
		user.setEmail(user.getEmail());
		user.setRole(user.getRole());
		user.setEnabled(user.getEnabled());
		user.setPersonalData(user.getPersonalData());
		applicationUserRepository.save(user);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(user.getUsername()));
	}

	@PostMapping("/sign-in")
	public void signIn(@RequestBody ApplicationUser futureUser, HttpServletResponse res) throws IOException {
		//pobieramy usera
		//pobieramy hasło usera
		//porównujemy go z wysłanym
		//przesyłamy token
		ApplicationUser user = userService.getUserByUsername(futureUser.getUsername());
		//ApplicationUser user = applicationUserRepository.findByUsername(futureUser.getUsername());
		if(bCryptPasswordEncoder.matches(futureUser.getPassword(), user.getPassword()))
			res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(user.getUsername()));
		else
			res.sendError(403);
	}
	@RequestMapping("/{username}")
	public ApplicationUser getUserByUsername(@PathVariable String username){
		ApplicationUser user = userService.getUserByUsername(username);
		return user;
	}

	@RequestMapping("/id/{userId}")
	public ApplicationUser getUser(@PathVariable String userId) {
		return userService.getUser(Integer.parseInt(userId));
	}
}
