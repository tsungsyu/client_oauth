package hugh.oauth.admin.service.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import hugh.oauth.admin.model.h2.dao.AccessTokensDao;
import hugh.oauth.admin.model.h2.vo.AccessToken;
import hugh.oauth.admin.model.h2.vo.Authentication;
import hugh.oauth.admin.model.h2.vo.Token;
import hugh.oauth.admin.model.mysql.vo.ClientDetail;
import hugh.oauth.admin.service.TokenService;
import hugh.oauth.admin.utility.EncryptUtility;

@Service
public class TokenServiceImp implements TokenService {

  @Autowired
  private EncryptUtility encryptUtility;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private AccessTokensDao accessTokensDao;

  @Override
  public Token issueToken(Authentication authentication, ClientDetail clientDetail) throws Exception {
    GregorianCalendar calendar = new GregorianCalendar();
    String tokenValue = UUID.randomUUID().toString();
    // TODO refresh token if necessary
    Token token = new Token();
    Date now = calendar.getTime();
    calendar.add(Calendar.SECOND, clientDetail.getAccessTokenValidity());
    token.setAccessToken(tokenValue);
    token.setExpiration(calendar.getTime());
    token.setScopes(authentication.getScopes());
    token.setTokenType("Bearer");

    AccessToken accessToken = new AccessToken();
    accessToken.setAuthentication(authentication);
    accessToken.setAuthenticationId(encryptUtility.encryptMd5(objectMapper.writeValueAsString(authentication)));
    accessToken.setToken(token);
    accessToken.setTokenId(encryptUtility.encryptMd5(tokenValue));
    accessToken.setClientId(authentication.getClientId());
    accessToken.setUserId(authentication.getUserId());
    accessToken.setCreateTime(now);

    accessTokensDao.save(accessToken);
    return token;
  }

  @Override
  public Token checkToken(Authentication authentication) throws Exception {
    Token token = null;
    String authenticationId = encryptUtility.encryptMd5(objectMapper.writeValueAsString(authentication));
    AccessToken accessToken = accessTokensDao.getOneByAuthenticationId(authenticationId);
    GregorianCalendar calendar = new GregorianCalendar();
    Date now = calendar.getTime();
    calendar.setTime(accessToken.getToken().getExpiration());
    if (now.before(calendar.getTime())) {
      token = accessToken.getToken();
    }
    return token;
  }

  @Override
  public Boolean revokeToken(String accessToken) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Authentication validateToken(String accessToken) {
    Authentication authentication = null;
    AccessToken token = accessTokensDao.getOne(encryptUtility.encryptMd5(accessToken));
    if (token != null) {
      authentication = token.getAuthentication();
    }
    return authentication;
  }



}
