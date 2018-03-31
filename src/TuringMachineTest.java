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
        assertTrue(TM_palindrome.Run("010", false));
    }

    @Test
    void spec_example2() {
        assertTrue(TM_palindrome.Run("2011102", false));
    }

    @Test
    void spec_example3() {
        assertTrue(TM_palindrome.Run("111", false));
    }

    @Test
    void spec_example1_fail() {
        assertFalse(TM_palindrome.Run("001", false));
    }

    @Test
    void spec_example2_fail() {
        assertFalse(TM_palindrome.Run("20", false));
    }

    @Test
    void spec_example3_fail() {
        assertFalse(TM_palindrome.Run("20110", false));
    }

    // --- additional test cases ---

    @Test
    void unevenPalindromeSame() {
        assertTrue(TM_palindrome.Run("22222", false));
    }

    @Test
    void evenPalindromeSame() {
        assertTrue(TM_palindrome.Run("0000", false));
    }

    @Test
    void evenPalindromedifferent() {
        assertTrue(TM_palindrome.Run("0110", false));
    }

    @Test
    void unevenPalindromedifferent() {
        assertTrue(TM_palindrome.Run("01210", false));
    }

    @Test
    void morecomplicated() {
        assertTrue(TM_palindrome.Run("101210012101", false));
    }
}

class AdditionTests {
    private TuringMachine TM_addition;

    @BeforeEach
    void TuringMachine() {
        TM_addition = new TuringMachineFactory().BuildTuringMachine("addition.txt");
    }

    @Test
    void spec_example1() {
        assertTrue(TM_addition.Run("0#0#0", false));
    }
    @Test
    void spec_example2() {
        assertTrue(TM_addition.Run("01#1#11", false));
    }
    @Test
    void spec_example3() {
        assertTrue(TM_addition.Run("00#111#111", false));
    }

    @Test
    void spec_example1_false() {
        assertTrue(TM_addition.Run("0#0", false));
    }
    @Test
    void spec_example2_false() {
        assertTrue(TM_addition.Run("0#0#1", false));
    }
    @Test
    void spec_example3_false() {
        assertTrue(TM_addition.Run("000#111#11", false));
    }
}