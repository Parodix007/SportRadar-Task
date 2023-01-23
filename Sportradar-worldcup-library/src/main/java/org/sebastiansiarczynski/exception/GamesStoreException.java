package org.sebastiansiarczynski.exception;

/**
 * Exception thrown when any error occur in the {@link org.sebastiansiarczynski.GamesStore}
 *
 * @author Sebastian Siarczyński
 */
public class GamesStoreException extends Exception {

  public GamesStoreException(final String message) {
    super(message);
  }
}
