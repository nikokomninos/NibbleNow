package com.nibblenow.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.TestPostService;

class TestPostServiceTest {

  @Test
  public void testMethod() {
    TestPostService s = new TestPostService();
    String result = s.testPost("Niko");
    assertEquals("Hello, Niko!", result);
  }
}