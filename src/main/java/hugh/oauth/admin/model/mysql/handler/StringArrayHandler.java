package hugh.oauth.admin.model.mysql.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;


public class StringArrayHandler extends BaseTypeHandler<Set<String>> {

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Set<String> s, JdbcType jdbcType) throws SQLException {
    String setString = null;
    if (!StringUtils.isEmpty(s)) {
      StringBuilder sb = new StringBuilder();
      for (String element : s) {
        sb.append(element).append(",");
      }
      setString = sb.toString();
    }
    preparedStatement.setString(i, setString);
  }

  @Override
  public Set<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    String src = resultSet.getString(s);
    if (resultSet.wasNull()) {
      return null;
    } else {
      return toSet(src);
    }
  }

  @Override
  public Set<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    String src = resultSet.getString(i);
    if (resultSet.wasNull()) {
      return null;
    } else {
      return toSet(src);
    }
  }

  @Override
  public Set<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    String src = callableStatement.getString(i);
    if (callableStatement.wasNull()) {
      return null;
    } else {
      return toSet(src);
    }
  }

  private Set<String> toSet(String s) {
    String[] arr = s.split(",");
    return new TreeSet<String>(Arrays.asList(arr));
  }
}
