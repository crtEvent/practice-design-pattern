package oodpdmm.ch07.proxy;

public class ProxyImage implements Image {

    private String path;
    private RealImage image;

    public ProxyImage(String path) {
        this.path = path;
    }

    @Override
    public void draw() { // draw()를 호출할 때 실제 이미지 객체가 생성된다
        if (image == null) {
            image = new RealImage(path); // 최초 접근 시 객체 생성
        }
        image.draw(); // RealImage 객체에 위임
    }
    /*
    ProxyImage 객체는 최종에 draw() 메서드가 실행 될 때 RealImage 객체를 생성하기 때문에,
    draw() 메서드가 호출되기 전에는 RealImage 객체가 생성되지 않아 메모리에 이미지 데이터를 로딩하지 않는다
    -> 화면에 표시되지 않는 이미지를 불필요하게 메모리에 로드하지 않아도 된다

    ListUI는 이미지가 언제 로딩되는지 모르게 된다 -> 이미지 로딩 정책을 변경해도 ListUI 에는 변경이 없디
     */
}
