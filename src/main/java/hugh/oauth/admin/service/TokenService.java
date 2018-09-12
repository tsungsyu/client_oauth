package hugh.oauth.admin.service;

import hugh.oauth.admin.model.h2.vo.Authentication;
import hugh.oauth.admin.model.h2.vo.Token;
import hugh.oauth.admin.model.mysql.vo.ClientDetail;

public interface TokenService {

  Token issueToken(Authentication authentication, ClientDetail clientDetail) throws Exception;

  Token checkToken(Authentication authentication) throws Exception;

  Boolean revokeToken(String accessToken);

  Authentication validateToken(String accessToken);
}
