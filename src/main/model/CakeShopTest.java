package model;

import model.CakeShop;
import model.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the cake shop class
 */
public class CakeShopTest {
    private CakeShop testShop;
    private Map<String,List<Material>> market = new LinkedHashMap();
    private List<Material> materials = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        testShop = new CakeShop(1000,market);
    }

    @Test
    void testConstructor() {
    }
}
