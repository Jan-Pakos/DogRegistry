// Jan Pakos japa4307
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class OwnerCollection {

    private Owner[] ownerArray;

    public OwnerCollection(){
        ownerArray = new Owner[0];
    }

    public boolean addOwner(Owner owner) {
        if (containsOwner(owner.getName())) {
            System.out.println("Owner already exists");
            return false;
        } else {

            for(int i = 0; i < ownerArray.length; i++){
                if(ownerArray[i] == null){
                    ownerArray[i] = owner;
                    return true;
                }
            }
            Owner[] newArray = new Owner[ownerArray.length + 1];
            System.arraycopy(ownerArray, 0, newArray, 0, ownerArray.length);
            newArray[newArray.length - 1] = owner;
            ownerArray = newArray;
            System.out.println("Owner added");
            return true;
        }
    }

    public boolean removeOwner(String ownerName) {
        if (containsOwner(ownerName)) {
            Owner[] newArray = new Owner[ownerArray.length - 1];
            int indexCounter = 0;
            for (Owner owner : ownerArray) {
                if (owner != null && owner.getName().equals(ownerName)) {
                    continue;
                } else {
                    newArray[indexCounter] = owner;
                    indexCounter++;
                }
            }
            System.arraycopy(newArray, 0, ownerArray, 0, newArray.length);
            ownerArray[ownerArray.length - 1] = null;
            return true;
        }
        return false;
    }

    public boolean removeOwner(Owner ownerToRemove) {
        if (containsOwner(ownerToRemove)) {
            Owner[] newArray = new Owner[ownerArray.length - 1];
            int indexCounter = 0;
            for (Owner owner : ownerArray) {
                if (owner != null && owner.equals(ownerToRemove)) {
                    continue;
                } else {
                    newArray[indexCounter] = owner;
                    indexCounter++;
                }
            }
            System.arraycopy(newArray, 0, ownerArray, 0, newArray.length);
            ownerArray[ownerArray.length - 1] = null;
            return true;
        }
        return false;
    }

    public boolean containsOwner(Owner owner) {
        for (Owner o : ownerArray) {
            if (o != null && o.equals(owner)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOwner(String ownerName) {
        for (Owner owner : ownerArray) {
            if (owner != null && owner.getName().equals(ownerName)) {
                return true;
            }
        }
        return false;
    }

    public Owner getOwner(String ownerName){
        for (Owner owner : ownerArray){
            if(owner != null && owner.getName().equals(ownerName)) {
                return owner;
            }
        }
        return null;
    }

    public ArrayList<Owner> getOwners() {
        ArrayList<Owner> ownerList = new ArrayList<>();
        for (Owner owner : ownerArray) {
            if (owner != null) {
                ownerList.add(owner);
            }
        }
        Collections.sort(ownerList, new Comparator<Owner>() {
            @Override
            public int compare(Owner o1, Owner o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return ownerList;
    }
}

