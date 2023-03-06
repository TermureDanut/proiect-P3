package arbore;

import Produse.Produs;

import static org.junit.jupiter.api.Assertions.*;

class bstClassTest {
    bstClass bst = new bstClass();
    Produs p1 = new Produs(1, "1", 12, "1", 100, "verde", "i");
    Produs p2 = new Produs(2, "2", 1, "2", 100, "verde", "i");
    Produs p3 = new Produs(3, "3", 40, "3", 100, "verde", "i");
    Produs p4 = new Produs(4, "4", 8, "4", 100, "verde", "i");

    @org.junit.jupiter.api.Test
    void testMinValue(){
        bst.insert(p1);
        bst.insert(p2);
        bst.insert(p3);
        bst.insert(p4);

        assertEquals(1, bst.minValue(bst.root));
    }

    @org.junit.jupiter.api.Test
    void testMaxValue(){
        bst.insert(p1);
        bst.insert(p2);
        bst.insert(p3);
        bst.insert(p4);

        assertEquals(40, bst.maxValue(bst.root));
    }

}