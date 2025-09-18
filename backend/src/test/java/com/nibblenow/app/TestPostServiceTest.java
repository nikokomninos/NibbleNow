package com.nibblenow.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.TestPostService;

class TestPostServiceTest {

  private TestPostService s;
  private String result;

  @BeforeEach
  public void setUp() {
    this.s = new TestPostService();
  }

  @AfterEach
  public void tearDown() {
    this.s = null;
    this.result = null;
  }

  @Test
  public void testMethod() {
    this.result = this.s.testPost("Niko");
    assertEquals("Hello, Niko!", this.result);
  }
}