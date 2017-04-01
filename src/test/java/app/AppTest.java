package app;

import org.junit.Assert;
import org.junit.Test;

import app.App;

public class AppTest {

	@Test
	public void testGetMessage() {
		App app = new App();
		app.setMessage("hello world");
		Assert.assertEquals("should be: hello world", "hello world", app.getMessage());
	}

}
