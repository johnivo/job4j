package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 *@author John Ivanov (johnivo@mail.ru)
 *@version $ld$
 *@since 01
 */
public class CalculateTest {
	
	/**
	*Test echo.
	*/
	@Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "John Ivanov";
		String expect = "Echo, echo, echo : John Ivanov";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}