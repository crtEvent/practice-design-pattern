package oodpdmm.ch07.proxy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> paths = new ArrayList<>(List.of(
            "/src/1.jpg", "/src/2.jpg", "/src/3.jpg", "/src/4.jpg",
            "/src/5.jpg", "/src/6.jpg", "/src/7.jpg", "/src/8.jpg",
            "/src/9.jpg", "/src/10.jpg", "/src/11.jpg", "/src/12.jpg",
            "/src/13.jpg", "/src/14.jpg", "/src/15.jpg", "/src/16.jpg"));
        List<Image> images = new ArrayList<>();

        for (int i = 0; i < paths.size(); i++) {
            if (i <= 4)
                // 처음 화면에 보여줄 이미지 4개는 RealImage
                images.add(new RealImage(paths.get(i)));
            else
                // 첫 화면에서 보이지 않는 이미지는 ProxyImage
                images.add(new ProxyImage(paths.get(i)));
        }
        ListUI listUI = new ListUI(images);
        listUI.onScroll(0, 3);
        listUI.onScroll(4, 7);
        listUI.onScroll(8, 11);
        listUI.onScroll(12, 15);
    }
}
