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
 */

public class VendingMachine {
    private enum State { NO_COIN, SELECTABLE }
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
        }
    }

    public void select(int productId) {
        switch (state) {
            case NO_COIN -> {}
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


}
