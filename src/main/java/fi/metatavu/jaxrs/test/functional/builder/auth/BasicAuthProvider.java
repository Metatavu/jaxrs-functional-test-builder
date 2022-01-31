package fi.metatavu.jaxrs.test.functional.builder.auth;

/**
 * Implementation of basic auth provider for tests
 * 
 * @author Antti Leppä
 */
public class BasicAuthProvider implements AuthProvider {

  private final String username;
  private final String password;

  /**
   * Constructor
   *
   * @param username username
   * @param password password
   */
  public BasicAuthProvider(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Returns username
   *
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns password
   *
   * @return password
   */
  public String getPassword() {
    return password;
  }
  
}
