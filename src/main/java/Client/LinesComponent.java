
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

        setSize(480, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    void drawLines(Graphics g) throws InterruptedException {
        Graphics2D g2d = (Graphics2D) g;
        for(Line2D line: makeAllLines()){
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinesComponent().setVisible(true);
            }
        });
    }
}
