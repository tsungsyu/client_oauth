package hugh.oauth.admin.model.mysql;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("hugh.oauth.admin.model.mysql.dao")
public class MysqlDataSourceConfig {

  @Bean(name = "mysqlDataSource")
  @ConfigurationProperties(prefix = "spring.mysql.datasource")
  public DataSource mysqlDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "mysqlSessionFactory")
  public SqlSessionFactoryBean mysqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/mysql/dao/xml/*.xml"));
    sessionFactory.setDataSource(mysqlDataSource());
    sessionFactory.setTypeAliasesPackage("hugh.oauth.admin.model.mysql.vo");
    return sessionFactory;
  }

  @Bean(name = "mysqlTransactionManager")
  public DataSourceTransactionManager mysqlTransactionManager() throws NamingException {
    return new DataSourceTransactionManager(mysqlDataSource());
  }
}
