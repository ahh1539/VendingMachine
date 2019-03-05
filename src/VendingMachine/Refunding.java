package VendingMachine;

import java.util.List;

public class Refunding implements State {

    private boolean refunded = false;
    private VendingMachine vendingMachine;
    private List<VendingMachine.Coin> coins;
    private long delay;

    public Refunding(VendingMachine vendingMachine, long delay) {
        this.vendingMachine = vendingMachine;
        this.coins = vendingMachine.getCoins();
        this.delay = delay;
    }

    /**
     * doesnt allow user to insert more coins
     * @param coin
     */
    @Override
    public void insertCoin(VendingMachine.Coin coin) {
        System.out.println("Can't insert coins while refunding in progress.");
    }

    /**
     * checks if machine has already refunded if not it refunds then clears coins and resets state
     */
    @Override
    public synchronized void refund() {
        if (refunded == false) {
            refunded = true;
            for (VendingMachine.Coin coin : coins) {
                System.out.println("A " + coin.toString() + " pops out. *clink*");
                // realistic simulation!
                try {
                    wait(delay);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
                vendingMachine.getCoins().clear();
                refunded = false;
                vendingMachine.setCurrState("Waiting");


        } else {
            System.out.println("Stop mashing the button!");
        }

    }

    /**
     * doesnt allow user to access dispense from refund state
     */
    @Override
    public void dispenseProduct() {
        System.out.println("Can't purchase while refunding!");
    }
}

