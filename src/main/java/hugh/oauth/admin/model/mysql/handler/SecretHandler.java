package hugh.oauth.admin.model.mysql.handler;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;


public class SecretHandler extends BaseTypeHandler<String> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      s = StringUtils.isEmpty(s) ? s : String.format("%032x", new BigInteger(1, digest.digest(s.getBytes(StandardCharsets.UTF_8))));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    preparedStatement.setString(i, s);
  }

  @Override
  public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
    String src = resultSet.getString(s);
    if (resultSet.wasNull()) {
      return null;
    } else {
      return src;
    }
  }

  @Override
  public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
    String src = resultSet.getString(i);
    if (resultSet.wasNull()) {
      return null;
    } else {
      return src;
    }
  }

  @Override
  public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    String src = callableStatement.getString(i);
    if (callableStatement.wasNull()) {
      return null;
    } else {
      return src;
    }
  }
}
