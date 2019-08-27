
package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class LinesComponent extends JFrame{
    private Client client;
    public LinesComponent(Client client) {
        super("Lines Drawing Demo");
        this.client = client;
        setSize(1000, 900);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private ArrayList<Line2D> makeAllLines(){
        ArrayList<Line2D> arrayList = new ArrayList<>();
        for (int i = 0; i<client.getObjects().size()-1; i++){
            if(client.getObjects().get(i+1).getCommand().equals("move")){
                arrayList.add(new Line2D.Double(client.getObjects().get(i).getX()*getWidth(), client.getObjects().get(i).getY()*getHeight(),
                        client.getObjects().get(i+1).getX()*getWidth(), client.getObjects().get(i+1).getY()*getHeight()));

            }

        }

        return arrayList;
    }



    private void drawLines(Graphics g) throws InterruptedException {
        Graphics2D g2d = (Graphics2D) g;
        for(Line2D line: makeAllLines()){
            g2d.draw(line);
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
