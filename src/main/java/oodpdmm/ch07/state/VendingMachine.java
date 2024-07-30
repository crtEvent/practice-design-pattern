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
 */

public class VendingMachine {
    private enum State { NO_COIN, SELECTABLE, SOLD_OUT }
    private State state = State.NO_COIN;
    private int coin = 0;

    public void insertCoin(int coin) {
        switch (state) {
            case NO_COIN -> {
                increaseCoin(coin);
                state = State.SELECTABLE;}
            case SELECTABLE -> {
                increaseCoin(coin);
            }
            case SOLD_OUT -> returnCoin(); // 상태에 대한 요구사항이 추가될 때 마다 조건문 코드가 추가된다
        }
    }

    public void select(int productId) {
        switch (state) {
            case NO_COIN, SOLD_OUT -> {}
            case SELECTABLE -> {
                provideProduct(productId);
                decreaseCoin();
                if (hasNoCoin()) {
                    state = State.NO_COIN;
                }
            }
        }
    }

    private void increaseCoin(int coin) {
        this.coin += coin;
    }

    private void decreaseCoin() {
        coin--;
    }

    private boolean hasNoCoin() {
        return coin <= 0;
    }

    private void provideProduct(int productId) {
    }

    private void returnCoin() {
    }


}
