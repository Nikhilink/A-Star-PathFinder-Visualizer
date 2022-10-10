package com.nikhil.pathfinding;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents a single Node in a grid
 */
public class Node 
{
    private int x,y,width,height,nodeNumber,gCost,hCost,fCost,nodeRowIndex,nodeColumnIndex;
    
    boolean walkable = true;

    private Node parent;
    
    /**
     * Initializes the Node or one Cell in Grid
     * @param x X position in World Space
     * @param y Y position in World Space
     * @param width width of the Cell
     * @param height height of the Cell
     * @param nodeRowIndex X or Row Index position in Grid Space
     * @param nodeColumnIndex Y or Column Index position in Grid Space
     * @param nodeNumber Data Of the Node
     * @param walkable is the  Node Walkable or !
     */
    public Node(int x,int y,int width,int height,int nodeRowIndex,int nodeColumnIndex,int nodeNumber,boolean walkable)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walkable = walkable;
        this.nodeNumber = nodeNumber;
        this.nodeRowIndex = nodeRowIndex;
        this.nodeColumnIndex = nodeColumnIndex;
    }
    /**
     * Renders the node with given X and Y position with a Width and Height specified in the constructor
     * @param g Graphics g
     */
    public void render(Graphics g,Color color,boolean findPath)
    {
        if(walkable && findPath)
        {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
        if(walkable)
        {
            g.setColor(Color.BLACK);
            g.drawString(nodeNumber+"",x + (width/2), y + (height / 2));
            g.drawRect(x, y, width, height);
        }
        else
        {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);
        }
    }

    //#region Getters and Setters
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the nodeNumber
     */
    public int getNodeNumber() {
        return nodeNumber;
    }

    /**
     * @param nodeNumber the nodeNumber to set
     */
    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    /**
     * @return the gCost
     */
    public int getgCost() {
        return gCost;
    }

    /**
     * @param gCost the gCost to set
     */
    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    /**
     * @return the hCost
     */
    public int gethCost() {
        return hCost;
    }

    /**
     * @param hCost the hCost to set
     */
    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    /**
     * @return the fCost
     */
    public int getfCost() {
        return fCost;
    }

    /**
     * @param fCost the fCost to set
     */
    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    /**
     * @return the walkable
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * @param walkable the walkable to set
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    /**
     * @return the nodeRowIndex
     */
    public int getNodeRowIndex() {
        return nodeRowIndex;
    }

    /**
     * @param nodeRowIndex the nodeRowIndex to set
     */
    public void setNodeRowIndex(int nodeRowIndex) {
        this.nodeRowIndex = nodeRowIndex;
    }

    /**
     * @return the nodeColumnIndex
     */
    public int getNodeColumnIndex() {
        return nodeColumnIndex;
    }

    /**
     * @param nodeColumnIndex the nodeColumnIndex to set
     */
    public void setNodeColumnIndex(int nodeColumnIndex) {
        this.nodeColumnIndex = nodeColumnIndex;
    }
    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }
    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    //#endregion
    
}
