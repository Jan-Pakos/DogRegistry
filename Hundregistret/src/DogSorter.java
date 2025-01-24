// Jan Pakos japa4307
import java.util.ArrayList;
import java.util.Comparator;


public class DogSorter {
    private static void swapDogs(ArrayList<Dog> dogArrayList, int indexOne, int indexTwo){
        Dog temp = dogArrayList.get(indexOne);
        dogArrayList.set(indexOne, dogArrayList.get(indexTwo));
        dogArrayList.set(indexTwo, temp);

    }

    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogs, int startIndex) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i < dogs.size(); i++) {
            if (comparator.compare(dogs.get(i), dogs.get(minIndex)) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int sortDogs(Comparator<Dog> comparator, ArrayList<Dog> dogs){
        int swapsMade = 0;
        for(int i = 0; i<dogs.size();i++){
            int indexOfDogToSwap = DogSorter.nextDog(comparator, dogs, i);
            if(indexOfDogToSwap != i){
                swapDogs(dogs, i, indexOfDogToSwap);
                swapsMade++;


            }

        }
        return swapsMade;

    }

}
