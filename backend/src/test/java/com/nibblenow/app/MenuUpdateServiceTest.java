/**
 * MenuUpdateServiceTest:
 * 
 * A JUnit test suite to test the MenuUpdateService class,
 * which contains methods related to updating a restaurant's menu
 * 
 * @author Nikolaos Komninos
 */

package com.nibblenow.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nibblenow.app.services.MenuUpdateService;
import com.nibblenow.app.MenuItem;

class MenuUpdateServiceTest {

  private MenuUpdateService service;
  private MenuItem expected, actual;
  private Database db;
  private String restaurant;

  @BeforeEach
  public void setUp() {
    this.service = new MenuUpdateService();
    this.db = mock(Database.class);
    db.RESTAURANTS.put("Pizzaria", new Restaurant("Pizzaria", "A pizzaria"));
    this.restaurant = "Pizzaria";
    this.expected = null;
    this.actual = null;
  }

  @AfterEach
  public void tearDown() {
    this.service = null;
    this.expected = null;
    this.actual = null;
    this.restaurant = null;
    this.db = null;
  }

  /* TESTS FOR addItem() */

  /**
   * addItemTestStub:
   * 
   * A test stub for the addItem method
   */
  @Test
  public void addItemTestStub() {
    actual = service.addItem("", "", "");
    assertEquals(null, actual);
  }

  /**
   * addItemValid:
   * 
   * Tests adding a completely valid item
   */
  @Test
  public void addItemValid() {
    expected = new MenuItem("Regular Slice", "The classic slice of pizza you know and love");
    actual = service.addItem(restaurant, "Regular Slice", "The classic slice of pizza you know and love");
    assertTrue(expected.equals(actual));
  }

  /**
   * addItemNameMinLength:
   * 
   * Tests adding an item with a name with the minimum allowed characters
   */
  @Test
  public void addItemNameMinLength() {
    expected = new MenuItem("Pizza", "Example");
    actual = service.addItem(restaurant, "Pizza", "Example");
    assertTrue(expected.equals(actual));
  }

  /**
   * addItemNameMaxLength:
   * 
   * Tests adding an item with a name with the maximum allowed characters
   */
  @Test
  public void addItemNameMaxLength() {
    expected = new MenuItem("Spaghetti with Meat Sauce", "Example");
    actual = service.addItem(restaurant, "Spaghetti with Meat Sauce", "Example");
    assertTrue(expected.equals(actual));
  }

  /**
   * addItemNameEmpty:
   * 
   * Tests adding an item with an empty name
   */
  @Test
  public void addItemNameEmpty() {
    actual = service.addItem(restaurant, "", "Example");
    assertEquals(null, actual);
  }

  /**
   * addItemNameBelowMinLength:
   * 
   * Tests adding an item with a name below the minimum
   * allowed characters
   */
  @Test
  public void addItemNameBelowMinLength() {
    actual = service.addItem(restaurant, "Food", "Example");
    assertEquals(null, actual);
  }

  /**
   * addItemNameAboveMaxLength:
   * 
   * Tests adding an item with a name above the
   * maximum allowed characters
   * 
   */
  @Test
  public void addItemNameAboveMaxLength() {
    actual = service.addItem(restaurant, "Spaghetti with Sun-Dried Tomato Alfredo Sauce", "Example");
    assertEquals(null, actual);
  }

  /**
   * addItemDescriptionMinLength:
   * 
   * Tests adding an item with a description
   * with the minimum allowed characters
   */
  @Test
  public void addItemDescriptionMinLength() {
    expected = new MenuItem("Hamburger", "");
    actual = service.addItem(restaurant, "Hamburger", "");
    assertTrue(expected.equals(actual));
  }

  /**
   * addItemDescriptionMaxLength:
   * 
   * Tests adding an item with a description
   * with the maximum allowed characters
   */
  @Test
  public void addItemDescriptionMaxLength() {
    expected = new MenuItem("Hamburger", "Lorem ipsum dolor sit amet consectetur adipiscing.");
    actual = service.addItem(restaurant, "Hamburger", "Lorem ipsum dolor sit amet consectetur adipiscing.");
    assertTrue(expected.equals(actual));
  }

  /**
   * addItemDescriptionAboveMaxLength:
   * 
   * Tests adding an item with a description
   * above the maximum allowed characters
   */
  @Test
  public void addItemDescriptionAboveMaxLength() {
    actual = service.addItem(restaurant, "Hamburger",
        "Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus” into description field");
    assertEquals(null, actual);
  }

  /* TESTS FOR removeItem() */

  /**
   * remoteItemTestStub:
   * 
   * Test stub for removeItem()
   */
  @Test
  public void remoteItemTestStub() {
    assertFalse(service.removeItem("", ""));
  }

  /**
   * removeItemValid:
   * 
   * Tests removal of a completely valid item
   */
  @Test
  public void removeItemValid() {
    service.addItem(restaurant, "Pizza", "A slice of pizza");
    assertTrue(service.removeItem(restaurant, "Pizza"));
  }

