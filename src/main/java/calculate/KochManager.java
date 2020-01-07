/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import enums.Side;
import fun3kochfractalfx.FUN3KochFractalFX;
import timeutil.TimeStamp;

/**
 *
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager {
    
    private KochFractal koch;
    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;
//    private LeftEdgeCalculation leftEdgeCalculation;
//    private RightEdgeCalculation rightEdgeCalculation;
//    private BottomEdgeCalculation bottomEdgeCalculation;

    private EdgeCalculater leftEdgeCalculation;
    private EdgeCalculater rightEdgeCalculation;
    private EdgeCalculater bottomEdgeCalculation;

    //threadpool
    private ExecutorService executor;

    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        this.koch = new KochFractal();
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();

        this.executor = Executors.newFixedThreadPool(3);
    }
    
    public void changeLevel(int nxt) {
        edges.clear();
        koch.setLevel(nxt);
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");

//        this.leftEdgeCalculation = new LeftEdgeCalculation(koch.getLevel());
//        this.rightEdgeCalculation = new RightEdgeCalculation(koch.getLevel());
//        this.bottomEdgeCalculation  = new BottomEdgeCalculation(koch.getLevel());


          this.leftEdgeCalculation = new EdgeCalculater(Side.left, koch.getLevel());
          this.rightEdgeCalculation = new EdgeCalculater(Side.right,koch.getLevel());
          this.bottomEdgeCalculation  = new EdgeCalculater(Side.bottom,koch.getLevel());

        Future f1 = executor.submit(leftEdgeCalculation);
        Future f2 = executor.submit(rightEdgeCalculation);
        Future f3 = executor.submit(bottomEdgeCalculation);

        while (!f1.isDone() || !f2.isDone() || !f3.isDone()) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s and future 3 is %s",
                            f1.isDone() ? "done" : "not done",
                            f2.isDone() ? "done" : "not done",
                            f3.isDone() ? "done" : "not done"
                    )
            );
        }

        edges.addAll(leftEdgeCalculation.getEdges());
        edges.addAll(rightEdgeCalculation.getEdges());
        edges.addAll(bottomEdgeCalculation.getEdges());

        tsCalc.setEnd("End calculating");
        application.setTextNrEdges("" + koch.getNrOfEdges());
        application.setTextCalc(tsCalc.toString());
        drawEdges();
    }
    
    public void drawEdges() {
        tsDraw.init();
        tsDraw.setBegin("Begin drawing");
        application.clearKochPanel();
        for (Edge e : edges) {
            application.drawEdge(e);
        }
        tsDraw.setEnd("End drawing");
        application.setTextDraw(tsDraw.toString());
    }
}
