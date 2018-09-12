package hugh.oauth.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import hugh.oauth.admin.utility.EncryptUtility;

@Configuration
public class BeansConfig {

  @Bean(name = "encryptUtility")
  public EncryptUtility encryptUtility() {
    return new EncryptUtility();
  }

  @Bean(name = "objectMapper")
  public ObjectMapper objectMapper() {
    return new ObjectMapper().setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
  }
}
