package org.sebastiansiarczynski.exception;

/**
 * Exception thrown when cannot create any type of the Game DTO
 *
 * @author Sebastian Siarczyński
 */
public class GameCreatingException extends RuntimeException {

  public GameCreatingException(final String message) {
    super(message);
  }
}
