package popsim;

import java.util.EnumMap;

public class Entity {
  public int id;
  public EnumMap<Resource, Integer> resources = new EnumMap<>(Resource.class);
}
