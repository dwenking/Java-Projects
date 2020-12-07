import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class TestEqual {
	@Test
	public void testEZero() {
		assertEquals(0,new Judge().judge(0));
	}
}
