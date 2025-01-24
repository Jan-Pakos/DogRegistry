// Jan Pakos japa4307
import java.util.ArrayList;

public class Owner implements Comparable<Owner> {

    private String name;
    private ArrayList<Dog> listOfOwnedDogs = new ArrayList<>();

    public Owner(String inputName){
        String normalizedName = normalizeString(inputName);
        this.setName(normalizedName);
    }

    public String getName(){
        return name;
    }

    private void setName(String inputName){
        this.name = inputName.toUpperCase();
    }

    public int compareTo(Owner other){
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Owner{name='" + name + "', dogs=" + listOfOwnedDogs + "}";
    }

    private String normalizeString(String inputString){
        return inputString.substring(0, 1).toUpperCase() + inputString.substring(1).toLowerCase();
    }

    public boolean addDog(Dog dog){
        if (dog.getOwner() != this) {
            System.out.println("Dog does not have the correct owner set.");
            return false;
        }
        if (listOfOwnedDogs.contains(dog)){
            System.out.println("Hunden finns redan");
            return false;
        } else {
            listOfOwnedDogs.add(dog);
            return true;
        }
    }

    public boolean removeDog(Dog dog){
        if (listOfOwnedDogs.contains(dog) && dog.getOwner() == this){
            listOfOwnedDogs.remove(dog);
            dog.setOwner(null);
            return true;
        }
        return false;
    }

    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> sortedDogs = new ArrayList<>(listOfOwnedDogs);
        DogSorter.sortDogs(new DogNameComparator(), sortedDogs);
        return sortedDogs;
    }
}
