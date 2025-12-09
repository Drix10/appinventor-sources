// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2025 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for DisplayMode enum.
 * Tests null safety, case-insensitive lookup, and value conversion.
 *
 * @author MIT App Inventor
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35, manifest = Config.NONE)
public class DisplayModeTest {

  @Test
  public void testToUnderlyingValue() {
    assertEquals("safe", DisplayMode.Safe.toUnderlyingValue());
    assertEquals("edge-to-edge", DisplayMode.EdgeToEdge.toUnderlyingValue());
    assertEquals("background-edge-to-edge", DisplayMode.BackgroundEdgeToEdge.toUnderlyingValue());
  }

  @Test
  public void testFromUnderlyingValueExactMatch() {
    assertEquals(DisplayMode.Safe, DisplayMode.fromUnderlyingValue("safe"));
    assertEquals(DisplayMode.EdgeToEdge, DisplayMode.fromUnderlyingValue("edge-to-edge"));
    assertEquals(DisplayMode.BackgroundEdgeToEdge, 
        DisplayMode.fromUnderlyingValue("background-edge-to-edge"));
  }

  @Test
  public void testFromUnderlyingValueCaseInsensitive() {
    // Test uppercase
    assertEquals(DisplayMode.Safe, DisplayMode.fromUnderlyingValue("SAFE"));
    assertEquals(DisplayMode.EdgeToEdge, DisplayMode.fromUnderlyingValue("EDGE-TO-EDGE"));
    assertEquals(DisplayMode.BackgroundEdgeToEdge, 
        DisplayMode.fromUnderlyingValue("BACKGROUND-EDGE-TO-EDGE"));
    
    // Test mixed case
    assertEquals(DisplayMode.Safe, DisplayMode.fromUnderlyingValue("Safe"));
    assertEquals(DisplayMode.EdgeToEdge, DisplayMode.fromUnderlyingValue("Edge-To-Edge"));
    assertEquals(DisplayMode.BackgroundEdgeToEdge, 
        DisplayMode.fromUnderlyingValue("Background-Edge-To-Edge"));
    
    // Test random case
    assertEquals(DisplayMode.Safe, DisplayMode.fromUnderlyingValue("sAfE"));
    assertEquals(DisplayMode.EdgeToEdge, DisplayMode.fromUnderlyingValue("eDgE-tO-eDgE"));
  }

  @Test
  public void testFromUnderlyingValueNull() {
    assertNull(DisplayMode.fromUnderlyingValue(null));
  }

  @Test
  public void testFromUnderlyingValueInvalid() {
    assertNull(DisplayMode.fromUnderlyingValue("invalid"));
    assertNull(DisplayMode.fromUnderlyingValue(""));
    assertNull(DisplayMode.fromUnderlyingValue("safe-mode"));
    assertNull(DisplayMode.fromUnderlyingValue("edge"));
    assertNull(DisplayMode.fromUnderlyingValue("background"));
  }

  @Test
  public void testFromUnderlyingValueWhitespace() {
    // Whitespace should not match
    assertNull(DisplayMode.fromUnderlyingValue(" safe"));
    assertNull(DisplayMode.fromUnderlyingValue("safe "));
    assertNull(DisplayMode.fromUnderlyingValue(" safe "));
    assertNull(DisplayMode.fromUnderlyingValue("edge-to-edge "));
  }

  @Test
  public void testAllEnumValuesHaveUnderlyingValue() {
    // Ensure all enum values have non-null underlying values
    for (DisplayMode mode : DisplayMode.values()) {
      assertNotNull("DisplayMode." + mode.name() + " has null underlying value", 
          mode.toUnderlyingValue());
    }
  }

  @Test
  public void testAllEnumValuesCanBeRetrieved() {
    // Ensure all enum values can be retrieved via fromUnderlyingValue
    for (DisplayMode mode : DisplayMode.values()) {
      String underlyingValue = mode.toUnderlyingValue();
      assertEquals("Failed to retrieve " + mode.name() + " via fromUnderlyingValue", 
          mode, DisplayMode.fromUnderlyingValue(underlyingValue));
    }
  }

  @Test
  public void testRoundTripConversion() {
    // Test that converting to underlying value and back returns the same enum
    for (DisplayMode mode : DisplayMode.values()) {
      String underlyingValue = mode.toUnderlyingValue();
      DisplayMode retrieved = DisplayMode.fromUnderlyingValue(underlyingValue);
      assertEquals("Round-trip conversion failed for " + mode.name(), mode, retrieved);
    }
  }

  @Test
  public void testEnumCount() {
    // Ensure we have exactly 3 display modes
    assertEquals("Expected exactly 3 DisplayMode values", 3, DisplayMode.values().length);
  }

  @Test
  public void testEnumOrder() {
    // Verify the order of enum values (Safe should be first/default)
    DisplayMode[] modes = DisplayMode.values();
    assertEquals("Safe should be the first enum value", DisplayMode.Safe, modes[0]);
    assertEquals("EdgeToEdge should be the second enum value", DisplayMode.EdgeToEdge, modes[1]);
    assertEquals("BackgroundEdgeToEdge should be the third enum value", 
        DisplayMode.BackgroundEdgeToEdge, modes[2]);
  }

  @Test
  public void testUnderlyingValuesAreUnique() {
    // Ensure all underlying values are unique
    String safe = DisplayMode.Safe.toUnderlyingValue();
    String edgeToEdge = DisplayMode.EdgeToEdge.toUnderlyingValue();
    String backgroundEdgeToEdge = DisplayMode.BackgroundEdgeToEdge.toUnderlyingValue();
    
    // No two values should be equal
    assertNotNull(safe);
    assertNotNull(edgeToEdge);
    assertNotNull(backgroundEdgeToEdge);
    
    // Check uniqueness
    if (safe.equals(edgeToEdge) || safe.equals(backgroundEdgeToEdge) 
        || edgeToEdge.equals(backgroundEdgeToEdge)) {
      throw new AssertionError("DisplayMode underlying values are not unique");
    }
  }

  @Test
  public void testThreadSafety() throws InterruptedException {
    // Test that fromUnderlyingValue is thread-safe
    final int threadCount = 10;
    final int iterationsPerThread = 1000;
    Thread[] threads = new Thread[threadCount];
    final boolean[] success = new boolean[threadCount];
    
    for (int i = 0; i < threadCount; i++) {
      final int threadIndex = i;
      threads[i] = new Thread(() -> {
        try {
          for (int j = 0; j < iterationsPerThread; j++) {
            // Test all conversions
            assertEquals(DisplayMode.Safe, DisplayMode.fromUnderlyingValue("safe"));
            assertEquals(DisplayMode.EdgeToEdge, DisplayMode.fromUnderlyingValue("edge-to-edge"));
            assertEquals(DisplayMode.BackgroundEdgeToEdge, 
                DisplayMode.fromUnderlyingValue("background-edge-to-edge"));
            assertNull(DisplayMode.fromUnderlyingValue(null));
            assertNull(DisplayMode.fromUnderlyingValue("invalid"));
          }
          success[threadIndex] = true;
        } catch (Exception e) {
          success[threadIndex] = false;
        }
      });
      threads[i].start();
    }
    
    // Wait for all threads to complete
    for (Thread thread : threads) {
      thread.join();
    }
    
    // Verify all threads succeeded
    for (int i = 0; i < threadCount; i++) {
      if (!success[i]) {
        throw new AssertionError("Thread " + i + " failed");
      }
    }
  }
}
