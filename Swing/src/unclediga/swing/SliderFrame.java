package unclediga.swing;

import com.sun.deploy.util.Property;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class SliderFrame extends JFrame {

    private JPanel sliderPanel;
    private JSlider slider;
    private JSlider slider2;

    public SliderFrame() {

        sliderPanel = new JPanel();

        Hashtable<Integer, Component> ht = new Hashtable<Integer, Component>();
        ht.put(0, new JLabel("One"));
        ht.put(20, new JLabel("Two"));
        ht.put(40, new JLabel("Three"));
        ht.put(60, new JLabel("Four"));
        ht.put(80, new JLabel("Five"));
        ht.put(100, new JLabel("Six"));

        slider = new JSlider();
        slider.setLabelTable(ht);
        addSlider(slider);

        ht = new Hashtable<Integer, Component>();
        ht.put(0, new JLabel(new ImageIcon("img/nine.gif")));
        ht.put(20, new JLabel(new ImageIcon("img/ten.gif")));
        ht.put(40, new JLabel(new ImageIcon("img/jack.gif")));
        ht.put(60, new JLabel(new ImageIcon("img/queen.gif")));
        ht.put(80, new JLabel(new ImageIcon("img/king.gif")));
        ht.put(100, new JLabel(new ImageIcon("img/ace.gif")));

        slider = new JSlider();
        slider.setLabelTable(ht);
        addSlider(slider);

        add(sliderPanel);
        pack();
    }


    private void addSlider(JSlider slider) {

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(20);
        JPanel p = new JPanel();
        p.add(slider);

        sliderPanel.add(slider);
    }


    public static void main(String[] args) {

        JFrame fr = new SliderFrame();
        System.out.println(Paths.get("img/ace.gif").toAbsolutePath());
        Properties p = System.getProperties();
        Set s = p.keySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            String k = (String) it.next();
            if (k.contains("path")) {

                String pp = p.getProperty(k);
                System.out.println("[" + k + "] : " + pp);
            }
        }
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setVisible(true);

    }

}
