package Controller;

import Model.Coordinate;
import Model.GridListener;
import Model.GridRep;
import Model.TargetGrid;
import Util.Constants;
import View.GridPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TargetGridController {
    private GridPanel view; 
    private TargetGrid model;
    private GridPanelListener viewListener;
    private GridListener modelListener;

    public TargetGridController (GridPanel view, TargetGrid model){
        this.view = view;
        this.model = model;

        // Listen for clicks from the target panel
        viewListener = new GridPanelListener();
        view.addMouseListener(viewListener);

        // listen for notifications from the model
        modelListener = new TargetGridListener();
        model.addListener(modelListener);
    }

    private class GridPanelListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            // debug to see what coord pixel dimension (x, y)
            int column = x / Constants.Dimension.CELL_SIZE;
            int row = y / Constants.Dimension.CELL_SIZE;
            // System.out.println(String.format("Clicked: %d, %d", modelX, modelY));
            

            // create a shot
            Coordinate shot = null;
            try {
                shot = new Coordinate(row, column);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } if (model.isValidShot(shot)) {
                model.handleShot(shot, this);
            } else {
                System.out.printf("Already made this shot at %s%n", shot.toString());
            }
        }
    } 

    private class TargetGridListener implements GridListener {

        @Override
        public void gridChanged(GridRep gridRep) {
            view.setGridRep(gridRep);
        }
    }
}
