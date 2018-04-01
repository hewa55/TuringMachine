import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PalindromeTest {
    private TuringMachine TM_palindrome;

    @BeforeEach
    void TuringMachine() {
        TM_palindrome = new TuringMachineFactory().BuildTuringMachine("palindrome.txt");
    }

    // --- examples from the specs ---

    @Test
    void spec_example1() {
        assertTrue(TM_palindrome.Run("010", false) > 0);
    }

    @Test
    void spec_example2() {
        assertTrue(TM_palindrome.Run("2011102", false) > 0);
    }

    @Test
    void spec_example3() {
        assertTrue(TM_palindrome.Run("111", false) > 0);
    }

    @Test
    void spec_example1_fail() {
        assertEquals(TM_palindrome.Run("001", false), -1);
    }

    @Test
    void spec_example2_fail() {
        assertEquals(TM_palindrome.Run("20", false), -1);
    }

    @Test
    void spec_example3_fail() {
        assertEquals(TM_palindrome.Run("20110", false), -1);

    }

    // --- additional test cases ---

    @Test
    void unevenPalindromeSame() {
        assertTrue(TM_palindrome.Run("22222", false) > 0);
    }

    @Test
    void evenPalindromeSame() {
        assertTrue(TM_palindrome.Run("0000", false) > 0);
    }

    @Test
    void evenPalindromedifferent() {
        assertTrue(TM_palindrome.Run("0110", false) > 0);
    }

    @Test
    void unevenPalindromedifferent() {
        assertTrue(TM_palindrome.Run("01210", false) > 0);
    }

    @Test
    void morecomplicated() {
        assertTrue(TM_palindrome.Run("101210012101", false) > 0);
    }
}

class AdditionTests {
    private TuringMachine TM_addition;

    @BeforeEach
    void TuringMachine() {
        TM_addition = new TuringMachineFactory().BuildTuringMachine("addition.txt");
    }
    // ----- given in specs
    @Test
    void spec_example1() {
        assertTrue(TM_addition.Run("0#0#0", false) > 0);
    }
    @Test
    void spec_example2() {
        assertTrue(TM_addition.Run("01#1#11", false) > 0);
    }
    @Test
    void spec_example3() {
        assertTrue(TM_addition.Run("00#111#111", false) > 0);
    }
    @Test
    void spec_example1_false() {
        assertEquals(TM_addition.Run("0#0", false), -1);
    }
    @Test
    void spec_example2_false() {
        assertEquals(TM_addition.Run("0#0#1", false), -1);
    }
    @Test
    void spec_example3_false() {
        assertEquals(TM_addition.Run("000#111#11", false), -1);
    }
    // ----- extra tests
    @Test
    void empty() {
        assertTrue(TM_addition.Run("##", false) > 0);
    }
    @Test
    void firstonly() {
        assertTrue(TM_addition.Run("01##01", false) > 0);
    }
    @Test
    void firstonlywithzero() {
        assertTrue(TM_addition.Run("01#0#01", false) > 0);
    }
    @Test
    void secondonly() {
        assertTrue(TM_addition.Run("#01#01", false) > 0);
    }
    @Test
    void secondonlywithzero() {
        assertTrue(TM_addition.Run("0#11#11", false) > 0);
    }
    @Test
    void simplecarry() {
        assertTrue(TM_addition.Run("1#1#01", false) > 0);
    }
    @Test
    void doublecarry() {
        assertTrue(TM_addition.Run("11#11#011", false) > 0);
    }
    @Test
    void complicatedcarry() {
        assertTrue(TM_addition.Run("101#111#0011", false) > 0);
    }
    @Test
    void non_alphabet() {
        assertFalse(TM_addition.Run("1021#111#0011", false) > 0);
    }
}
