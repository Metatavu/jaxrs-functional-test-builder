package fi.metatavu.jaxrs.test.functional.builder;

import fi.metatavu.jaxrs.test.functional.builder.auth.AuthorizedTestBuilderAuthentication;
import fi.metatavu.jaxrs.test.functional.builder.auth.InvalidAccessTokenProvider;
import fi.metatavu.jaxrs.test.functional.builder.auth.NullAccessTokenProvider;

/**
 * Abstract test builder class for access token based builders
 *
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class AbstractAccessTokenTestBuilder<C> extends AbstractTestBuilder<C> {

  public AuthorizedTestBuilderAuthentication<C> invalid = createTestBuilderAuthentication(this, new InvalidAccessTokenProvider());

  public AuthorizedTestBuilderAuthentication<C> anonymous = createTestBuilderAuthentication(this, new NullAccessTokenProvider());

}
