// Jan Pakos japa4307

import java.util.ArrayList;


public class DogCollection{

    private ArrayList<Dog> dogList;

    public DogCollection() {
        this.dogList = new ArrayList<>();
    }

    public boolean addDog(Dog dog) {
        // kalla på containsDog med hundens namn som String
        if (!containsDog(dog.getName())) {
            dogList.add(dog);
            System.out.println("The dog was added");
            return true;
        } else {
            System.out.println("The dog was NOT added");
            return false;
        }
    }


    public boolean removeDog(String dogName){
        Dog dogToRemove = getDog(dogName);
        if(containsDog(dogName)){
            dogList.remove(dogToRemove);
            System.out.println("The dog was removed from the list");
            return true;
        }
        else {
            System.out.println("Dog was not found in the list");
            return false;
        }
    }

    public boolean removeDog(Dog dog){
        if(containsDog(dog)){
            dogList.remove(dog);
            System.out.println("The dog was removed from the list");
            return true;
        } else {
            System.out.println("The dog was not found in the list");
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

    public Dog getDog(String dogName){
        for (Dog dog : dogList){
            if (dog.getName().equals(dogName)){
                return dog;
            }

        }
        return null;
    }

    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> sortedDogs = new ArrayList<>(dogList);
        DogSorter.sortDogs(new DogNameComparator(),sortedDogs);
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