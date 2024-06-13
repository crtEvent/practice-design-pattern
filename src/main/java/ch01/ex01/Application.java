package ch01.ex01;

import java.awt.Button;
import java.awt.Component;
import java.awt.Menu;

public class Application {

    private Menu menu1 = new Menu("menu1");
    private Menu menu2 = new Menu("menu2");
    private Button button1 = new Button("button1");

    private ScreenUi currentMenu = null;

    public Application2() {
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    public void clicked(Component eventSource) {
        String sourceId = eventSource.getId();

        if (sourceId.equals("menu1")) {
            currentMenu = new Menu1ScreenUi();
            currentMenu.show();
        } else if (sourceId.equals("menu1")) {
            currentMenu = new Menu2ScreenUi();
            currentMenu.show();
        } else if (sourceId.equals("button1")) {
            if (currentMenu == null)
                return;
            currentMenu.handleButton1Click();
        }
    }
}
