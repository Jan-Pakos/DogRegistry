// Jan Pakos japa4307
import java.util.Comparator;

public class DogTailComparator implements Comparator<Dog> {
    public int compare(Dog tailOne, Dog tailTwo) {
        return Double.compare(tailOne.getTailLength(), tailTwo.getTailLength());
    }
}