// Jan Pakos japa4307

import java.util.ArrayList;
import java.util.Collections;

public class DogRegister {


    private static final String EXIT_COMMAND = "exit";
    private static final String REGISTER_OWNER_COMMAND = "register new owner";
    private static final String REMOVE_OWNER_COMMAND = "remove owner";
    private static final String REGISTER_DOG_COMMAND = "register new dog";
    private static final String REMOVE_DOG_COMMAND = "remove dog";
    private static final String LIST_DOGS_COMMAND = "list dogs";
    private static final String LIST_OWNERS_COMMAND = "list owners";
    private static final String INCREASE_AGE_COMMAND = "increase age";
    private static final String GIVE_DOG_TO_OWNER_COMMAND = "give dog to owner";
    private static final String REMOVE_DOG_FROM_OWNER_COMMAND = "remove dog from owner";

    private InputReader inputReader;
    private OwnerCollection ownerCollection;
    private DogCollection dogCollection;


    private void start() {
        initialize();
        runCommandLoop();
        System.out.println("Application shut down cleanly.");
    }

    public static void main(String[] args) {
        new DogRegister().start();
    }

    private void initialize() {
        System.out.println("Initializing program...");
        ownerCollection = new OwnerCollection();
        dogCollection = new DogCollection();
        inputReader = new InputReader();

    }

    private void runCommandLoop() {
        String command;
        do {
            command = readCommand();
            handleCommand(command);
        } while (!command.equals(EXIT_COMMAND));
    }

    private void handleCommand(String command) {
        switch (command) {
            case EXIT_COMMAND:
                break;
            case REGISTER_OWNER_COMMAND:
                registerOwner();
                break;
            case LIST_DOGS_COMMAND:
                listDogs();
                break;
            case REMOVE_OWNER_COMMAND:
                removeOwner();
                break;
            case LIST_OWNERS_COMMAND:
                listOwners();
                break;
            case REGISTER_DOG_COMMAND:
                registerNewDog();
                break;
            case REMOVE_DOG_COMMAND:
                removeDog();
                break;
            case GIVE_DOG_TO_OWNER_COMMAND:
                giveDogToOwner();
                break;
            case INCREASE_AGE_COMMAND:
                increaseDogAge();
                break;
            case REMOVE_DOG_FROM_OWNER_COMMAND:
                removeDogFromOwner();
                break;
            default:
                System.out.println("Error: Invalid command");
        }
    }


    private String readCommand() {
        return inputReader.readLine("Enter command").trim().toLowerCase();
    }


    private void registerOwner() {
        String ownerName = getNonEmptyInput("Enter owner name");
        ownerName = ownerName.trim().toUpperCase();
        if (ownerCollection.containsOwner(ownerName)) {
            System.out.println("Error The owner " + ownerName + " already exists");
        } else {
            Owner owner = new Owner(ownerName);
            ownerCollection.addOwner(owner);
            System.out.println("The owner " + ownerName + " has been added to the register");
        }

    }

