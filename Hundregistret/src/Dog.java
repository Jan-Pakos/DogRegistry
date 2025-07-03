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
        if (this.breed.equalsIgnoreCase("Dachshund") || this.breed.equalsIgnoreCase("Tax")) {
            this.tailLength = DASCHUND_TAIL_LENGTH;
        } else {
            this.tailLength = (double) age * ((double) weight / 10.0);
        }
    }

    public void updateDogAge() {
        if (age < Integer.MAX_VALUE) {
            age++;
            if (this.breed.equalsIgnoreCase("Dachshund") || this.breed.equalsIgnoreCase("Tax")) {
                this.tailLength = DASCHUND_TAIL_LENGTH;
            } else {
                this.tailLength = age * (weight / 10.0);
            }
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

    public boolean setOwner(Owner inputOwner) {
        // TA BORT OWNER
        if (inputOwner == null) {

            // OM HUNDEN HAR EN OWNER
            if (this.owner != null) {
                this.owner.removeDog(this);
                this.owner = null;
                return true;
            } else {
                return false;
            }
            // OM LÄGGA TILL OWNER
        } else {
            // OM HUNDEN INTE HAR EN OWNER LÄGG TILL
            if (this.owner == null) {
                // LÄGG TILL DENNA HUND TILL OWNERS LISTA AV HUNDAR
                inputOwner.addDog(this);
                this.owner = inputOwner;
                return true;
            } else {
                return false;
            }
        }
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Dog{name='" + name + "', breed='" + breed + "', age='" + age +
                "', weight='" + weight + "', taillength='" + String.format("%.1f", tailLength) +
                "', owner=" + (owner != null ? owner.getName() : "none") + "}";
    }

    private String normalizeText(String inputString) {
        return inputString.toUpperCase();
    }

}
