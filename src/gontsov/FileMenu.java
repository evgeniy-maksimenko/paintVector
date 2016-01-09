package gontsov;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gontsov.utils.CsvFileReader;
import gontsov.utils.CsvFileWriter;
import org.ho.yaml.YamlDecoder;
import org.ho.yaml.YamlEncoder;


public class FileMenu extends JMenuBar {
    Config conf;
    WorkedPanel workedPanel;

    private final String JSONFile = "dump.json";
    private final String XMLFile  = "dump.xml";
    private final String CSVFile  = "dump.csv";
    private final String YAMLFile = "dump.yaml";

    public FileMenu(Config conf, WorkedPanel workedPanel) {
        this.conf = conf;
        this.workedPanel = workedPanel;

        JMenu menu = new JMenu("file");
        add(menu);

        JMenu jsonMenu = new JMenu("json");
        menu.add(jsonMenu);

        JMenuItem toJsonItem = new JMenuItem("toJson");
        toJsonItem.addActionListener(new ToJsonItemAction());
        jsonMenu.add(toJsonItem);

        JMenuItem fromJsonItem = new JMenuItem("fromJson");
        fromJsonItem.addActionListener(new FromJsonItemAction());
        jsonMenu.add(fromJsonItem);


        JMenu xmlMenu = new JMenu("xml");
        menu.add(xmlMenu);

        JMenuItem toXMLItem = new JMenuItem("toXML");
        toXMLItem.addActionListener(new ToXMLItemAction());
        xmlMenu.add(toXMLItem);

        JMenuItem fromXMLItem = new JMenuItem("fromXML");
        fromXMLItem.addActionListener(new FromXMLItemAction());
        xmlMenu.add(fromXMLItem);


        JMenu csvMenu = new JMenu("csv");
        menu.add(csvMenu);

        JMenuItem toCSVItem = new JMenuItem("toCSV");
        toCSVItem.addActionListener(new ToCSVItemAction());
        csvMenu.add(toCSVItem);

        JMenuItem fromCSVItem = new JMenuItem("fromCSV");
        fromCSVItem.addActionListener(new FromCSVItemAction());
        csvMenu.add(fromCSVItem);


        JMenu yamlMenu = new JMenu("yaml");
        menu.add(yamlMenu);

        JMenuItem toYamltem = new JMenuItem("toYaml");
        toYamltem.addActionListener(new ToYamlItemAction());
        yamlMenu.add(toYamltem);

        JMenuItem fromYamlItem = new JMenuItem("fromYaml");
        fromYamlItem.addActionListener(new FromYamlItemAction());
        yamlMenu.add(fromYamlItem);

    }

    private class ToJsonItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String json = new Gson().toJson(conf.frame);
            File newTextFile = new File(JSONFile);
            FileWriter fw;
            try {
                fw = new FileWriter(newTextFile);
                fw.write(json);
                fw.flush();
                fw.close();
                JOptionPane.showMessageDialog(null, "Success save to file " + JSONFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class FromJsonItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(JSONFile));
                Gson gson = new Gson();
                ArrayList<Frame> result = gson.fromJson(reader, new TypeToken<ArrayList<Frame>>() {
                }.getType());
                for (Frame aFrame : result) {
                    conf.frame.add(new Frame(aFrame.x1, aFrame.y1, aFrame.x2, aFrame.y2, aFrame.type, aFrame.color, aFrame.width));
                }

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            workedPanel.repaint();
        }
    }

    private class ToXMLItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(XMLFile));
                xmlEncoder.writeObject(conf.frame);
                xmlEncoder.flush();
                xmlEncoder.close();
                JOptionPane.showMessageDialog(null, "Success save to file " + XMLFile);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
    }

    private class FromXMLItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(XMLFile));
                conf.frame = (ArrayList<Frame>) xmlDecoder.readObject();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            workedPanel.repaint();
        }
    }

    private class ToCSVItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CsvFileWriter csvFileWriter = new CsvFileWriter(conf);
            csvFileWriter.writeCsvFile(CSVFile);
        }
    }

    private class FromCSVItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CsvFileReader csvFileReader = new CsvFileReader(conf);
            csvFileReader.readCsvFile(CSVFile);
            workedPanel.repaint();
        }
    }


    private class ToYamlItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                YamlEncoder enc = new YamlEncoder(new FileOutputStream(YAMLFile));
                enc.writeObject(conf.frame);
                enc.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class FromYamlItemAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                YamlDecoder dec = new YamlDecoder(new FileInputStream(YAMLFile));
                conf.frame = (ArrayList<Frame>) dec.readObject();
                dec.close();
            } catch (EOFException e1) {
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            workedPanel.repaint();
        }
    }
}
