# 싱글톤 패턴

## 언제 필요할까?
- 시스템에 인스턴스가 딱 하나만 존재해야 한다
  - (???) 시스템에 인스턴스가 제한된 갯수 만큼만 필요한 경우(2~3개만 필요한 경우)가 있을까?
- 시스템의 모든 부분에서 인스턴스에 접근 가능해야 한다

## 싱글톤이 지켜야 하는 것
- 새로운 인스턴스가 생성되지 않아야 함
- 스레드 세이프 해야 함
    - 두 스레드가 동시에 싱글톤 객체를 생성하려고 할 수 있기 때문
- Lazy 인스턴스 생성
    - 인스턴스 생성에도 비용이 들기 때문에 필요한 순간에 생성되어야 한다
    - (???) 이것도 이해 안됨. 생성 비용이 크면 필요할 때 오래 걸리니까 프로젝트 시작할 때 생성 해 놓는게 좋지 않나?

## 적용 사례 예시
- 시스템 설정 정보 관하는 클래스
  - 어떤 시스템이 해당 설정 정보를 수정하든 모든 시스템에 영향 을 끼쳐야 한다.
- 고유 증분 ID 생성기 클래스
  - 프로그램에 2개의 ID 생성기 객체가 동시에 존재한다면 중복된 ID가 생성될 수 있다.
- 스레드 풀, 사용자 설정, 레지스트리 설정, 로그 기록용 객체, 디바이스 드라이버...

## 코드
### 즉시 초기화 방식
- 인스턴스 생성시 스레드 안전함
```java
public class IdGenerator {

  private final AtomicLong id = new AtomicLong(0);
  private static final IdGenerator instance = new IdGenerator();

  private IdGenerator() {
  }

  public static IdGenerator getInstance() {
    return instance;
  }

  public long getId() {
    return id.incrementAndGet();
  }

}
```
- `private static final IdGenerator instance = new IdGenerator();`
  - static이라 해도 시스템이 시작할 때 메모리에 올라가 있는게 아님
  - 실제로 사용할 때 올라감

### 늦은 초기화 방식
- 인스턴스가 필요한 시점에 초기화 됨
  - 인스턴스를 미리 만들어 놓는게 낭비라는 의견도 있다
  - 그러나 리소스가 많이 들수록 필요할 때 요청에 걸리는 시간이 길어지는 문제가 있을 수 있다
  - 그래서 오히려 리소스를 많이 쓴다면 즉시 초기화 방식을 선택하는게 좋다(fail-fast 관점에서도 좋음)
- 사용 빈도가 높으면 lcok이 자주 일어나 병목현상이 생길 수 있다.
- (???) static도 결국 사용하는 시점에 메모리에 올라가게 되는데 늦은 초기화로 구현할 필요가 있는지?
- 싱글톤을 사용하기 위해 다른 의존하는 객체가 필요하다면 늦은 초기화가 필요함
  - jvm
```java
public class IdGenerator {

  private final AtomicLong id = new AtomicLong(0);
  private static final IdGenerator instance = new IdGenerator();

  private IdGenerator() {
  }

  public static synchronized IdGenerator getInstance() {
    if (instance == null) {
      instance = new IdGenerator();
    }
    return instance;
  }

  public long getId() {
    return id.incrementAndGet();
  }

}
```

### 홀더에 의한 초기화
- 지연 초기화 + 동시성을 지원하는 방식
```java
public class IdGenerator {

  private final AtomicLong id = new AtomicLong(0);

  private IdGenerator() {
  }

  private static class SingletonHolder { 
      private static final IdGenerator instance = new IdGenerator();
  }

  public static IdGenerator getInstance() { 
      return SingletonHolder.instance;
  }

  public long getId() {
      return id.incrementAndGet();
  }

}
```
- 외부 클래스 `IdGenerator`가 적재될 때 `SingletonHolder`는 적재되지 않은 상태
- `getInstance()`가 호출될 때 `SingletonHolder`가 적재되면서 인스턴스 생성
- 인스턴스의 유일성과 생성 프로세스의 스레드 안전성이 JVM에 의해 보장된다

### Enum 싱글톤
- Enum은 static 보다 먼저 초기화 됨
  - 여러 의존관계를 갖는 싱글톤일 경우 더 안전함 (??? 왜지... 왜 더 안전하지)
- 인스턴스 개수를 컴파일 타임에 결정할 수 있음
```java
public enum IdGenerator {

    INSTANCE;
    
    private final AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
```

## 싱글톤 단점
- 클래스간 의존성을 알 수 없음
  - 싱글톤 클래스가 변경되었을 때 여파가 어디까지 미칠지 알 수 없다
  - 대안) 싱글톤 객체를 일반 객체로 감싸서 사용하기. 싱글톤은 외부에 노출시키지 않음

## 코틀린 싱글톤
- `object` 키워드로 싱글톤 객체를 만들 수 있다
  - `object` 키워드로 class를 선언하면 클래스를 정의하는 동시에 클래스의 인스턴스를 정의하는 것
  - 스레드 세이프함
  - 초기화는 싱글턴 클래스가 실제 로딩되는 시점까지 지연된다
```kotlin
object Application {
    val name = "My Application"

    override fun toString() = name
}
```