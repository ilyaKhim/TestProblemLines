
package Client;
import Server.SeparateFile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class LinesComponent extends JFrame {

    public LinesComponent() {
        super("Lines Drawing Demo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new LinesComponent().setVisible(true);
//            }
//        });

    }



    void drawLines(Graphics g) throws InterruptedException {

        ArrayList<Line2D> arrayList = new ArrayList<>();
        arrayList.add(new Line2D.Double(100,200,200,300));
        arrayList.add(new Line2D.Double(200,100,300,150));
        arrayList.add(new Line2D.Double(50,70,100,250));
        Graphics2D g2d = (Graphics2D) g;
        Thread.sleep(500);
        for(Line2D line: arrayList){ //makeAllLines
            g2d.draw(line);
            Thread.sleep(1000);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        try {
            drawLines(g);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
