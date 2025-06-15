// Jan Pakos japa4307

import java.util.ArrayList;


public class DogCollection {

    private ArrayList<Dog> dogList;

    public DogCollection() {
        this.dogList = new ArrayList<>();
    }

    public boolean addDog(Dog dog) {
        // kalla på containsDog med hundens namn som String
        if (!containsDog(dog.getName())) {
            dogList.add(dog);
            return true;
        } else {
            return false;
        }
    }


    public boolean removeDog(String dogName) {
        if (!containsDog(dogName)) {
            return false;
        }

        Dog dogToRemove = getDog(dogName);
        if (dogToRemove.getOwner() != null) {
            return false;
        }

        dogList.remove(dogToRemove);
        return true;
    }

    public boolean removeDog(Dog dog) {
        if (containsDog(dog) && dog.getOwner() == null) {
            dogList.remove(dog);
            return true;
        } else {
            return false;

        }
    }

    public boolean containsDog(String dogName) {
        for (Dog dog : dogList) {
            if (dog.getName().equals(dogName)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDog(Dog dog) {
        return dogList.contains(dog);
    }

    public Dog getDog(String dogName) {
        for (Dog dog : dogList) {
            if (dog.getName().equals(dogName.toUpperCase())) {
                return dog;
            }
        }
        return null;
    }

    public ArrayList<Dog> getDogs() {
        ArrayList<Dog> sortedDogs = new ArrayList<>(dogList);
        DogSorter.sortDogs(new DogNameComparator(), sortedDogs);
        return sortedDogs;
    }

    public ArrayList<Dog> getDogsWithAtLeastTailLength(double tailLength) {
        ArrayList<Dog> filteredDogs = new ArrayList<>();
        for (Dog dog : dogList) {
            if (dog.getTailLength() >= tailLength) {
                filteredDogs.add(dog);
            }
        }
        DogSorter.sortDogs(new DogTailNameComparator(), filteredDogs);
        return filteredDogs;
    }

}