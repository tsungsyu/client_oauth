package hugh.oauth.admin.model.h2.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "access_tokens")
public class AccessToken implements Serializable {

  @JsonIgnore
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "token_id")
  private String tokenId;

  @Lob
  @Column(name = "token")
  private Token token;

  @Column(name = "authentication_id", unique = true)
  private String authenticationId;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "client_id")
  private String clientId;

  @Lob
  @Column(name = "authentication")
  private Authentication authentication;

  @Column(name = "refresh_token_id")
  private String refreshTokenId;

  @Column(name = "create_time")
  private Date createTime;

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }

  public String getAuthenticationId() {
    return authenticationId;
  }

  public void setAuthenticationId(String authenticationId) {
    this.authenticationId = authenticationId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Authentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }

  public String getRefreshTokenId() {
    return refreshTokenId;
  }

  public void setRefreshTokenId(String refreshTokenId) {
    this.refreshTokenId = refreshTokenId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
