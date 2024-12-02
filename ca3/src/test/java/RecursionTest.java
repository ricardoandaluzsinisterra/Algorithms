import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import utils.Recursion;

public class RecursionTest {

    @Test
    public void testPower() {
        assertEquals(1, Recursion.power(2, 0));
        assertEquals(2, Recursion.power(2, 1));
        assertEquals(8, Recursion.power(2, 3));
        assertEquals(27, Recursion.power(3, 3));
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(Recursion.isPalindrome(202.0));
        assertTrue(Recursion.isPalindrome(200.002));
        assertTrue(Recursion.isPalindrome(20.002));
        assertTrue(Recursion.isPalindrome(-1001.0));
    }

    @Test
    public void testIsSortedDescending() {
        assertTrue(Recursion.isSortedDescending(new String[]{"z", "y", "x"}));
        assertFalse(Recursion.isSortedDescending(new String[]{"a", "b", "c"}));
        assertTrue(Recursion.isSortedDescending(new String[]{"c", "b", "a"}));
        assertFalse(Recursion.isSortedDescending(new String[]{"a", "c", "b"}));
    }

    @Test
    public void testCountSubstringOccurrences() {
        assertEquals(3, Recursion.countSubstringOccurrences("abababa", "aba"));
        assertEquals(0, Recursion.countSubstringOccurrences("hello", "world"));
        assertEquals(3, Recursion.countSubstringOccurrences("aaaa", "aa"));
        assertEquals(0, Recursion.countSubstringOccurrences("abc", "d"));
    }

}