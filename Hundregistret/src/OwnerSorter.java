// Jan Pakos japa4307

import java.util.ArrayList;
import java.util.Comparator;

public class OwnerSorter {

    public static int sortOwners(Comparator<Owner> comparator, ArrayList<Owner> owners) {
        int swapsMade = 0;
        for (int i = 0; i < owners.size(); i++) {
            int indexOfOwnerToSwap = OwnerSorter.nextOwner(comparator, owners, i);
            if (indexOfOwnerToSwap != i) {
                swapOwners(owners, i, indexOfOwnerToSwap);
                swapsMade++;
            }
        }
        return swapsMade;
    }

    private static void swapOwners(ArrayList<Owner> ownerArrayList, int indexOne, int indexTwo) {
        Owner temp = ownerArrayList.get(indexOne);
        ownerArrayList.set(indexOne, ownerArrayList.get(indexTwo));
        ownerArrayList.set(indexTwo, temp);

    }

    private static int nextOwner(Comparator<Owner> comparator, ArrayList<Owner> owners, int startIndex) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i < owners.size(); i++) {
            if (comparator.compare(owners.get(i), owners.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
