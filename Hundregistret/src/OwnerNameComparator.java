// Jan Pakos japa4307
import java.util.ArrayList;
import java.util.Comparator;

public class OwnerNameComparator implements Comparator<Owner> {
    public int compare(Owner nameOne, Owner nameTwo) {
        return nameOne.getName().compareToIgnoreCase(nameTwo.getName());
    }
}

