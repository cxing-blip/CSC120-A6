## Bug 1
Brief description: The Computer Constructor does not correctly set computer memory. It always sets a memory of 16.
Failed unit test: testComputerConstructor4()

## Bug 2
Brief description: The Computer Constructor does not correctly set computer price. It always sets a memory of 0.
Failed unit test: testComputerConstructor7()

## Bug 3
Brief description: The SetOS method in the Computer Class does not set the correct new OS. It always sets OS as "None."
Failed unit test: testSetOS()

## Bug 4
Brief description: The ResaleShop Constructor does not correctly set inventory to zero. It always adds a new computer.
Failed unit test: testShopConstructor()

## Bug 5
Brief description: The ResaleShop doesn't buy a designated computer. It always buys the same computer.
Failed unit test: testBuyComputerNotInInventory()

## Bug 6
Brief description: The buy method of Resaleshop doesn't throw an exception when a computer is already in the inventory.
Failed unit test: testBuyComputerInInventory() 

## Bug 7
Brief description: The sell method of Resaleshop doesn't throw an exception when trying to sell a computer that is not in the inventory.
Failed unit test: testSellComputerNotInInventory()

## Bug 8
Brief description: The printInventory method of Resaleshop throws an IndexOutOfBoundsException but it shouldn't throw an exception.
Failed unit test: testPrintInventory()

## Bug 9
Brief description: The refurbish method of the Resaleshop does not refurbish OS of a computer correctly. It always refurbishes OS as "None."
Failed unit test: testRrefurbishOS()

## Bug 10
Brief description: The refurbish method of the Resaleshop does not refurbish the price of computers whose year is between 2012 and 2018 correctly. It should be set to 250 instead of 2500.
Failed unit test: testRefurbishPriceByYear()