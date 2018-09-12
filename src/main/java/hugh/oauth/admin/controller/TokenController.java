package hugh.oauth.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hugh.oauth.admin.model.h2.vo.Authentication;
import hugh.oauth.admin.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @GetMapping("/")
  public Authentication authenticate(@RequestHeader("Authorization") String accessToken) {
    return tokenService.validateToken(accessToken);
  }

}
