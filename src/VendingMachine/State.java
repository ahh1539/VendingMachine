package VendingMachine;


public interface State {
    public abstract void insertCoin(VendingMachine.Coin coin);
    public abstract void refund();
    public abstract void dispenseProduct();
}

