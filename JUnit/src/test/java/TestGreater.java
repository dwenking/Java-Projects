import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class TestGreater {
	@Test
	public void testGZero() {
		assertEquals(1,new Judge().judge(1));
	}
}
