package VendingMachine;

import java.util.List;

public class Dispensable implements State {

    private VendingMachine vendingMachine;
    private List<VendingMachine.Coin> coins;

    public Dispensable(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
        this.coins = vendingMachine.getCoins();
    }

    /**
     * insert coin for Dispensable state
     * @param coin
     */
    @Override
    public void insertCoin(VendingMachine.Coin coin) {
        System.out.println("Can't insert more coins.");

    }

    /**
     * automatically sends to refund state and refunds
     */
    @Override
    public void refund() {
        vendingMachine.setCurrState("Refunding");
        vendingMachine.refund();
    }

    /**
     * Dispenses product, clears the coins and resets machine
     */
    @Override
    public void dispenseProduct() {
        System.out.println("Dispensing delicious goodies!");
        vendingMachine.getCoins().clear();
        vendingMachine.setCurrState("Waiting");

    }
}
