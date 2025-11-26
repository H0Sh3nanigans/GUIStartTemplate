package View;

import javax.swing.JPanel;

import Model.CellState;
import Model.GridRep;
import Util.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class GridPanel extends JPanel {
    private GridRep gridRep = null;

    public GridPanel() {
        this.setBackground(Color.CYAN);
    }

    public void setGridRep(GridRep rep) {
        gridRep = rep;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // panel dimensions
        int width = this.getWidth();
        int height = this.getHeight();
        int spacing = Constants.Dimension.CELL_SIZE;

        // set colors
        g.setColor(Color.BLACK);

        // draw vertical lines
        for (int x = 0; x < width; x += spacing) {
            g.drawLine(x, 0, x, height);
        }

        // draw horizontal lines
        for (int y = 0; y < height; y += spacing) {
            g.drawLine(0, y, width, y);
        }

        if (gridRep != null) {
            // the grid rep will have the information about what it should be draing in the
            // cells
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    CellState state = gridRep.getStateAt(row, column);

                    switch (state) {
                        case MISS -> drawMissAt(row, column, g);
                        case HIT -> drawHitsAt(row, column, g);
                        case OCCUPIED -> drawOccupiedAt(row, column, g);
                        case EMPTY -> {
                        } // should do nothing
                    }
                }
            }
        }
    }

    protected void drawHitsAt(int row, int column, Graphics g) {
        // draw red circle to represent a peg
        // location math
        int upperLeftX = column * Constants.Dimension.CELL_SIZE + Constants.Dimension.CELL_SIZE / 2
                - Constants.Dimension.PEG_DIAMETER / 2;
        int upperLeftY = row * Constants.Dimension.CELL_SIZE + Constants.Dimension.CELL_SIZE / 2
                - Constants.Dimension.PEG_DIAMETER / 2;

        // Creating a 2d graphic peg
        Graphics2D g2d = (Graphics2D) g;
        // setting the color of pen to whtie
        g2d.setColor(Color.RED);
        // created a circle using the dimension
        Ellipse2D.Double circle = new Ellipse2D.Double(upperLeftX, upperLeftY, Constants.Dimension.PEG_DIAMETER,
                Constants.Dimension.PEG_DIAMETER);
        // filling it with color WHITE
        g2d.fill(circle);
        // Set it the edge to black
        g2d.setColor(Color.BLACK);
        // will draw the circle now
        g2d.draw(circle);

    }

    protected void drawOccupiedAt(int row, int column, Graphics g) {
        // Draw whole cell gray for the ships

        // Location math
        // set up variables for rect
        int upperLeftX = column * Constants.Dimension.CELL_SIZE;
        int upperLeftY = row * Constants.Dimension.CELL_SIZE;
        int width = Constants.Dimension.CELL_SIZE;
        int height = Constants.Dimension.CELL_SIZE;

        // set color to dark gray
        g.setColor(Color.DARK_GRAY);
        // create rectangle
        g.fillRect(upperLeftX, upperLeftY, width, height);

    }

    protected void drawMissAt(int row, int column, Graphics g) {
        // draw white circle to represent a peg

        // location math
        int upperLeftX = column * Constants.Dimension.CELL_SIZE + Constants.Dimension.CELL_SIZE / 2
                - Constants.Dimension.PEG_DIAMETER / 2;
        int upperLeftY = row * Constants.Dimension.CELL_SIZE + Constants.Dimension.CELL_SIZE / 2
                - Constants.Dimension.PEG_DIAMETER / 2;

        // Creating a 2d graphic peg
        Graphics2D g2d = (Graphics2D) g;
        // setting the color of pen to whtie
        g2d.setColor(Color.WHITE);
        // created a circle using the dimension
        Ellipse2D.Double circle = new Ellipse2D.Double(upperLeftX, upperLeftY, Constants.Dimension.PEG_DIAMETER,
                Constants.Dimension.PEG_DIAMETER);
        // filling it with color WHITE
        g2d.fill(circle);
        // Set it the edge to black
        g2d.setColor(Color.BLACK);
        // will draw the circle now
        g2d.draw(circle);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.Dimension.GRID_SIZE, Constants.Dimension.GRID_SIZE);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(Constants.Dimension.GRID_SIZE, Constants.Dimension.GRID_SIZE);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Constants.Dimension.GRID_SIZE, Constants.Dimension.GRID_SIZE);
    }
}
