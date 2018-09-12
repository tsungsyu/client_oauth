package hugh.oauth.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hugh.oauth.admin.model.h2.vo.Authentication;
import hugh.oauth.admin.model.h2.vo.Token;
import hugh.oauth.admin.model.mysql.vo.ClientDetail;
import hugh.oauth.admin.service.ClientService;
import hugh.oauth.admin.service.TokenService;

@RestController
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;


  @Autowired
  private TokenService tokenService;


  @GetMapping("/")
  public Token test() throws Exception {
    ClientDetail clientDetail = clientService.getClientDetail("order_admin", "HughHugeDick");
    Authentication authentication = clientService.authenticateClient(clientDetail, "read", "wang", "write");
    Token token = tokenService.issueToken(authentication, clientDetail);
    authentication = tokenService.validateToken(token.getAccessToken());
    return tokenService.checkToken(authentication);
  }
}
