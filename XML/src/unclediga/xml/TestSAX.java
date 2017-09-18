package unclediga.xml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestSAX {

    private static JPanel rpanel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("test SAX");
        rpanel = new JPanel();
        rpanel.setLayout(new BorderLayout());

        fillPanel();
        frame.add(rpanel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static void fillPanel() {
        JPanel p = new JPanel();
        p.add(new JButton("But 1"));
        JButton b2 = new JButton("But 2");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(SAXVersion.getVersionString());
            };
        });


        p.add(b2);

        rpanel.add(p, BorderLayout.SOUTH);
    }


}


