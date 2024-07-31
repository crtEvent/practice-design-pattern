package oodpdmm.ch07.state;

public class SoldOutState implements MachineState {

    @Override
    public void insertCoin(int coin, VendingMachine vm) {
        vm.returnCoin();
    }

    @Override
    public void select(int productId, VendingMachine vm) {
        System.out.println("아무 것도 하지 않는다.");
    }
}
