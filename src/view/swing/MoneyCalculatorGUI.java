package view.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoneyCalculatorGUI extends JFrame{

    public MoneyCalculatorGUI(JPanel jpanel, String title) {
        super(title);
        getContentPane().add(jpanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