  /**
   * removeItemNotExist:
   * 
   * Tests removal of an item that does not exist
   * on the menu
   */
  @Test
  public void removeItemNotExist() {
    assertFalse(service.removeItem(restaurant, "Hamburger"));
  }

  /**
   * removeItemEmptyName:
   *
   * Tests trying to remove an item with
   * an empty name 
   */
  @Test
  public void removeItemEmptyName() {
    assertFalse(service.removeItem(restaurant, ""));
  }

  /* TESTS FOR editItem() */

  /**
   * editItemTestStub:
   * 
   * Test stub for editItem()
   */
  @Test
  public void editItemTestStub() {
    actual = service.editItem("", "", "", "");
    assertEquals(null, actual);
  }

  /**
   * editItemValid:
   * 
   * Tests a completely valid item edit
   */
  @Test
  public void editItemValid() {
    service.addItem(restaurant, "Hamburger", "A hamburger");
    expected = new MenuItem("Original Hamburger", "A New York hamburger");
    actual = service.editItem(restaurant, "Hamburger", "Original Hamburger", "A New York hamburger");
    assertTrue(expected.equals(actual));
  }

  /**
   * editItemNameMinLength:
   * 
   * Tests trying to edit an item's name to have a name
   * with the minimum amount of allowed characters
   */
  @Test
  public void editItemNameMinLength() {
    service.addItem(restaurant, "Regular Pizza", "A slice of pizza");
    expected = new MenuItem("Pizza", "A slice of pizza");
    actual = service.editItem(restaurant, "Regular Pizza", "Pizza", "A slice of pizza");
    assertTrue(expected.equals(actual));
  }

  /**
   * editItemNameMaxLength:
   * 
   * Tests trying to edit an item's name to have a name
   * with the maximum amount of allowed characters
   */
  @Test
  public void editItemNameMaxLength() {
    service.addItem(restaurant, "Spaghetti", "Example");
    expected = new MenuItem("Spaghetti with Meat Sauce", "Example");
    actual = service.editItem(restaurant, "Spaghetti", "Spaghetti with Meat Sauce", "Example");
    assertTrue(expected.equals(actual));
  }

  /**
   * editItemNameEmpty:
   * 
   * Tests trying to edit an item's name to have
   * an empty name
   */
  @Test
  public void editItemNameEmpty() {
    service.addItem(restaurant, "Spaghetti", "Example");
    actual = service.editItem(restaurant, "Spaghetti", "", "Example");
    assertEquals(null, actual);
  }

  /**
   * editItemNameBelowMinLength:
   * 
   * Tests trying to edit an item's name to be below
   * the minimum amount of allowed characters
   */
  @Test
  public void editItemNameBelowMinLength() {
    service.addItem(restaurant, "Spaghetti", "Example");
    actual = service.editItem(restaurant, "Spaghetti", "Spag", "Example");
    assertEquals(null, actual);
  }

  /**
   * editItemNameAboveMaxLength:
   * 
   * Tests trying to edit an item's name to be above
   * the maximum amount of allowed characters
   */
  @Test
  public void editItemNameAboveMaxLength() {
    service.addItem(restaurant, "Spaghetti", "Example");
    actual = service.editItem(restaurant, "Spaghetti", "Spaghetti with Sun-Dried Tomato Alfredo Sauce", "Example");
    assertEquals(null, actual);
  }

  /**
   * editItemDescriptionMinLength:
   * 
   * Tests trying to edit an item's description to
   * be the minimum amount of allowed characters
   */
  @Test
  public void editItemDescriptionMinLength() {
    service.addItem(restaurant, "Spaghetti", "Example");
    expected = new MenuItem("Spaghetti", "");
    actual = service.editItem(restaurant, "Spaghetti", "Spaghetti", "");
    assertTrue(expected.equals(actual));
  }

  /**
   * editItemDescriptionMaxLength:
   * 
   * Tests trying to edit an item's description to
   * be the maximum amount of allowed characters
   */
  @Test
  public void editItemDescriptionMaxLength() {
    service.addItem(restaurant, "Spaghetti", "Example");
    expected = new MenuItem("Spaghetti", "Lorem ipsum dolor sit amet consectetur adipiscing.");
    actual = service.editItem(restaurant, "Spaghetti", "Spaghetti",
        "Lorem ipsum dolor sit amet consectetur adipiscing.");
    assertTrue(expected.equals(actual));
  }

  /**
   * editItemDescriptionAboveMaxLength:
   * 
   * Tests editing an item's description to
   * be above the maximum amount of allowed characters
   */
  @Test
  public void editItemDescriptionAboveMaxLength() {
    service.addItem(restaurant, "Hamburger", "Example");
    actual = service.editItem(restaurant, "Hamburger", "Hamburger",
        "Lorem ipsum dolor sit amet consectetur adipiscing elit quisque faucibus” into description field");
    assertEquals(null, actual);
  }
}