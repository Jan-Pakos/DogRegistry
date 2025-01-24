// Jan Pakos japa4307
public class Dog {

    private static final double DASCHUND_TAIL_LENGTH = 3.7;
    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = normalizeText(name);
        this.breed = normalizeText(breed);
        this.age = age;
        this.weight = weight;
        if (this.breed.equalsIgnoreCase("Daschund") || this.breed.equalsIgnoreCase("Tax")) {
            this.tailLength = DASCHUND_TAIL_LENGTH;
        } else {
            this.tailLength = (double) age * ((double) weight / 10.0);
        }
    }

    public void updateDogAge() {
        age++;
        if (this.breed.equalsIgnoreCase("Daschund") || this.breed.equalsIgnoreCase("Tax")) {
            this.tailLength = DASCHUND_TAIL_LENGTH;
        } else {
            this.tailLength = age * (weight / 10.0);
        }
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        return tailLength;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', owner=" + (owner != null ? owner.getName() : "none") + "}";
    }

    private String normalizeText(String inputString) {
        return inputString.toUpperCase();
    }

    public boolean setOwner(Owner inputOwner) {
        // if owner in input is null == means we want to remove owner
        if (inputOwner != null) {
            // if owner is not already null
            if (this.owner != null) {
                this.owner = null;
                // remove dog from dogList in Owner class
                inputOwner.removeDog(this);
                return true;
            } else {
                return false;
            }
        } else {
            // if owner in input is NOT null = we want to add a new owner
            // if owner is null
            // add owner
            this.owner = inputOwner;
            // add dog to dogList in owner class
            inputOwner.addDog(this);
            return true;
        }
    }
    public Owner getOwner () {
        return this.owner;
    }

}
