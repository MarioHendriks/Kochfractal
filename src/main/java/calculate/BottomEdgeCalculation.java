package calculate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BottomEdgeCalculation implements Runnable {

    KochFractal koch;
    List<Edge> edges;

    public List<Edge> getEdges() {
        return edges;
    }


    public BottomEdgeCalculation(int lvl) {
        this.koch = new KochFractal();
        this.edges = new ArrayList<>();
        koch.setLevel(lvl);
    }

    @Override
    public void run() {

        try {
            koch.generateBottomEdge();
            edges = koch.getEdges();
        }catch (Exception e){

        }
    }
}
