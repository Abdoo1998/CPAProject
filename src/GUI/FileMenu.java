package GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Class representing the File menu of the application
 *
 * @author gorosgobe
 */
public class FileMenu extends JMenu {

    private JMenuBar menuBar;
    private CPAProjectApplicationGUI applicationReference;

    private JMenu newMenu;
    private JMenuItem newOverallTaskItem;
    private JMenuItem newSubTaskItem;
    private JMenuItem exitItem;

    private static String MENU_STRING_NEW = "New";
    private static String MENU_STRING_TASK = "Task";
    private static String MENU_STRING_SUBTASK = "Subtask";
    private static String MENU_STRING_EXIT = "Exit";

    public FileMenu(String s, JMenuBar menuBar, CPAProjectApplicationGUI applicationReference) {
        //initialises components
        super(s);
        this.menuBar = menuBar;
        this.applicationReference = applicationReference;

        this.newMenu = new JMenu(MENU_STRING_NEW);
        newMenu.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.newOverallTaskItem = new JMenuItem(MENU_STRING_TASK);
        newOverallTaskItem.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.newSubTaskItem = new JMenuItem(MENU_STRING_SUBTASK);
        newSubTaskItem.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        this.exitItem = new JMenuItem(MENU_STRING_EXIT);
        exitItem.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //sets mnemonics
        this.setMnemonic(KeyEvent.VK_F);
        newMenu.setMnemonic(KeyEvent.VK_N);
        newOverallTaskItem.setMnemonic(KeyEvent.VK_T);
        newSubTaskItem.setMnemonic(KeyEvent.VK_S);
        exitItem.setMnemonic(KeyEvent.VK_E);

        //adds file menu to menu bar
        menuBar.add(this);

        //sets actions for new submenu
        setActionsNewMenu();

        //sets actions exit item
        setActionExitItem();

        //sets font and preferred size for file menu
        this.setFont(FontCollection.DEFAULT_FONT_PLAIN);

        //TODO: PABLO, SET PREFERRED SIZE OF EACH MENU CONTAINED IN FILE MENU
    }

    private void setActionsNewMenu() {
        //adds newMenu to file menu
        add(newMenu);

        //adds overall task and sub task to NEW menu and links their actions
        newMenu.add(newOverallTaskItem);
        newMenu.add(newSubTaskItem);

        newOverallTaskItem.setAction(new NewOverallTaskAction(applicationReference));
        newOverallTaskItem.setText("Task");

        //TODO: ADD NewSubTaskAction to newSubTaskItem

    }

    private void setActionExitItem() {
        add(exitItem);
        exitItem.setAction(new ExitAction(applicationReference));
        exitItem.setText(MENU_STRING_EXIT);
    }
}
