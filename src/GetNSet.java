import java.util.concurrent.atomic.AtomicIntegerArray;

public class GetNSet implements State {
    AtomicIntegerArray value;
    private byte maxval;
    GetNSet(byte[] v) {
        value = new AtomicIntegerArray( v.length);
        for(int i=0;i<v.length;i++){
            value.set(i,v[i]);
        }
        maxval = 127;
    }

    GetNSet(byte[] v, byte m) {
        value = new AtomicIntegerArray( v.length);
        for(int i=0;i<v.length;i++){
            value.set(i,v[i]);
        }
        maxval = m;
    }
    @Override
    public int size() {
        return value.length();
    }

    @Override
    public byte[] current() {

        byte[] b= new byte[value.length()];
        for(int i=0;i<value.length();i++){
            b[i]=(byte)value.get(i);
        }
        return b;
    }

    @Override
    public boolean swap(int i, int j) {
        if (value.get(i)<= 0 || value.get(j) >= maxval) {
            return false;
        }
        value.decrementAndGet(i);
        value.incrementAndGet(j);
        return true;
    }

}
