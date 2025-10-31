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
        assertEquals("Mac Pro (Late 2013)\n" + 
                    "3.5 GHc 6-Core Intel Xeon E5\n" + 
                    "1024\n" + 
                    "64\n" + 
                    "macOS Big Sur\n" + 
                    "2013\n" + 
                    "1500",
                    c.toString());
    }

    @Test
    public void testShopConstructor()
    {
        assertEquals(1, r.inventory.size());
        
        Computer initialComputer = r.inventory.get(0);
        assertEquals("2019 MacBook Pro", initialComputer.description);
        assertEquals("Intel", initialComputer.processorType);
        assertEquals(256, initialComputer.hardDriveCapacity);
        assertEquals(16, initialComputer.memory);
        assertEquals("High Sierra", initialComputer.operatingSystem);
        assertEquals(2019, initialComputer.yearMade);
        assertEquals(0, initialComputer.price);
    }

    @Test
    public void testBuy()
    {
        try
        {
            int initialSize = r.inventory.size();
            r.buy(c);
            assertEquals(initialSize + 1, r.inventory.size());
            assertSame(c, r.inventory.get(r.inventory.size()-1));
        }
        catch (Exception e) 
        {
            fail("buy method should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testBuyComputerInInventory()      
    {
        try
        {
            r.inventory.add(c);
            r.buy(c);
            fail("buy method should throw an exception when a computer is already in inventory");
        }
        catch (Exception e)
        {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testSell()
    {
        try
        {
            r.inventory.add(c);
            int size = r.inventory.size();
            r.sell(c);
            int sizeAfterSell = r.inventory.size();
            assertEquals(size-1, sizeAfterSell);
            assertFalse(r.inventory.contains(c));
        }
        catch (Exception e) 
        {
            fail("sell method should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testSellComputerNotInInventory()    
    {   
        try
        {
            r.sell(c);
            fail("sell method should throw an exception when the computer is not in inventory");
        }
        catch (Exception e)
        {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testPrintInventory()
    {
        try 
        {
            r.printInventory();
        } 
        catch (Exception e) 
        {
            fail("printInventory should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testRrefurbishOS()
    {
        try
        {
            r.inventory.add(c);
            r.refurbish(c, "MacOS");
            assertEquals("MacOS", c.operatingSystem);
        }
        catch(Exception e)
        {
            fail("refurbish method should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testRefurbishWithNoneOS() 
    {
        try 
        {
            r.inventory.add(c);
            r.refurbish(c, "None");
            assertEquals("macOS Big Sur", c.operatingSystem);
        }
        catch (Exception e)
        {
            fail("refurbish method should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testRefurbishPriceByYear() 
    {
        try 
        {
            Computer oldComputer = new Computer("Old", "Pentium", 80, 2, "Windows 98", 1999, 100);
            r.inventory.add(oldComputer);
            r.refurbish(oldComputer, "MacOS");
            assertEquals(0, oldComputer.price);
            
            Computer midComputer = new Computer("Mid", "Core 2 Duo", 320, 4, "Windows 7", 2010, 300);
            r.inventory.add(midComputer);
            r.refurbish(midComputer, "MacOS");
            assertEquals(2500, midComputer.price);
            
            Computer recentComputer = new Computer("Recent", "i5", 500, 8, "Windows 10", 2015, 500);
            r.inventory.add(recentComputer);
            r.refurbish(recentComputer, "MacOS");
            assertEquals(550, recentComputer.price);
            
            Computer newComputer = new Computer("New", "i7", 1000, 16, "Windows 11", 2020, 1000);
            r.inventory.add(newComputer);
            r.refurbish(newComputer, "MacOS");
            assertEquals(1000, newComputer.price);
        } 
        catch (Exception e) 
        {
            fail("refurbish method should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testRefurbishComputerNotInInventory() {
        try 
        {
            r.refurbish(c, "Windows 11");
            fail("refurbish method should throw an exception when the computer is not in inventory");
        } 
        catch (Exception e) 
        {
            assertEquals("Computer not in inventory", e.getMessage());
        }
    }
}
