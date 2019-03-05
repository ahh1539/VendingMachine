
package VendingMachine;

import java.util.List;

public class Waiting implements State {

    private List<VendingMachine.Coin> coins;
    private VendingMachine vend;

    public Waiting(VendingMachine vendingMachine){
        this.vend = vendingMachine;
        this.coins = vendingMachine.getCoins();
    }

    /**
     * checks if amount with coin is greater than 1
     * then adds coin and if == 1 change state to dispensable
     * @param coin
     */
    @Override
    public void insertCoin(VendingMachine.Coin coin) {
        double amountAlreadyInserted = calculateMoneyInserted();
        if (amountAlreadyInserted + coin.amount() > 1){
            System.out.println("Exact change only.");
        }
        else {
            coins.add(coin);
            if (calculateMoneyInserted() == 1){
                vend.setCurrState("Dispensable");
            }
        }

    }

    /**
     * sets state to refunding and calls refund
     */
    @Override
    public void refund() {
        vend.setCurrState("Refunding");
        vend.refund();

    }

    /**
     * doesnt allow to dispense in this state
     */
    @Override
    public void dispenseProduct() {
        System.out.println("Insufficient funds!");

    }

    /**
     * calculates total money inserted
     * @return
     */
    private double calculateMoneyInserted() {
        double amount = 0.0;

        for(VendingMachine.Coin coin : coins) {
            amount += coin.amount();
        }
        return amount;
    }
}

