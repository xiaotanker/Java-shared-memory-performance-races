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
        try {
            lock.lock();
            if (value[i] <= 0 || value[j] >= maxval) {
                return false;
            }
            value[i]--;
            value[j]++;
            return true;
        }finally {
            lock.unlock();
        }
    }
}
