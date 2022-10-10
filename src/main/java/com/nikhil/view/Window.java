package com.nikhil.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;

import com.nikhil.pathfinding.Grid;
import com.nikhil.pathfinding.Node;

public class Window extends JPanel
{
    private JFrame jFrame;
    private Grid grid;
    private Node start = null,end = null;

    /**
     * Creates a Window with specified Parameters
     * @param x Width of window
     * @param y Height of Window
     * @param panelSizeX Width of the Grid
     * @param panelSizeY Height of the Grid
     * @param cellSize Cell Width and Height
     */
    public Window(int x,int y,int panelSizeX,int panelSizeY,int cellSize)
    {
        grid = new Grid(panelSizeX, panelSizeY,cellSize);

        setBounds(0, 0, panelSizeX * cellSize, panelSizeY * cellSize);
        setBorder(new LineBorder(Color.BLACK, 2));

        jFrame = new JFrame("Select Any Cell To Start With");
        jFrame.setSize(x, y);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(null);

        JLabel inst = new JLabel("Click First Node");
        inst.setLocation(10, 310);

        JPanel menu = new JPanel();

        menu.setBackground(Color.BLUE);
        menu.setVisible(true);
        jFrame.add(menu);
        jFrame.add(this);

        jFrame.setContentPane(this);
        addMouseListener(new MouseInputListener() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(start == null)
                {
                    start = grid.getClickedNode(e.getX(), e.getY());
                    System.out.println(start.getNodeNumber());
                    jFrame.setTitle("Choose End Node");
                }
                else if(start != null && end == null)
                {
                    end = grid.getClickedNode(e.getX(), e.getY());
                    System.out.println(end.getNodeNumber());
                    jFrame.setTitle("A* Pathfinding");
                    long startTime = System.currentTimeMillis();
                    grid.findPath(start, end);
                    jFrame.setTitle("Total Time: "+(System.currentTimeMillis() - startTime)+" Milli Seconds");
                    System.out.println("\nTotal Time: "+(System.currentTimeMillis() - startTime)+" Milli Seconds");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
            
        });
        jFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) 
    {
        grid.render(g);
        repaint();
    }

   
}
