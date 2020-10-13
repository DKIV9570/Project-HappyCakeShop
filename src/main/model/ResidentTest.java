package model;

import model.Material;
import model.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Resident class
 */
public class ResidentTest {
    private Resident testResident;
    private Map<String, List<Material>> market = new LinkedHashMap<>();

    @BeforeEach
    void runBefore() {
        testResident = new Resident(market);
    }

    @Test
    void testConstructor() {

    }
}
