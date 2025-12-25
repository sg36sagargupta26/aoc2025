package days.day11;

import java.util.ArrayList;
import java.util.List;

public class GraphNodes {
    private final String name;
    private final List<GraphNodes> children;
    public GraphNodes(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    public void updateChildren(GraphNodes graphNodes){
        children.add(graphNodes);
    }

    public String getName() {
        return name;
    }

    public List<GraphNodes> getChildren() {
        return children;
    }
}
