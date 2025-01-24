// Jan Pakos japa4307
import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog> {
    public int compare(Dog nameOne, Dog nameTwo) {
        return nameOne.getName().compareToIgnoreCase(nameTwo.getName());
    }
}