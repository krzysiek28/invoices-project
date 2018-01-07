package com.io.invoices.invoiceshibernate.user;

import com.io.invoices.invoiceshibernate.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUsername(user.getUsername());
		user.setEmail(user.getEmail());
		user.setRole(user.getRole());
		user.setEnabled(user.getEnabled());
		applicationUserRepository.save(user);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(user.getUsername()));
	}
	@RequestMapping("/{userId}")
	public ApplicationUser getUser(@PathVariable String userId) {
		return userService.getUser(Integer.parseInt(userId));
	}
}
