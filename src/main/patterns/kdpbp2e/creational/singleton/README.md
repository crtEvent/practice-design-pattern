# 싱글톤 패턴

## 언제 필요할까?
- 시스템에 인스턴트가 딱 하나만 존재해야 한다
- 시스템의 모든 부분에서 인스턴스에 접근 가능해야 한다

## 요구사항
- 새로운 인스턴스가 생성되지 않아야 함
- 스레드 세이프 해야 함
  - 두 스레드가 동시에 싱글톤 객체를 생성하려고 할 수 있기 때문
- Lazy 인스턴스 생성
  - 인스턴스 생성에도 비용이 들기 때문에 필요한 순간에 생성되어야 한다