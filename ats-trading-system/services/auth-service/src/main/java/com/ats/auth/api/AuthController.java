// com/ats/auth/api/AuthController.java
package com.ats.auth.api;
import org.springframework.web.bind.annotation.*; record LoginReq(String username,String password){}
record TokenRes(String accessToken,String refreshToken){}
@RestController @RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login") public TokenRes login(@RequestBody LoginReq req){
        return new TokenRes("dev-access-token","dev-refresh-token"); // Phase 1: real JWT
    }
}
