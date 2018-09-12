package hugh.oauth.admin.service.imp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hugh.oauth.admin.model.h2.vo.Authentication;
import hugh.oauth.admin.model.mysql.dao.ClientDetailsDao;
import hugh.oauth.admin.model.mysql.vo.ClientDetail;
import hugh.oauth.admin.service.ClientService;

@Service
public class ClientServiceImp implements ClientService {

  @Autowired
  private ClientDetailsDao clientDetailDao;

  @Override
  public Authentication authenticateClient(ClientDetail clientDetail, String... scopes) {
    Authentication authentication = null;
    if (clientDetail != null) {
      Set<String> eligibleScopes = clientDetail.getScopes();
      if (!clientDetail.getAutoapprove()) {
        // validate scopes
        Set<String> requestScopes = new TreeSet<String>(Arrays.asList(scopes));
        Iterator<String> iterator = requestScopes.iterator();
        while (iterator.hasNext()) {
          if (!eligibleScopes.contains(iterator.next())) {
            iterator.remove();
          }
        }
        eligibleScopes = requestScopes;
      }
      authentication = new Authentication();
      authentication.setClientId(clientDetail.getClientId());
      authentication.setResourceIds(clientDetail.getResourceIds());
      authentication.setScopes(eligibleScopes);
    }
    return authentication;
  }

  @Override
  public ClientDetail getClientDetail(String clientId, String clientSecret) {
    return clientDetailDao.getOne(clientId, clientSecret);
  }

}
