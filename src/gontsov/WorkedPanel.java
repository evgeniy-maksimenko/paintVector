package gontsov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class WorkedPanel extends JPanel implements MouseMotionListener, MouseListener {
    Config conf;
    int x = 0, y = 0;

    public WorkedPanel(Config conf) {
        this.conf = conf;
        setBackground(Color.WHITE);
        setBounds(100, 0, 540, 480);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (conf.type == 1) {
            conf.frame.add(new Frame(x, y, e.getX(), e.getY(), conf.type, conf.color, conf.width));
            x = e.getX();
            y = e.getY();
            this.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (conf.type == 1)
            return;

        conf.frame.add(new Frame(x, y, e.getX(), e.getY(), conf.type, conf.color, conf.width));
        this.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Frame aFrame : conf.frame) {
            g2d.setStroke(new BasicStroke(aFrame.width));
            g2d.setColor(aFrame.color);
            switch (aFrame.type) {
                case 1:
                    g2d.drawLine(aFrame.x1, aFrame.y1, aFrame.x2, aFrame.y2);
                    break;
                case 2:
                    g2d.drawRect(aFrame.x1, aFrame.y1, aFrame.x2, aFrame.y2);
                    break;
                case 3:
                    g2d.drawOval(aFrame.x1, aFrame.y1, aFrame.x2, aFrame.y2);
                    break;
                case 4:
                    g2d.drawRoundRect(aFrame.x1, aFrame.y1, aFrame.x2, aFrame.y2, 30, 30);
                    break;
                case 5:
                    g2d.drawLine(aFrame.x1, aFrame.y1, aFrame.x2, aFrame.y2);
                    break;
            }
        }

    }
}
