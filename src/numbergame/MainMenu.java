package numbergame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author Yongcong
 */
public class MainMenu extends JPanel {
    
    public MainMenu(){
        setUpMainMenu();
    }
    
    public void setUpMainMenu() {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        
        
        JButton eightByEight = new JButton("8x8");
        
        
        eightByEight.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
              NumberGame m = new NumberGame(8,8);
            }
        });
        p.add(eightByEight);
        f.setSize(200, 200);
        f.setTitle("Number Game");
        f.setLocationRelativeTo(null);
        f.add(p);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
