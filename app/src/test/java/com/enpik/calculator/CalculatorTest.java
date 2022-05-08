package com.enpik.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.enpik.calculator.utils.Calculator;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorTest {

    @Test
    public void checkWhenExpressionStartingWithMinusIsPassed() {
        Calculator calculator = new Calculator();
        assertFalse(calculator.checkIfExpressionIsValid("-1+2/5"));
    }

    @Test
    public void checkWhenExpressionHavingSuccessiveOperatorsIsPassed() {
        Calculator calculator = new Calculator();
        assertFalse(calculator.checkIfExpressionIsValid("1-+2"));
    }

    @Test
    public void checkWhenExpressionHavingLengthTwoIsPassed() {
        Calculator calculator = new Calculator();
        assertFalse(calculator.checkIfExpressionIsValid("1-"));
    }

    @Test
    public void checkWhenExpressionHavingTwoOperatorAndOneOperandIsPassed() {
        Calculator calculator = new Calculator();
        assertFalse(calculator.checkIfExpressionIsValid("+1-"));
    }

    @Test
    public void checkWhenValidExpressionPassed() {
        Calculator calculator = new Calculator();
        assertTrue(calculator.checkIfExpressionIsValid("18/3*2-2"));
    }

}