package oodpdmm.ch07.strategy;

public class NonFreshItemDiscountStrategy implements DiscountStrategy{

    @Override
    public int getDiscountPrice(Item item) {
        return (int) (item.getPrice() * 0.8);
    }
}
