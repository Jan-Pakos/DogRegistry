---

# Dog Register Application

This is a simple Java console application designed to manage a register of dogs and their owners. You can register new dogs and owners, assign dogs to owners, list registered entries, and perform various updates like increasing a dog's age or removing entries.

---

## What Does It Do?

The Dog Register allows you to:

* **Register new owners** by name.
* **Register new dogs** with details like name, breed, age, and weight.
* **Assign a dog to an owner** (a dog can only have one owner).
* **Remove a dog from its owner**.
* **Increase a dog's age** by one year.
* **Remove owners**, which also removes any dogs associated with them.
* **Remove dogs** from the register.
* **List all registered dogs**, with an option to filter by minimum tail length.
* **List all registered owners** and the dogs they own.

The program runs in an interactive console, where you type commands to perform actions.

---

## How to Use It

### Running the Application

1.  **Compile the Java code:** Make sure you have the `DogRegister.java`, `InputReader.java`, `Owner.java`, `Dog.java`, `OwnerCollection.java`, and `DogCollection.java` files (and any other necessary helper classes) in the same project directory.
2.  **Run from your IDE** (like IntelliJ IDEA or Eclipse) or compile and run from the command line:
    ```bash
    javac *.java
    java DogRegister
    ```

### Available Commands

Once the application starts, it will prompt you to "Enter command". Type one of the following commands and press Enter:

* `register new owner` - Adds a new owner to the system.
* `register new dog` - Adds a new dog to the system. You'll be prompted for its name, breed, age, and weight.
* `give dog to owner` - Assigns an unowned dog to an existing owner.
* `remove dog from owner` - Unassigns a dog from its current owner.
* `increase age` - Increases a dog's age by one year.
* `remove dog` - Removes a dog from the register. If the dog has an owner, it will first be unassigned.
* `remove owner` - Removes an owner and all dogs associated with that owner from the register.
* `list dogs` - Displays a list of all registered dogs. You'll be asked to enter a minimum tail length to filter the list.
* `list owners` - Displays a list of all registered owners and their dogs.
* `exit` - Shuts down the application.

### Example Workflow

1.  **Start the program:**
    ```
    Initializing program...
    Enter command>
    ```
2.  **Register an owner:**
    ```
    Enter command> register new owner
    Enter owner name> Alice
    The owner ALICE has been added to the register
    ```
3.  **Register a dog:**
    ```
    Enter command> register new dog
    Enter dog name> Buddy
    Enter dog breed> Labrador
    Enter dog age> 3
    Enter dog weight> 30
    The dog BUDDY has been added to the register
    ```
4.  **Give the dog to the owner:**
    ```
    Enter command> give dog to owner
    Enter dog name> Buddy
    Enter owner name> Alice
    Dog BUDDY has been given to owner ALICE
    ```
5.  **List dogs:**
    ```
    Enter command> list dogs
    Enter minimum dog tail length?> 0
    Name Breed Age Weight Tail Owner
    --------------------------------------------
    BUDDY Labrador 3 30 15.0 ALICE
    --------------------------------------------
    ```
6.  **Exit the application:**
    ```
    Enter command> exit
    Application shut down cleanly.
    ```
