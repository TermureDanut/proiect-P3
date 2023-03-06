package LoginRelated;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Client c = new Client(1, "1", "1", "1", "1", "1", "1");
    @org.junit.jupiter.api.Test
    void testToString() {
        String str = "Nume : 1 , prenume : 1 , email : 1 , parola : 1";
        assertEquals(c.toString(), str);
    }

    @org.junit.jupiter.api.Test
    void testGetId(){
        assertEquals(1, c.getId());
    }

    @org.junit.jupiter.api.Test
    void testGetEmail(){
        assertEquals("1", c.getEmail());
    }

    @org.junit.jupiter.api.Test
    void testGetParola(){
        assertEquals("1", c.getParola());
    }

    @org.junit.jupiter.api.Test
    void testGetAdresa(){
        assertEquals("1", c.getAdresa());
    }

    @org.junit.jupiter.api.Test
    void testSetteri(){
        c.setP1(1);
        c.setP2(1);
        c.setP3(1);
        c.setP4(1);
        c.setP5(1);
        assertEquals(1, c.getP1());
        assertEquals(1, c.getP2());
        assertEquals(1, c.getP3());
        assertEquals(1, c.getP4());
        assertEquals(1, c.getP5());
    }
}