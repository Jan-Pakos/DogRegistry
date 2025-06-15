// Jan Pakos japa4307

import java.util.ArrayList;
import java.util.Comparator;

public class Owner implements Comparable<Owner> {

    private String name;
    private ArrayList<Dog> listOfOwnedDogs;

    public Owner(String inputName) {
        String normalizedName = normalizeString(inputName);
        this.setName(normalizedName);
        this.listOfOwnedDogs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addDog(Dog dog) {
        if (listOfOwnedDogs.contains(dog) || dog.getOwner() != null) {
            return false;
        } else {
            listOfOwnedDogs.add(dog);
            return true;
        }
    }

    public boolean removeDog(Dog dog) {
        if (listOfOwnedDogs.contains(dog) && dog.getOwner() == this) {
            listOfOwnedDogs.remove(dog);
            return true;
        }
        return false;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> copyOfList = new ArrayList<>(listOfOwnedDogs);
        copyOfList.sort(Comparator.comparing(Dog::getName));
        return copyOfList;
    }

    public int compareTo(Owner other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Owner{name='" + name + "', dogs=" + listOfOwnedDogs + "}";
    }

    private String normalizeString(String inputString) {
        return inputString.substring(0, 1).toUpperCase() + inputString.substring(1).toLowerCase();
    }

    private void setName(String inputName) {
        this.name = inputName.toUpperCase();
    }

}
