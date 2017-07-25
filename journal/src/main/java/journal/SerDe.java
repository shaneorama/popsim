package journal;

public interface SerDe<T> {
  byte[] serialize(T t);
  T deserialize(byte[] bytes);
  int size();
}
