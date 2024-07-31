package oodpdmm.ch07.state;

public interface MachineState {
    void insertCoin(int coin, VendingMachine vm);
    void select(int productId, VendingMachine vm);
}
