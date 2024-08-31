package oodpdmm.ch07.proxy;

public class RealImage implements Image {
    private String path;

    public RealImage(String path) {
        this.path = path;
    }

    @Override
    public void draw() {
        System.out.printf("[%s] 이미지 불러오기.%n", path);
    }
}
