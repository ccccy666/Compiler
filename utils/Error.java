package utils;

public class Error extends RuntimeException {
  public String message;
  public Position pos;

  public Error(Position pos, String message) {
    this.message = message;
    this.pos = pos;
  }

  @Override
  public String toString() {
    return "Error: " + message + " at " + pos.toString();
  }
}