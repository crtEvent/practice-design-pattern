package ch01.ex01;

public class Menu2ScreenUi implements ScreenUi {

    @Override
    public void show() {
        System.out.println("메뉴2 화면으로 전환");
    }

    @Override
    public void handleButton1Click() {
        System.out.println("메뉴2 화면의 버튼1 처리");
    }
}
