import java.util.concurrent.atomic.AtomicInteger;

class BetterSorryState implements State {
    private AtomicInteger value[] ;
    private byte maxval;

    BetterSorryState(byte[] v) {
	int v_le=value.length;
        value = new AtomicInteger[v_le];
	int i;
        for ( i=0; i<v.length; i++)
            {value[i] = new AtomicInteger((int) v[i]); }
	
        maxval = 127;
    }

    BetterSorryState(byte[] v, byte m) { 
	int v_le=v.length;
        value = new AtomicInteger[v_le];
        for (int i=0; i<v.length; i++)
            {value[i] = new AtomicInteger((int) v[i]); }

	maxval = m; 
    }

    public int size() { return value.length; }

    public byte[] current() {
	int v_le=value.length;
        byte Value_atPos[] = new byte[v_le];
        for (int i = 0; i < value.length; i++)
            Value_atPos[i] = (byte) value[i].get();

	return Value_atPos; 
    }

    public boolean swap(int i, int j) {
	AtomicInteger zero =new  AtomicInteger( 0);
	AtomicInteger max= new AtomicInteger(maxval);
	
	if (value[i].get() <= zero.get() || value[j].get() >= max.get() ) {
        return false;
    }
	
    value[i].getAndDecrement();
    value[j].getAndIncrement();
    return true;
    }
}
