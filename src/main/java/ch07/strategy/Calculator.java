package ch07.strategy;

import java.util.List;

public class Calculator {

    /**
     * 서로 다른 계산 정책들이 한 코드에 섞여 있다.
     * 정책이 추가될 수록 코드에 분기문이 늘어나고 파악하기 어려워 진다
     */
    public int calculate(boolean firstGuest, List<Item> items) {
        int sum = 0;
        for (Item item : items) {
            if (firstGuest)
                sum += (int) (item.getPrice() * 0.9); // 첫 고객 10% 할인
            else if (! item.isFresh()) {
                sum += (int) (item.getPrice() * 0.8); // 오래된 상품 20% 할인
            }
        }
        return sum;
    }

}
