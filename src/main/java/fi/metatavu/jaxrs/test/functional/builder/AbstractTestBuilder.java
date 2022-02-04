package fi.metatavu.jaxrs.test.functional.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fi.metatavu.jaxrs.test.functional.builder.auth.AuthProvider;
import fi.metatavu.jaxrs.test.functional.builder.auth.AuthorizedTestBuilderAuthentication;

/**
 * Test builder class
 *
 * @param <C> API Client class
 * @param <P> Auth provider
 * @author Antti Leppä
 */
public abstract class AbstractTestBuilder <C, P extends AuthProvider> implements AutoCloseable {

  private List<CloseableResource<?>> closables = new ArrayList<>();

  /**
   * Creates new authorized test builder authentication
   * 
   * @param abstractTestBuilder this instance
   * @param authProvider auth provider
   * @return initialized test builder authentication
   */
  public abstract AuthorizedTestBuilderAuthentication<C, P> createTestBuilderAuthentication(AbstractTestBuilder<C, P> abstractTestBuilder, P authProvider);

  /**
   * Adds closable to clean queue
   * 
   * @param closable closable
   * @return given instance
   * @param <T> closeable resource type
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
    closables = closables.stream().filter((closeable) -> !predicate.test(closeable.getResource())).collect(Collectors.toList());
  }

  @Override
  public void close() throws Exception {
    for (int i = closables.size() - 1; i >= 0; i--) {
      closables.get(i).close();
    }
  }

}