package popsim;

import java.util.ArrayList;
import java.util.List;

public class PopSim {
  List<Entity> agents = new ArrayList<>();

  public static void main(String[] args) {

  }

  private void process() {
    fetchAgents().forEach(agent -> {

    });
  }

  private List<Entity> fetchAgents(){
    return agents;
  }
}
