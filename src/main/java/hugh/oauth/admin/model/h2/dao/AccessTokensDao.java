package hugh.oauth.admin.model.h2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hugh.oauth.admin.model.h2.vo.AccessToken;

@Repository
public interface AccessTokensDao extends JpaRepository<AccessToken, String> {

  AccessToken getOneByAuthenticationId(String authenticationId);

}
