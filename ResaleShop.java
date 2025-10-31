import java.util.ArrayList;

public class ResaleShop {
    
    /*
     * Attrubutes
     */
    ArrayList<Computer> inventory; 

    /**
     * Constructor
     * @return an instance of ResaleShop
     */
    public ResaleShop() {
        this.inventory = new ArrayList<>();
        Computer newComputer = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        this.inventory.add(newComputer);

    }

    /**
     * Adds new computer to inventory
     * @param newComputer the computer to add to inventory 
     * @throws Exception if computer is already in inventory  
     */
    public void buy(Computer newComputer) throws RuntimeException {
        newComputer = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        this.inventory.add(newComputer); 
    }

    /**
     * Removes a computer from inventory 
     * @param comp computer to sell 
     * @throws Exception if computer is not in inventory 
     */
    public void sell(Computer comp) throws RuntimeException {
        this.inventory.remove(comp); 
    }

    /**
     * Prints inventory or empty message
     */
    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty");
        } else {
            for (int i = 0; i <= this.inventory.size(); i++){
                System.out.println(inventory.get(i));
            }
        }
    }

   /**
    * Refurbishes computer 
    * @param comp computer to refurbish
    * @param newOS "None" or new OS
    * @throws Exception if computer is not in inventory 
    */
    public void refurbish(Computer comp, String newOS) throws RuntimeException {
        if (!this.inventory.contains(comp)) {
            throw new RuntimeException("Computer not in inventory");
        } 
        if (comp.getYear() < 2000) {
            comp.setPrice(0);
        } else if (comp.getYear() < 2012) {
            comp.setPrice(2500);
        } else if (comp.getYear() < 2018) {
            comp.setPrice(550);
        } else {
            comp.setPrice(1000);
        }
        if (newOS != "None") {
            comp.setOS(newOS);
        }
    }

    public static void main(String[] args) {
        ResaleShop myShop = new ResaleShop(); 
        myShop.printInventory();
        Computer comp1 = new Computer("2019 MacBook Pro", "Intel", 256, 16, "High Sierra", 2019, 1000);
        try {
            myShop.buy(comp1);
            myShop.refurbish(comp1, "NEW OS");
            System.out.println(comp1);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


}
