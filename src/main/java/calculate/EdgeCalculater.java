package calculate;

import enums.Side;

import java.util.ArrayList;
import java.util.List;

public class EdgeCalculater implements Runnable{

    KochFractal koch;
    List<Edge> edges;
    Side side;

    public List<Edge> getEdges() {
        return edges;
    }


    public EdgeCalculater(Side side, int lvl) {
        this.side = side;
        this.koch = new KochFractal();
        this.edges = new ArrayList<>();
        koch.setLevel(lvl);
    }

    @Override
    public void run() {

        try {
            switch (side) {
                case left:
                    koch.generateLeftEdge();
                    edges = koch.getEdges();
                    break;
                case right:
                    koch.generateRightEdge();
                    edges = koch.getEdges();
                    break;
                case bottom:
                    koch.generateBottomEdge();
                    edges = koch.getEdges();
                    break;
            }

        }catch (Exception e){

        }
    }
}
