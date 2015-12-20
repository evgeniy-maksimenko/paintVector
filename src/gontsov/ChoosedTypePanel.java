package gontsov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosedTypePanel extends JPanel {
    Config conf;

    public ChoosedTypePanel(Config conf) {
        this.conf = conf;
        setBackground(Color.LIGHT_GRAY);
        setBounds(0, 0, 100, 480);


        String[] btns = {"fozzy", "rect", "oval", "round rect", "line"};

        for (int i = 0; i < btns.length; i++) {
            JButton btn = new JButton(btns[i]);
            btn.addActionListener(new TypeAction());
            btn.setActionCommand("" + (i + 1));
            add(btn);
        }

        JButton btnColor = new JButton("color");
        btnColor.addActionListener(new BtnColor());
        add(btnColor);

        JButton btnWidth = new JButton("width");
        btnWidth.addActionListener(new BtnWidth());
        add(btnWidth);


    }

    private class TypeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conf.type = Integer.parseInt(e.getActionCommand());
        }
    }

    private class BtnColor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JColorChooser colorChooser = new JColorChooser();
            conf.color = colorChooser.showDialog(null, "Choose color", conf.color);
        }
    }

    private class BtnWidth implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conf.width = 10;
        }
    }
}
