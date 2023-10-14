public class Play {

  public String name;
  public Type type;

  public Play(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  public enum Type {
    COMEDY,
    TRAGEDY,
    HISTORY,
    PASTORAL
  }
}
