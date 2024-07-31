package oodpdmm.ch07.state;

public class SelectableState implements MachineState {

    @Override
    public void insertCoin(int coin, VendingMachine vm) {
        vm.increaseCoin(coin);
    }

    @Override
    public void select(int productId, VendingMachine vm) {
        vm.provideProduct(productId);
        vm.decreaseCoin();
        if (vm.hasNoCoin()) {
            vm.changeState(new NoCoinState());
        }
    }
}
