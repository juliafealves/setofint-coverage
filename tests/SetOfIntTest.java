import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class SetOfIntTest {

    private SetOfInt setOfInt;

    @Before
    public void inicializa(){
        this.setOfInt = new SetOfInt();
    }

    @Test
    public void testSetOfIntInitialCapacityMenorZero(){
        new SetOfInt(1);
    }

    @Test
    public void testSetOfIntInitialCapacityMaiorZero(){
        new SetOfInt(100);
    }

    @Test
    public void testAddMaiorZero(){
        this.setOfInt.add(10);
    }

    @Test
    public void testContainsTrue(){
        this.setOfInt.add(10);
        boolean contem = this.setOfInt.contains(10);

        Assert.assertTrue(contem);
    }

    @Test
    public void testContainsFalse(){
        this.setOfInt.add(10);
        boolean contem = this.setOfInt.contains(100);

        Assert.assertFalse(contem);
    }

    @Test
    public void testContainsVazio(){
        Assert.assertFalse(this.setOfInt.contains(100));
    }

    @Test
    public void testEnsureCapacity(){
        SetOfInt set = new SetOfInt(1);
        set.add(10);
        set.ensureCapacity(3);
        Assert.assertEquals(3, set.getCapacity());
    }

    @Test
    public void testGetCapacity(){
        Assert.assertEquals(10, this.setOfInt.getCapacity());
    }

    @Test
    public void testSize(){
        this.setOfInt.add(10);
        this.setOfInt.add(20);
        Assert.assertEquals(2, this.setOfInt.size());
    }

    @Test
    public void testAddJaContemItem(){
        this.setOfInt.add(10);
        Assert.assertEquals(1, this.setOfInt.size());
        this.setOfInt.add(10);
        Assert.assertEquals(1, this.setOfInt.size());
    }

    @Test
    public void testAddAtingeLimte(){
        SetOfInt set = new SetOfInt(1);
        set.add(10);
        set.add(7);
        Assert.assertEquals(3, set.getCapacity());
    }

    @Test
    public void testAdd(){
        this.setOfInt.add(10);
        this.setOfInt.add(20);
        Assert.assertEquals(2, this.setOfInt.size());
    }

    // data.length != manyItems
    @Test
    public void testTrimToSize(){
        this.setOfInt.add(2);
        this.setOfInt.add(5);
        this.setOfInt.add(8);
        this.setOfInt.trimToSize();
        Assert.assertEquals(3, this.setOfInt.getCapacity());
        Assert.assertEquals(3, this.setOfInt.size());
    }

    // data.length == manyItems
    @Test
    public void testTrimToSizeIguais(){
        SetOfInt set = new SetOfInt(2);
        set.add(1);
        set.add(2);
        set.trimToSize();
        Assert.assertEquals(2, set.getCapacity());
        Assert.assertEquals(2, set.getCapacity());
    }

    @Test
    public void testPrintSet(){
        this.setOfInt.add(10);
        this.setOfInt.printSet();
    }

    @Test
    public void testPrintSetVazio(){
        this.setOfInt.printSet();
    }

    @Test
    public void testClone(){
        this.setOfInt.add(10);
        this.setOfInt.add(20);
        SetOfInt set = (SetOfInt) this.setOfInt.clone();
        Assert.assertTrue(set.contains(10));
        Assert.assertTrue(set.contains(20));
    }

    @Test
    public void testRemove(){
        this.setOfInt.add(10);
        this.setOfInt.add(20);
        Assert.assertEquals(2, this.setOfInt.size());
        this.setOfInt.remove(10);
        Assert.assertEquals(1, this.setOfInt.size());
        this.setOfInt.remove(20);
        Assert.assertEquals(0, this.setOfInt.size());
    }

    @Test
    public void testRemoveVazio(){
        Assert.assertFalse(this.setOfInt.remove(10));
    }

    @Test
    public void testRemoveNaoExite(){
        this.setOfInt.add(200);
        this.setOfInt.add(7);
        Assert.assertFalse(this.setOfInt.remove(10));
    }

    @Test
    public void testAddAll(){
        this.setOfInt.add(200);
        this.setOfInt.add(7);
        SetOfInt set = new SetOfInt(2);
        set.add(10);
        Assert.assertEquals(2, set.getCapacity());
        set.addAll(this.setOfInt);
        Assert.assertEquals(3, set.getCapacity());
    }

    @Test (expected = NullPointerException.class)
    public void testAddAllNulo(){
        SetOfInt set = new SetOfInt(2);
        set.add(10);
        set.addAll(null);
    }

    @Test
    public void testAddAllElementosIguais(){
        this.setOfInt.add(200);
        this.setOfInt.add(7);
        SetOfInt set = new SetOfInt(2);
        set.add(10);
        set.add(7);
        set.addAll(this.setOfInt);
    }
}
