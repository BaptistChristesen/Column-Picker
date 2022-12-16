import kareltherobot.*;
import java.util.concurrent.Callable;

/**
 * Counts the number of beepers in a column and puts the same
 * number of beepers at the base of the column
 * 
 * @author 
 * @version 
 */
public class ColumnPicker extends Robot
{
    public ColumnPicker(int street, int avenue,
    Direction direction, int beepers){
        super(street, avenue, direction, beepers);
    }
    
    public int countABeeper(){
        if (!nextToABeeper()){
            return 0;
        }
        pickBeeper();
        return 1 + countABeeper();
    }
    
    public int pickAllBeepers(){
        int count;
        if (!nextToABeeper()){
            return 0;
        }
        else {
            count = countABeeper();
            return count;
        }
    }
    
    public void putNBeepers(int numToPut)
    {
        if (numToPut == 0){
            return;
        }
        else{
            putBeeper();
            putNBeepers(numToPut - 1);
        }
    }
    
    public int countColumn(int streets)
    {
        int first, count;
        if (streets == 0){
            turnLeft();
            turnLeft();
            return 0;
        }
        else{
            move();
            first = pickAllBeepers();
            putNBeepers(first);
            count = first + countColumn(streets - 1);
            move();
            return count;
        }
    }
    
    public void singleColumn(){
        int total = countColumn(8);
        putNBeepers(total);
    }
    
    public void countColumns(){
        if (nextToABeeper()){
            turnOff();
            return;
        }
        else{
            singleColumn();
            turnLeft();
            move();
            turnLeft();
            countColumns();
        }   
    }
}