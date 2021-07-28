package com.thewalking.shop.contoller;

import com.thewalking.shop.security.config.TokenProvider;
import com.thewalking.shop.model.AuthToken;
import com.thewalking.shop.dto.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/token")
@Api(value = "Authentication Controller, which manages token generation")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @ApiOperation(value = "Returns token")
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        System.out.println("new token: " + loginUser.getEmail());

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
//        System.out.println("authentication " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        System.out.println(token);
        return ResponseEntity.ok(new AuthToken(token));
    }

//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUser)
//                .map(user -> {
//                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//                        "Refresh token is not in database!"));
//    }


    @ApiOperation(value = "Returns token")
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(@RequestBody String token) throws AuthenticationException {


        System.out.println("refreshed: " + token);
        return ResponseEntity.ok(new AuthToken(token));
    }

//    @ApiOperation(value = "Returns token")
//    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshToken() throws AuthenticationException {
//        System.out.println("refresh called");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        final String token = jwtTokenUtil.generateToken(authentication);
//        System.out.println("refreshed: " + token);
//        return ResponseEntity.ok(new AuthToken(token));
//    }

    @ApiOperation(value = "Log out")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout() throws AuthenticationException {
        System.out.println("logout called");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        final String token = jwtTokenUtil.generateToken(authentication);
        System.out.println("refreshed: " + token);
        return ResponseEntity.ok(new AuthToken(token));
    }
}
