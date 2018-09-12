package hugh.oauth.admin.model.mysql.dao;

import org.apache.ibatis.annotations.Param;

import hugh.oauth.admin.model.mysql.vo.ClientDetail;

public interface ClientDetailsDao {
  ClientDetail getOne(@Param("clientId") String clientId, @Param("clientSecret") String clientSecret);
}
