package calculate;

import java.util.ArrayList;
import java.util.List;

public class RightEdgeCalculation implements Runnable {

    KochFractal koch;
    List<Edge> edges;

    public List<Edge> getEdges() {
        return edges;
    }


    public RightEdgeCalculation(int lvl) {
        this.koch = new KochFractal();
        this.edges = new ArrayList<>();
        koch.setLevel(lvl);
    }

    @Override
    public void run() {

        try {
            koch.generateRightEdge();
            edges = koch.getEdges();
        }catch (Exception e){

        }
    }
}
