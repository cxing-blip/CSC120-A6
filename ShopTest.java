import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShopTest {

    private Computer c;
    private ResaleShop r;

    @Before
    public void setUp()
    {
        c = new Computer("Mac Pro (Late 2013)",
                        "3.5 GHc 6-Core Intel Xeon E5",
                        1024,
                        64,
                        "macOS Big Sur",
                        2013,
                        1500);
        r = new ResaleShop();
    }
    
    @Test
    public void testComputerConstructor1()
    {
        assertEquals("Mac Pro (Late 2013)", c.description);
    }

    @Test
    public void testComputerConstructor2()
    {
        assertEquals("3.5 GHc 6-Core Intel Xeon E5", c.processorType);
    }

    @Test
    public void testComputerConstructor3()
    {
        assertEquals(1024, c.hardDriveCapacity);
    }

    @Test
    public void testComputerConstructor4()
    {
        assertEquals(64, c.memory);
    }

    @Test
    public void testComputerConstructor5()
    {
        assertEquals("macOS Big Sur", c.operatingSystem);
    }

    @Test
    public void testComputerConstructor6()
    {
        assertEquals(2013, c.yearMade);
    }

    @Test
    public void testComputerConstructor7()
    {
        assertEquals(1500, c.price);
    }

    @Test
    public void testSetPrice()
    {
        c.setPrice(1000);
        assertEquals(1000, c.price);
    }
    
    @Test
    public void testSetOS()
    {
        c.setOS("macOS");
        assertEquals("macOS", c.operatingSystem);
    }

    @Test
    public void testGetYear()
    {
        assertEquals(2013, c.getYear());
    }

    @Test
    public void testToString()
    {
        assertEquals(c.description+"\n" + 
                    c.processorType+"\n" + 
                    c.hardDriveCapacity+"\n"+ 
                    c.memory+"\n" + 
                    c.operatingSystem+"\n" + 
                    c.yearMade+"\n" + 
                    c.price,
                    c.toString());
    }

    @Test
    public void testShopConstructor()
    {
        assertEquals(0, r.inventory.size());
    }

    @Test
    public void testBuyComputerNotInInventory()
    {
        int initialSize = r.inventory.size();
        r.buy(c);
        assertEquals(initialSize + 1, r.inventory.size());
        assertSame(c, r.inventory.get(r.inventory.size()-1));
    }

    @Test (expected = RuntimeException.class)
    public void testBuyComputerInInventory()      
    {
        r.inventory.add(c);
        r.buy(c);
    }

    @Test
    public void testSellComputerInInventory()
    {
        r.inventory.add(c);
        int size = r.inventory.size();
        r.sell(c);
        int sizeAfterSell = r.inventory.size();
        assertEquals(size-1, sizeAfterSell);
        assertFalse(r.inventory.contains(c));
    }

    @Test (expected = RuntimeException.class)
    public void testSellComputerNotInInventory()    
    {   
        r.sell(c);
    }

    @Test
    public void testPrintInventory()
    {
        r.printInventory();
    }

    @Test
    public void testRrefurbishOS()
    {
        r.inventory.add(c);
        r.refurbish(c, "MacOS");
        assertEquals("MacOS", c.operatingSystem);
    }

    @Test
    public void testRefurbishWithNoneOS() 
    {
        r.inventory.add(c);
        r.refurbish(c, "None");
        assertEquals("macOS Big Sur", c.operatingSystem);
    }

    @Test
    public void testRefurbishPriceByYear() 
    {
        Computer oldComputer = new Computer("Old", "Pentium", 80, 2, "Windows 98", 1999, 100);
        r.inventory.add(oldComputer);
        r.refurbish(oldComputer, "MacOS");
        assertEquals(0, oldComputer.price);
        
        Computer midComputer = new Computer("Mid", "Core 2 Duo", 320, 4, "Windows 7", 2010, 300);
        r.inventory.add(midComputer);
        r.refurbish(midComputer, "MacOS");
        assertEquals(250, midComputer.price);
        
        Computer recentComputer = new Computer("Recent", "i5", 500, 8, "Windows 10", 2015, 500);
        r.inventory.add(recentComputer);
        r.refurbish(recentComputer, "MacOS");
        assertEquals(550, recentComputer.price);
        
        Computer newComputer = new Computer("New", "i7", 1000, 16, "Windows 11", 2020, 1000);
        r.inventory.add(newComputer);
        r.refurbish(newComputer, "MacOS");
        assertEquals(1000, newComputer.price);
    }

    @Test (expected = RuntimeException.class)
    public void testRefurbishComputerNotInInventory() 
    {
        r.refurbish(c, "Windows 11");
    }
}
