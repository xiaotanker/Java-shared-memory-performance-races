public class Unsynchronized implements State {
    private byte[]  value;
    private byte maxval;

    Unsynchronized(byte[] v) { value = v; maxval = 127; }

    Unsynchronized(byte[] v, byte m) { value = v; maxval = m; }

    public int size() { return value.length; }

    public byte[] current() { return value; }

    public  boolean swap(int i, int j) {
        if (value[i] <= 0 || value[j] >= maxval) {
            return false;
        }
        value[i]--;
        value[j]++;
        return true;
    }
}
