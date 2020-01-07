package calculate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LeftEdgeCalculation implements Runnable {

    KochFractal koch;
    List<Edge> edges;

    public List<Edge> getEdges() {
        return edges;
    }


    public LeftEdgeCalculation( int lvl) {
        this.koch = new KochFractal();
        this.edges = new ArrayList<>();
        koch.setLevel(lvl);
    }

    @Override
    public void run() {

        try {
            koch.generateLeftEdge();
            edges = koch.getEdges();
        }catch (Exception e){

        }
    }
}
