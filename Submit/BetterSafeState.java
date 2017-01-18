//BetterSafe state implementation;
//Have the same structure as GetNSet but fix the erros;
//Dont use seperate get set methods but instead make use of the AtomicIntegerArray functions
//decrementAndGet and incrementAndGet. This functions are atomic and replace a get-set pair
//which makes the functions more efficient as well as DRF
import java.util.concurrent.atomic.AtomicIntegerArray;
class BetterSafeState implements State {

    private AtomicIntegerArray value;
    private byte maxval;
    
    //constructor has to fill all the values to AtomicIntegerArray
    BetterSafeState(byte[] v)
    {
	int[] dummy= new int [v.length];
	for(int i=0; i<v.length; i++)
	    {
		dummy[i]=(byte) v[i];
       	    }
	value= new AtomicIntegerArray(dummy);
	maxval=127;
    }

    BetterSafeState(byte[] v, byte m )
    {
	
	int[] dummy= new int [v.length];
	for (int i=0; i<v.length ; i++)
	    {
		dummy[i]=(byte) v[i];
		
	    }
	value= new AtomicIntegerArray(dummy);
	maxval=m;

    }

    //An error that I made was to use
    //.length instead of the class method .length()
    public int size() { return value.length(); }

    public byte[] current()
    {
	int v_le=value.length();
	byte Value_atPos[]= new byte[value.length()];
	for (int i=0; i<value.length(); i++)
	    {
		Value_atPos[i]=(byte) value.get(i);
      	    }
	return Value_atPos;
				
    }

    public boolean swap ( int i, int j)
    {
	if (value.get(i)<=0 || value.get(j)>=maxval) {
	    return false;
	}
	
	value.decrementAndGet(i); //Only changes to GetNSet is here!
	value.incrementAndGet(j);
	return true;
    }




}
