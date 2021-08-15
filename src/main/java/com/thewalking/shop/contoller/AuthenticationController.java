package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.User;
import com.thewalking.shop.security.config.TokenProvider;
import com.thewalking.shop.model.AuthToken;
import com.thewalking.shop.dto.LoginUser;
import com.thewalking.shop.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/token")
@Api(value = "Authentication Controller, which manages token generation")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Returns token")
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        System.out.println("new token for " + loginUser.getEmail());

        final Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getEmail(),
                            loginUser.getPassword()
                    )
            );
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email/Password is wrong! Please try again.");
        }
        System.out.println("authentication " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        System.out.println(token);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @ApiOperation(value = "User details")
    @RequestMapping(value = "/getuserdetails", method = RequestMethod.GET)
    public ResponseEntity<User> getUserDetails() throws AuthenticationException {
        System.out.println("email");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        return ResponseEntity.ok(userService.findOne(email));
    }
}
