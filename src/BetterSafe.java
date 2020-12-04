import java.util.concurrent.locks.ReentrantLock;

public class BetterSafe implements State{
    private volatile byte[] value;
    private byte maxval;
    ReentrantLock lock = new ReentrantLock();
    public BetterSafe(byte[] value){
        this.value = value;
        maxval=127;

    }
    public BetterSafe(byte[] value,byte maxval){
        this.value = value;
        this.maxval=maxval;
    }
    @Override
    public int size() {
        return value.length;
    }

    @Override
    public byte[] current() {
        return value;
    }

    @Override
    public boolean swap(int i, int j) {
        lock.lock();
        if (value[i] <= 0 || value[j] >= maxval) {
            lock.unlock();
            return false;
        }
        value[i]--;
        value[j]++;
        lock.unlock();
        return true;
    }
}
