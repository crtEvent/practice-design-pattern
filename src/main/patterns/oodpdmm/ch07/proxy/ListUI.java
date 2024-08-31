package oodpdmm.ch07.proxy;

import java.util.List;

/**
 * 이미지 목록을 보여주는 UI
 * 목록의 일무는 화면에 보여주고, 스크롤 할 때 나머지 목록을 보여준다
 *
 * 메모리 효율을 위해 화면에 보이지 않는 이미지들의 객체를 나중에 불러올 수 없을까?
 */
public class ListUI {
    private List<Image> images;

    public ListUI(List<Image> images) {
        this.images = images;
    }

    public void onScroll(int start, int end) {
        for (int i = start; i <= end; i++) {
            Image image = images.get(i);
            image.draw();
        }

        if (end >= images.size() - 1) {
            System.out.println("[End]");
        } else {
            System.out.println("[Next]");
        }
    }
}
