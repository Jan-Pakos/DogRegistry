// Jan Pakos japa4307

import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog dogOne, Dog dogTwo) {

        int tailCompare = Double.compare(dogOne.getTailLength(), dogTwo.getTailLength());


        if (tailCompare == 0) {
            return dogOne.getName().compareToIgnoreCase(dogTwo.getName());
        }

        return tailCompare;
    }
}