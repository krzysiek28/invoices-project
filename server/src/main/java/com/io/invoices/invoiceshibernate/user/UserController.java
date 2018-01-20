package com.io.invoices.invoiceshibernate.user;

import com.io.invoices.invoiceshibernate.security.SecurityUtils;
import org.jsoup.Jsoup;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.HEADER_STRING;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;

/**
 * UserController is controller which is responsible for
 * handle all requests user related
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    /**
     *
     * @param applicationUserRepository
     * @param bCryptPasswordEncoder class responsible for encrytping passwords
     * @param userService
     */
    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    /**
     * method which allows user to sign up
     * method checks if user with same data exsist
     * if not create new user
     *
     * @param user json with all user data
     * @param res http response
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user, HttpServletResponse res) {
        if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (applicationUserRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with provided email already exists!");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(Jsoup.parse(user.getUsername()).text());
        user.setEmail(Jsoup.parse(user.getEmail()).text());
        user.setRole(user.getRole());
        user.setEnabled(user.getEnabled());
        user.setPersonalData(Jsoup.parse(user.getPersonalData()).text());
        applicationUserRepository.save(user);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(user.getUsername()));
    }

    /**
     * method finds and returns user by username
     * @param username
     * @return
     */
    @RequestMapping("/{username}")
    public ApplicationUser getUserByUsername(@PathVariable String username) {
        ApplicationUser user = userService.getUserByUsername(username);
        return user;
    }

    /**
     * method finds and reuturns user by id
     *
     * @param userId
     * @return
     */
    @RequestMapping("/id/{userId}")
    public ApplicationUser getUser(@PathVariable String userId) {
        return userService.getUser(Integer.parseInt(userId));
    }
}
