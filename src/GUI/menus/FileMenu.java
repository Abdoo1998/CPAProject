package GUI.menus;

import GUI.*;
import GUI.menus.actions.ExitAction;
import GUI.menus.actions.NewOverallTaskAction;
import GUI.menus.actions.NewSubTaskAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Class representing the File menu of the application
 *
 * @author gorosgobe
 */
public class FileMenu extends JMenu {

    /** Reference to the application's menuBar*/
    private JMenuBar menuBar;
    /** Reference to the application*/
    private CPAProjectApplicationGUI applicationReference;

    /** NEW menu in File menu*/
    private JMenu newMenu;
    /** New task creation menu under NEW menu*/
    private JMenuItem newOverallTaskItem;
    /** New subTask creation menu under NEW menu*/
    private JMenuItem newSubTaskItem;
    /** Exit menu under NEW menu*/
    private JMenuItem exitItem;

    /** String for NEW menu*/
    private static final String MENU_STRING_NEW = "New";
    /** String for creation of OverallTask menu*/
    private static final String MENU_STRING_TASK = "Task";
    /** String for creation of SubTask menu*/
    private static final String MENU_STRING_SUBTASK = "Subtask";
    /** String for creation of exit menu*/
    private static final String MENU_STRING_EXIT = "Exit";

    /**
     * Creates a file menu.
     * @param s the string representing the name of the menu
     * @param menuBar the menuBar the menu is associated to
     * @param applicationReference the application the menu is associated to
     */
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

    /**
     * Adds and sets up the actions for the NEW menu and the menus under it
     */
    private void setActionsNewMenu() {
        //adds newMenu to file menu
        add(newMenu);

        //adds overall task and sub task to NEW menu and links their actions
        newMenu.add(newOverallTaskItem);
        newMenu.add(newSubTaskItem);

        newOverallTaskItem.setAction(new NewOverallTaskAction(applicationReference));
        newOverallTaskItem.setText(MENU_STRING_TASK);

        //null parameter because the default selected task doesnt matter
        newSubTaskItem.setAction(new NewSubTaskAction(applicationReference, null));
        newSubTaskItem.setText(MENU_STRING_SUBTASK);

    }

    /**
     * Sets up the exit menu and its action
     */
    private void setActionExitItem() {
        add(exitItem);
        exitItem.setAction(new ExitAction(applicationReference));
        exitItem.setText(MENU_STRING_EXIT);
    }
}
