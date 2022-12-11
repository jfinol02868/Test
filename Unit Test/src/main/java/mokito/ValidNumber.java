package mokito;

public class ValidNumber {

    public ValidNumber() {}

    public boolean check(Object o) {
        if(o instanceof Integer) {
            if((Integer) o > 0 && (Integer) o < 10) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean checkZero(Object o){
        if(o instanceof Integer) {
            if((Integer) o == 0 ) {
                throw new ArithmeticException("Debe imgrezar un nùmero mayor a zero");
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public int doubleToInt(Object o) {
        if( o instanceof Double) {
            return ((Double)o).intValue();
        }else{
            return 0;
        }
    }
}
