package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RemoveButton extends JButton implements ActionListener, MouseListener {

    private JTabbedPane pane;
    private RemovableTabComponent removableTabComponent;
    private final static String ICON_PATH = "GUI/images/close.png";
    private static final String HOVERED_ICON_PATH = "GUI/images/closeHovered.png";

    public RemoveButton(JTabbedPane pane, RemovableTabComponent removableTabComponent) {
        super(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)));
        this.pane = pane;
        this.removableTabComponent = removableTabComponent;
        //sets the UI to a normal button without boldness of any look and feel
        setUI(new BasicButtonUI());
        setSize(new Dimension(5, 5));
        //add invisible borders top and left
        setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 0));
        //make button transparent
        setContentAreaFilled(false);
        setFocusable(false);
        setBorderPainted(false);
        addActionListener(this);
        addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int i = pane.indexOfTabComponent(removableTabComponent);
        System.out.println(i);
        if (i != -1) {
            pane.remove(i);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.setIcon(new ImageIcon(ClassLoader.getSystemResource(HOVERED_ICON_PATH)));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setIcon(new ImageIcon(ClassLoader.getSystemResource(ICON_PATH)));
    }
}
