import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame implements ActionListener, Runnable {
    private int x, y, r;
    private JButton buttonStart, buttonStop;

    public Test() {
        setLayout(null);
        buttonStart = new JButton("Start");
        buttonStop = new JButton("Stop");
        buttonStart.setSize(100, 20);
        buttonStop.setSize(100, 20);
        buttonStart.setLocation(50, 50);
        buttonStop.setLocation(200, 50);
        add(buttonStart);
        add(buttonStop);
        x = 40;
        y = 100;
        r = 50;
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        buttonStart.addActionListener(this);
        buttonStop.addActionListener(this);   
        buttonStop.setEnabled(false);  
    }

    public static void main(String[] args) {
        new Test();
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        g.setColor(Color.red);
        g.fillRect(80, 150, 200, 200);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, r, r);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource().equals(buttonStart)){
            Thread t=new Thread(this);
            t.start();
            buttonStop.setEnabled(true);
            buttonStart.setEnabled(false); 
        }
        if(e.getSource().equals(buttonStop)){
            Thread t=new Thread(this);
            buttonStop.setEnabled(false);
            buttonStart.setEnabled(true); 
            try {
                t.join();                
            } catch (InterruptedException ex) {
                //TODO: handle exception
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(x==40 && y<350){
            try {
                y=y+10;
                repaint();
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        } 
        while(x>=40 && y==350 && x<290){
            try {
                x=x+10;
                repaint();
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        } 
        while(x<=290&&y<=350&&x>=50){
            try {
                x=x-10;
                y=y-10;
                repaint();
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }     
    }        
}
