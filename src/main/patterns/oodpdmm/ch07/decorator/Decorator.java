package oodpdmm.ch07.decorator;

/*
상속은 다양한 조합의 기능 확장이 요구될 때 클래스가 불필요하게 증가하는 문제가 생김
버퍼 기능과 압축 기능을 함께 제공하거나, 압축한 뒤 암호화 기능을 제공해야 한다거나, 버퍼 기능과 암호화 기능을 함께 제공해야 한다면?

데코레이터 패턴은 상속이 아닌 위임을 하는 방식으로 기능을 확장할 수 있다
 */
public abstract class Decorator implements FileOut {
    private final FileOut delegate; // 위임 대상

    public Decorator(FileOut delegate) {
        this.delegate = delegate;
    }

    protected void doDelegate(byte[] data) {
        delegate.write(data); // delegate에 쓰기 위임하기
    }
}
