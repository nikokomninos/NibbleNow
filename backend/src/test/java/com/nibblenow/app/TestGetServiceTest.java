package com.nibblenow.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.TestGetService;

class TestGetServiceTest {

  @Test
  public void testMethod() {
    TestGetService s = new TestGetService();
    String result = s.testGet();
    assertEquals("Hello!", result);
  }
}