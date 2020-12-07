import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class TestLower {
	@Test
	public void testLZero() {
		assertEquals(-1,new Judge().judge(-2));
	}
}
