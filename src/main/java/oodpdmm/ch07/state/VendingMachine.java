package oodpdmm.ch07.state;

/* [요구 사항 - 자판기 소프트 웨어]
 *
 * | 동작     | 조건            | 실행             | 결과                 |
 * |---------|---------------|-----------------|----------------------|
 * | 동전 넣음 | 동전 없음이면     | 금액 증가         | 제품 선택 가능          |
 * | 동전 넣음 | 제품 선택 가능이면 | 금액 증가         | 제품 선택 가능          |
 * | 제품 선택 | 동전 없음이면     | 아무 동작 없음     | 동전 없음 유지          |
 * | 제품 선택 | 제품 선택 가능이면 | 제품 주고 잔액 감소 | 잔액 있으면 제품 선택 가능 |
 * |         |               |                 | 잔액 없으면 동전 없음     |
 *
 * [요구사항 추가]
 * - 자판기에 제품이 없는 경우 동전을 넣으면 동전을 되돌려 준다가
 *
 * [State pattern]
 * 동일한 기능이 상태에 따라 다르게 동작할 때
 * 상태를 별도의 타입으로 분리, 각 상태별로 알맞은 하위 타입을 구현한.
 * 상태 객가 기능을 제공하게 한다
 * -> 상태가 추가되어도 콘텍스트 코드가 받는 영향이 줄어든다
 */

public class VendingMachine {
    private MachineState state = new NoCoinState();
    private int coin = 0;

    public void insertCoin(int coin) {
        state.insertCoin(coin, this);
    }

    public void select(int productId) {
        state.select(productId, this);
    }

    public void changeState(MachineState State) {
        this.state = state;
    }

    public void increaseCoin(int coin) {
        this.coin += coin;
    }

    public void decreaseCoin() {
        coin--;
    }

    public boolean hasNoCoin() {
        return coin <= 0;
    }

    public void provideProduct(int productId) {
    }

    public void returnCoin() {
    }


}
