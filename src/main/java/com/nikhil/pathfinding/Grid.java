package com.nikhil.pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.nikhil.view.Window;

/**
 * Creates a Grid with nodes on Window
 */
public class Grid 
{
    private static final Logger LOG = Logger.getLogger(Window.class.getName());
    Node[][] grid;

    List<Node> path;

    int width,height;
    int gridWH;

    public boolean isFindPath = false;

    /**
     * Creates a Grid of Node or Cells with given width and height with respect to gridWH or Cell size
     * @param width width of the grid
     * @param height height of the grid
     * @param gridWH Cell Width and Height
     */
    public Grid(int width, int height,int gridWH) 
    {
        this.width = width;
        this.height = height;
        this.gridWH = gridWH;
        int no = 0;
        grid = new Node[width][height];
        Random r = new Random();
        for(int i = 0;i < width;i++)
        {
            for(int j = 0;j < height;j++)
            {
                no+=1;
                this.grid[i][j] = new Node(i*gridWH,j*gridWH,gridWH,gridWH,i,j,no,r.nextInt(20)%3 == 0 ? false:true);
            }
        }
    }
    /**
     * Renders the Grid
     * @param g Graphics g to Draw Grid
     */
    public void render(Graphics g)
    {
        if(!isFindPath)
        {
            for(int i = 0;i < width;i++)
            {
                for(int j = 0;j < height;j++)
                {
                    if(grid[i][j] != null)
                    {
                        grid[i][j].render(g,Color.BLACK,false);
                    }
                }
            }
        }
        else
        {
            for(int i = 0;i < width;i++)
            {
                for(int j = 0;j < height;j++)
                {
                    if(path.contains(grid[i][j]))
                    {
                        grid[i][j].render(g, Color.GREEN,true);
                    }
                    else
                    {
                        grid[i][j].render(g, Color.BLACK, false);
                    }
                }
            }
        }
    }
    /**
     * Gets the Node which was clicked by the user
     * @param locX X location of mouse pointer
     * @param locY Y localtion od mouse pointer
     * @return Node
     */
    public Node getClickedNode(int locX,int locY)
    {
        Node node = null;
        for(int i = 0;i < width;i++)
        {
            for(int j = 0;j < height;j++)
            {
                if((locX > grid[i][j].getX() && locX < (grid[i][j].getX() + grid[i][j].getHeight()) && (locY > grid[i][j].getY()) && locY < (grid[i][j].getY() + grid[i][j].getHeight())))
                {
                    node = grid[i][j];
                }
            }
        }
        return node;
    }
    /**
     * Gets the List of Neighbour cell surrounding the cell which is not walkable and outside the grid
     * @param node cell
     * @return List of neighbour cells
     */
    private List<Node> getNeighbours(Node node)
    {
        List<Node> neighbours = new ArrayList<Node>();
        for(int x = -1;x <=1;x++)
        {
            for(int y = -1;y <= 1;y++)
            {
                if(x == 0 && y == 0)
                {
                    continue;
                }
                else
                {
                    int checkX = node.getNodeRowIndex() + x;
                    int checkY = node.getNodeColumnIndex() + y;
                    if(checkX >= 0 && checkX < width && checkY >= 0 && checkY < height)
                    {
                        neighbours.add(grid[checkX][checkY]);
                    }
                }
            }
        }
        return neighbours;
    }
    /**
     * Finds the path with specified startnode and end node
     * @param start Start Node
     * @param end End Node
     */
    public void findPath(Node start,Node end) 
    {
        List<Node> openList = new ArrayList<Node>();
        HashSet<Node> closedList = new HashSet<Node>();

        openList.add(start);
        
        while(!openList.isEmpty())
        {
            Node currentNode = openList.get(0);
            for(int i = 1;i < openList.size();i++)
            {
                if(openList.get(i).getfCost() < currentNode.getfCost() || openList.get(i).getfCost() == currentNode.getfCost() && openList.get(i).gethCost() < currentNode.gethCost())
                {
                    currentNode = openList.get(i);
                }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);

            if(currentNode == end)
            {
                path = retracePath(start, end);
                isFindPath = true;
                return;
            }
            else
            {
                for(Node neighbour : getNeighbours(currentNode))
                {
                    if(!neighbour.isWalkable() || closedList.contains(neighbour))
                    {
                        continue;
                    }
                    else
                    {
                        int newMovementCostToNeighbour = currentNode.getgCost() + getDistance(currentNode, neighbour);
                        if(newMovementCostToNeighbour < neighbour.getgCost() || !openList.contains(neighbour))
                        {
                            neighbour.setgCost(newMovementCostToNeighbour);
                            neighbour.sethCost(getDistance(neighbour, end));
                            neighbour.setParent(currentNode);

                            if(!openList.contains(neighbour))
                            {
                                openList.add(neighbour);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Retraces the path
     * @param start Start Node
     * @param end End Node
     * @return List of Nodes
     */
    public List<Node> retracePath(Node start,Node end) 
    {
        List<Node> path = new ArrayList<>();
        Node currentNode = end;
        while(currentNode != start)
        {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        StringBuilder wholePath = new StringBuilder();
        for(Node node : path)
        {
            // System.out.print(node.getNodeNumber()+" ");
            wholePath.append(node.getNodeNumber()+" ");
            
        }
        LOG.info(wholePath.toString());
        return path;
    }
    /**
     * Gets the Distance between two Nodes
     * @param nodeA NodeA
     * @param nodeB NodeB
     * @return distance
     */
    private int getDistance(Node nodeA,Node nodeB) 
    {
        int distanceX = Math.abs(nodeA.getNodeRowIndex() - nodeB.getNodeRowIndex());
        int distanceY = Math.abs(nodeA.getNodeColumnIndex() - nodeB.getNodeColumnIndex());

        if(distanceX > distanceY)
        {
            return 14 * distanceY + 10 * (distanceX - distanceY);
        }
        else
        {
            return 14 * distanceX + 10 * (distanceY - distanceX);   
        }
    }
}
