package VendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by robertstjacquesjr on 2/22/17.
 */
public class VendingMachine {

    public enum Coin {
        NICKEL (0.05),
        DIME (0.10),
        QUARTER (0.25),
        FIFTY_CENT_PIECE (0.5),
        DOLLAR (1.00);

        private double amount;

        Coin(double amount) {
            this.amount = amount;
        }

        public double amount() {
            return amount;
        }


    }

    private List<Coin> coins;
    private boolean refunding;
    private boolean dispensable;
    public Map<String, State> states;
    private final long refundDelay;
    private State currState;

    public VendingMachine() {
        this(500);
    }

    VendingMachine(long refundDelay) {
        states = new HashMap<>();
        coins = new ArrayList<>();
        states.put("Dispensable", new Dispensable(this));
        states.put("Refunding", new Refunding(this,refundDelay));
        states.put("Waiting", new Waiting(this));
        currState = states.get("Waiting");



        this.refundDelay = refundDelay;
    }

    public void insertCoin(Coin coin) {
        currState.insertCoin(coin);
    }

    public void refund() {
        currState.refund();
    }

    public void dispenseProduct() {
        currState.dispenseProduct();

    }



    public void setCurrState(String state) {
        currState = states.get(state);
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public long getRefundDelay(){return refundDelay;}
}
