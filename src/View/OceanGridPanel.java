package View;

import java.awt.Graphics;

public class OceanGridPanel extends GridPanel{
    @Override
    protected void drawHitsAt(int row, int column, Graphics g) {
        // Draw the occupied ship first (background) and then draw the hit marker
        // so the hit indicator is visible on top of the ship graphics.
        super.drawOccupiedAt(row, column, g);
        super.drawHitsAt(row, column, g);
    }

}
