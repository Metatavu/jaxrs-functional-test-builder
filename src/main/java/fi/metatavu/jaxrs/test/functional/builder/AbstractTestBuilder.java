package fi.metatavu.jaxrs.test.functional.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fi.metatavu.jaxrs.test.functional.builder.auth.AccessTokenProvider;
import fi.metatavu.jaxrs.test.functional.builder.auth.AuthorizedTestBuilderAuthentication;
import fi.metatavu.jaxrs.test.functional.builder.auth.InvalidAccessTokenProvider;
import fi.metatavu.jaxrs.test.functional.builder.auth.NullAccessTokenProvider;

/**
 * Test builder class
 * 
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class AbstractTestBuilder <C> implements AutoCloseable {

  private AuthorizedTestBuilderAuthentication<C> invalid;
  private AuthorizedTestBuilderAuthentication<C> anonymous;
  private List<CloseableResource<?>> closables = new ArrayList<>();

  /**
   * Creates new authorized test builder authentication
   * 
   * @param abstractTestBuilder this instance
   * @param accessTokenProvider access token provider
   * @return initialized test builder authentication
   */
  public abstract AuthorizedTestBuilderAuthentication<C> createTestBuilderAuthentication(AbstractTestBuilder<C> abstractTestBuilder, AccessTokenProvider accessTokenProvider);

  /**
   * Returns authentication resource with invalid token
   * 
   * @return authentication resource with invalid token
   */
  public AuthorizedTestBuilderAuthentication<C> invalid() {
    if (invalid != null) {
      return invalid;
    }

    return invalid = createTestBuilderAuthentication(this, new InvalidAccessTokenProvider());
  }

  /**
   * Returns authentication resource without token
   * 
   * @return authentication resource without token
   */
  public AuthorizedTestBuilderAuthentication<C> anonymous() {
    if (anonymous != null) {
      return anonymous;
    }

    return anonymous = createTestBuilderAuthentication(this, new NullAccessTokenProvider());
  }

  /**
   * Adds closable to clean queue
   * 
   * @param closable closable
   * @return given instance
   */
  public <T extends CloseableResource<?>> T addClosable(T closable) {
    closables.add(closable);
    return closable;
  }

  /**
   * Removes a closable from clean queue
   * 
   * @param predicate filter predicate
   */
  protected void removeCloseable(Predicate<Object> predicate) {
    closables = closables.stream().filter((closeable) -> {
      return !predicate.test(closeable.getResource());
    }).collect(Collectors.toList());
  }

  @Override
  public void close() throws Exception {
    for (int i = closables.size() - 1; i >= 0; i--) {
      closables.get(i).close();
    }
  }

}