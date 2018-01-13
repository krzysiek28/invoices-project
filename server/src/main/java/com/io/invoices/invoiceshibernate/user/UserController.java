package com.io.invoices.invoiceshibernate.user;

import com.io.invoices.invoiceshibernate.security.SecurityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.HEADER_STRING;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;


@RestController
@RequestMapping("/users")
public class UserController {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
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

    @RequestMapping("/{username}")
    public ApplicationUser getUserByUsername(@PathVariable String username) {
        ApplicationUser user = userService.getUserByUsername(username);
        return user;
    }

    @RequestMapping("/id/{userId}")
    public ApplicationUser getUser(@PathVariable String userId) {
        return userService.getUser(Integer.parseInt(userId));
    }
}
