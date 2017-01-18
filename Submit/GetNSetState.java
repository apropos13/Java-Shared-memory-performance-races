//GetNSet state implementation;
//Uses the AtomicIntegerArray class 
import java.util.concurrent.atomic.AtomicIntegerArray;
class GetNSetState implements State {

    private AtomicIntegerArray value;
    private byte maxval;
    
    //constructor has to fill all the values to AtomicIntegerArray
    GetNSetState(byte[] v)
    {
	int[] dummy= new int [v.length];
	for(int i=0; i<v.length; i++)
	    {
		dummy[i]=(byte) v[i];
       	    }
	value= new AtomicIntegerArray(dummy);
	maxval=127;
    }

    GetNSetState(byte[] v, byte m )
    {
	//GetNSet(v);
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
	
	value.set(i,value.get(i)-1);
	value.set(j,value.get(j)+1);
	return true;
    }




}