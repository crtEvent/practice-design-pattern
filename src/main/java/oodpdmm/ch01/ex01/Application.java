package oodpdmm.ch01.ex01;

import oodpdmm.ch01.ex01.gabage.Menu;
import oodpdmm.ch01.ex01.gabage.Button;
import oodpdmm.ch01.ex01.gabage.OnClickListener;

public class Application {

    private Menu menu1 = new Menu("menu1");
    private Menu menu2 = new Menu("menu2");
    private Button button1 = new Button("button1");

    private ScreenUi currentScreen = null;

    public Application() {
        menu1.setOnClickListener(menuListener);
        menu2.setOnClickListener(menuListener);
        button1.setOnClickListener(buttonListener);
    }

    private OnClickListener menuListener = eventSource -> {
        String sourceId = eventSource.getId();

        if (sourceId.equals("menu1")) {
            currentScreen = new Menu1ScreenUi();
        } else if (sourceId.equals("menu2")) {
            currentScreen = new Menu2ScreenUi();
        }
        currentScreen.show();
    };

    private OnClickListener buttonListener = eventSource -> {
        if (currentScreen == null)
            return;

        String sourceId = eventSource.getId();
        if (sourceId.equals("button1")) {
            currentScreen.handleButton1Click();
        }
    };
}
