package View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import Util.Constants;     


public class GameWindow extends BetterWindow {

    private GridPanel targetPanel = new GridPanel();
    private GridPanel oceanPanel = new OceanGridPanel();
    private JTextPane statusPanel = new JTextPane();
    private JScrollPane statusScroller = new JScrollPane(statusPanel);
    private FadingLabel shotLabel = new FadingLabel("Shot Counter", JLabel.CENTER);

    public GameWindow(String title) {
        super(title);

        Container contentPane = this.getContentPane();

        // setup center panel with BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Stack in target grid
        targetPanel.setBackground(Color.lightGray);
        oceanPanel.setBackground(Color.lightGray);
        centerPanel.add(targetPanel);

        // stack in status area
        statusPanel.setEditable(false);
        statusPanel.setText("Welcome to Battleship.\n");
        try {
            statusPanel.getStyledDocument().insertString(statusPanel.getStyledDocument().getLength(), "It's your turn", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        statusScroller.setMinimumSize(new Dimension(Constants.Dimension.GRID_SIZE, Constants.Dimension.STATUS_AREA_HEIGHT));
        statusScroller.setPreferredSize(new Dimension(Constants.Dimension.GRID_SIZE, Constants.Dimension.STATUS_AREA_HEIGHT));

        statusScroller.getVerticalScrollBar().addAdjustmentListener(e -> e.getAdjustable().setValue(e.getAdjustable().getMaximum()));
        centerPanel.add(statusScroller);
        
        // stack in oceangrid
        centerPanel.add(oceanPanel);

        // stack in footer
        JPanel footerPanel = new JPanel();
        centerPanel.add(shotLabel);
        centerPanel.add(footerPanel);

        // add to content pane
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // window: not resizable sized specifically to calculation
        this.setResizable(false);
        this.setSize(new Dimension(Constants.Dimension.WINDOW_WIDTH, Constants.Dimension.WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(Constants.Dimension.WINDOW_WIDTH, Constants.Dimension.WINDOW_HEIGHT));
    }

    public GridPanel getTargetPanel() {
        return targetPanel;
    }
    
    public GridPanel getOceanPanel() {
        return oceanPanel;
    }

    public JTextPane getStatusPane() {
        return statusPanel;
    }
}
