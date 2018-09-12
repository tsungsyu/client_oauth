package hugh.oauth.admin.utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtility {

  private MessageDigest shaDigest;

  private MessageDigest md5Digest;

  public EncryptUtility() {
    try {
      shaDigest = MessageDigest.getInstance("SHA-256");
      md5Digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

  }

  public String encryptSha(String plainTxt) {
    String encryptTxt = String.format("%064x", new BigInteger(1, shaDigest.digest(plainTxt.getBytes(StandardCharsets.UTF_8))));
    return encryptTxt;
  }

  public String encryptMd5(String plainTxt) {
    String encryptTxt = String.format("%032x", new BigInteger(1, md5Digest.digest(plainTxt.getBytes(StandardCharsets.UTF_8))));
    return encryptTxt;
  }  

  public static void main(String[] args) {
    String secret = "HughHugeDick";
    System.out.println(new EncryptUtility().encryptSha(secret));
  }

}
