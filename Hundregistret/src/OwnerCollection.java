// Jan Pakos japa4307

import java.util.*;

public class OwnerCollection {
    private Owner[] ownerArray;

    public OwnerCollection() {
        ownerArray = new Owner[0];
    }

    public boolean addOwner(Owner owner) {
        if (containsOwner(owner.getName())) {
            return false;
        } else {
            for (int i = 0; i < ownerArray.length; i++) {
                if (ownerArray[i] == null) {
                    ownerArray[i] = owner;
                    return true;
                }
            }
            Owner[] newArray = new Owner[ownerArray.length + 1];
            System.arraycopy(ownerArray, 0, newArray, 0, ownerArray.length);
            newArray[newArray.length - 1] = owner;
            ownerArray = newArray;
            return true;
        }
    }

    public boolean removeOwner(String ownerName) {
        Owner ownerToRemove = getOwner(ownerName);
        if (ownerToRemove != null && ownerToRemove.getDogs().isEmpty()) {
            for (int i = 0; i < ownerArray.length; i++) {
                if (ownerArray[i] != null && ownerArray[i].getName().equals(ownerName)) {
                    ownerArray[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeOwner(Owner ownerToRemove) {
        if (ownerToRemove != null && containsOwner(ownerToRemove) && ownerToRemove.getDogs().isEmpty()) {
            for (int i = 0; i < ownerArray.length; i++) {
                if (ownerArray[i] != null && ownerArray[i].equals(ownerToRemove)) {
                    ownerArray[i] = null;
                    return true;
                }
            }
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

    public Owner getOwner(String ownerName) {
        for (Owner owner : ownerArray) {
            if (owner != null && owner.getName().equals(ownerName)) {
                return owner;
            }
        }
        return null;
    }

    public ArrayList<Owner> getOwners() {
        ArrayList<Owner> listToReturn = new ArrayList<>();
        for (Owner owner : ownerArray) {
            if (owner != null) {
                listToReturn.add(owner);
            }
        }
        Collections.sort(listToReturn);
        // OwnerSorter.sortOwners(new OwnerNameComparator(), listToReturn);
        return listToReturn;
    }
}