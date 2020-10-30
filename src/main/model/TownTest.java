package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Town class
 */
public class TownTest {
    private Town testTown;

    @BeforeEach
    public void runBefore() {
        testTown = new Town();
    }

    @Test
    public void testConstructor() {
        assertEquals(testTown.getMarket().size(),3);
        assertEquals(testTown.getResidents().size(),500);
    }

    @Test
    public void testGetResidents() {
        List<Resident> residents = testTown.getResidents();
        assertEquals(residents.size(),500);
    }

    @Test
    public void testGetMarket() {
        Map<String, List<Material>> market = testTown.getMarket();
        assertEquals(market.size(),3);
        assertEquals(market.get("cake base").size(),3);
        assertEquals(market.get("cream").size(),3);
        assertEquals(market.get("topping").size(),3);
    }

}
