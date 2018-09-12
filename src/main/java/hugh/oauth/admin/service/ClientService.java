package hugh.oauth.admin.service;

import hugh.oauth.admin.model.h2.vo.Authentication;
import hugh.oauth.admin.model.mysql.vo.ClientDetail;

public interface ClientService {

  Authentication authenticateClient(ClientDetail clientDetail, String... scopes);

  ClientDetail getClientDetail(String clientId, String clientSecret);

}