    private void listDogs() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error there are no dogs in the register");
        } else {
            double dogTailLength = inputReader.readDouble("Enter minimum dog tail length?> ");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Name Breed Age Weight Tail Owner\n");
            stringBuilder.append("--------------------------------------------\n");

            for (Dog dog : dogCollection.getDogsWithAtLeastTailLength(dogTailLength)) {
                stringBuilder.append(dog.getName()).append(" ").append(dog.getBreed()).append(" ").append(dog.getAge()).append(" ").append(dog.getWeight()).append(" ").append(dog.getTailLength());
                if (dog.getOwner() != null) {
                    stringBuilder.append(" ").append(dog.getOwner().getName()).append("\n");
                } else {
                    stringBuilder.append("\n");
                }
            }
            stringBuilder.append("--------------------------------------------\n");
            System.out.println(stringBuilder.toString());
        }
    }

    private void removeOwner() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error there are no owners in the register");
        } else {
            String ownerName = inputReader.readLine("Enter owner name");
            ownerName = ownerName.trim().toUpperCase();
            if (!ownerCollection.containsOwner(ownerName)) {
                System.out.println("Error The owner " + ownerName + " does not exist");
            } else {
                Owner owner = ownerCollection.getOwner(ownerName);
                if (owner.getDogs().isEmpty()) {
                    ownerCollection.removeOwner(ownerName);
                } else {
                    for (Dog dog : dogCollection.getDogs()) {
                        if (dog.getOwner() != null && dog.getOwner().equals(owner)) {
                            dog.setOwner(null);
                            dogCollection.removeDog(dog);
                        }
                    }
                    ownerCollection.removeOwner(ownerName);
                }

            }
            System.out.println("The owner " + ownerName + " and their dogs have been removed from the register");
        }
    }

    private void listOwners() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error there are no owners in the register");
        } else {
            ArrayList<Owner> owners = new ArrayList<>(ownerCollection.getOwners());
            owners.removeAll(Collections.singleton(null));
            Collections.sort(owners);
            StringBuilder stringBuilder = new StringBuilder();
            for (Owner owner : owners) {
                if (owner != null) {
                    stringBuilder.append(owner.getName()).append(" ");
                    if (!owner.getDogs().isEmpty()) {
                        stringBuilder.append(" ").append(owner.getDogs()).append("\n");
                    } else {
                        stringBuilder.append("\n");
                    }
                }
            }
            System.out.println(stringBuilder.toString());
        }
    }

    private void registerNewDog() {
        String dogName = getValidDogName();
        if (dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " already exists, try again");
        } else {
            String breed = getValidBreed();
            int age = getValidAge();
            int weight = getValidWeight();

            Dog dogToAdd = new Dog(dogName, breed, age, weight);

            dogCollection.addDog(dogToAdd);
            System.out.println("The dog " + dogName + " has been added to the register");
        }

    }


    private void removeDog() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error there are no dogs in the register");
        } else {
            String dogName = inputReader.readLine("Enter dog name");
            dogName = dogName.toUpperCase();
            if (!dogCollection.containsDog(dogName)) {
                System.out.println("Error: The dog " + dogName + " does not exist, try again");
            } else {
                Dog dog = dogCollection.getDog(dogName);
                // System.out.println("created new instance of " + dog);
                dog.setOwner(null);
                // System.out.println("after set owner to null " + dog.getOwner());
                dogCollection.removeDog(dogName);
                System.out.println("Dog " + dogName + " has been removed from the register");
            }
        }
    }

    private void giveDogToOwner() {
        if (!validateCollections()) return;

        String dogName = getNonEmptyInput("Enter dog name");
        Dog dog = validateAndGetDog(dogName);
        if (dog == null) return;

        if (dog.getOwner() != null) {
            System.out.println("Error: Dog already has an owner");
            return;
        }

        String ownerName = getNonEmptyInput("Enter owner name");
        Owner owner = validateAndGetOwner(ownerName);
        if (owner == null) return;

        dog.setOwner(owner);
        System.out.println("Dog " + dogName + " has been given to owner " + ownerName);
    }

    private void increaseDogAge() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: There are no dogs in the register, try again");
        } else {
            String dogName = inputReader.readLine("Enter dog name");

            if (!dogCollection.containsDog(dogName.toUpperCase())) {
                System.out.println("Error: Dog does not exist, try again");
            } else {
                while (dogName.trim().isEmpty()) {
                    System.out.println("Error: A blank string is not allowed, try again");
                    dogName = inputReader.readLine("Enter dog name");
                }
                dogCollection.getDog(dogName.trim().toUpperCase()).updateDogAge();
            }
        }
    }

    private void removeDogFromOwner() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: there are no dogs in the register");
        } else if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: there are no owners in the register, try again");
        } else {
            String dogName = getNonEmptyInput("Enter dog name");
            if (!dogCollection.containsDog(dogName.trim().toUpperCase())) {
                System.out.println("Error: Dog does not exist, try again");
            } else if (dogCollection.getDog(dogName.trim().toUpperCase()).getOwner() == null) {
                System.out.println("Error: Dog does not have an owner");
            } else {
                Dog dog = dogCollection.getDog(dogName.trim().toUpperCase());
                Owner owner = dog.getOwner();
                dog.setOwner(null);
                owner.removeDog(dog);
            }
        }
    }

    /// ///////////////////////////////// HJÄLPMETODER /////////////////////////////////////////////

    private static boolean stringHasDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private String getValidDogName() {
        String dogName;
        dogName = inputReader.readLine("Enter dog name");
        dogName = dogName.trim().toUpperCase();

        while (dogName.isEmpty() || stringHasDigits(dogName)) {
            System.out.println("Error: A blank string or number is not allowed, try again");
            dogName = inputReader.readLine("Enter dog name");
            dogName = dogName.trim().toUpperCase();
        }
        return dogName;
    }

    private String getValidBreed() {
        String breed;
        do {
            breed = inputReader.readLine("Enter dog breed");
            if (breed.trim().isEmpty() || stringHasDigits(breed)) {
                System.out.println("Error: numbers or blanks are allowed in the breed");
            }
        } while (breed.trim().isEmpty() || stringHasDigits(breed));
        return breed;
    }

    private int getValidAge() {
        int age;
        do {
            age = inputReader.readInt("Enter dog age");
            if (age < 0) {
                System.out.println("Error: Age must be a positive integer, try again");
            }
        } while (age < 0);
        return age;
    }

    private int getValidWeight() {
        int weight;
        do {
            weight = inputReader.readInt("Enter dog weight");
            if (weight < 0) {
                System.out.println("Error: Weight must be a positive integer, try again");
            }
        } while (weight < 0);
        return weight;
    }

    private boolean validateCollections() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: there are no dogs in the register");
            return false;
        }
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: there are no owners in the register, try again");
            return false;
        }
        return true;
    }

    private String getNonEmptyInput(String prompt) {
        String input = inputReader.readLine(prompt);
        while (input.trim().isEmpty()) {
            System.out.println("Error: A blank string is not allowed, try again");
            input = inputReader.readLine(prompt);
        }
        return input;
    }

    private Dog validateAndGetDog(String dogName) {
        if (!dogCollection.containsDog(dogName.trim().toUpperCase())) {
            System.out.println("Error: Dog does not exist, try again");
            return null;
        }
        Dog dog = dogCollection.getDog(dogName.trim().toUpperCase());
        System.out.println(dog);
        return dog;
    }

    private Owner validateAndGetOwner(String ownerName) {
        ownerName = ownerName.trim().toUpperCase();
        if (!ownerCollection.containsOwner(ownerName)) {
            System.out.println(ownerCollection.getOwners());
            System.out.println("Error: Owner does not exist, try again");
            return null;
        }
        Owner owner = ownerCollection.getOwner(ownerName);
        System.out.println(ownerCollection.getOwner(ownerName));
        return owner;
    }


}

