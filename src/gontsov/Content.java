package gontsov;

import javax.swing.*;

public class Content extends JFrame {
    public Content() {
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 640, 480);

        Config conf = new Config();

        WorkedPanel workedPanel = new WorkedPanel(conf);
        setJMenuBar(new FileMenu(conf, workedPanel));
        add(new ChoosedTypePanel(conf));
        add(workedPanel);


        setVisible(true);
    }
}
