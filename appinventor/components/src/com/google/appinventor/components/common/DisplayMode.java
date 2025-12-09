// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2025 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines a DisplayMode type used by the Form component to specify how the screen
 * layout interacts with system UI elements (status bar, navigation bar, and cutouts).
 */
public enum DisplayMode implements OptionList<String> {
  /**
   * Safe area mode - Layout respects all system UI (status bar, nav bar, cutouts).
   * Components are positioned within the safe area that doesn't intersect with system UI.
   */
  Safe("safe"),
  
  /**
   * Edge-to-edge mode - Layout extends under all system UI elements.
   * The app takes full control of the screen, hiding system bars and extending under cutouts.
   */
  EdgeToEdge("edge-to-edge"),
  
  /**
   * Hybrid mode - Background extends edge-to-edge while components stay in safe area.
   * Allows background images to extend behind system UI while keeping interactive
   * components within the safe area.
   */
  BackgroundEdgeToEdge("background-edge-to-edge");

  private final String value;

  DisplayMode(String val) {
    this.value = val;
  }

  public String toUnderlyingValue() {
    return value;
  }

  private static final Map<String, DisplayMode> lookup;

  static {
    Map<String, DisplayMode> tempMap = new HashMap<>();
    for (DisplayMode mode : DisplayMode.values()) {
      tempMap.put(mode.toUnderlyingValue().toLowerCase(), mode);
    }
    lookup = java.util.Collections.unmodifiableMap(tempMap);
  }

  public static DisplayMode fromUnderlyingValue(String mode) {
    if (mode == null) {
      return null;
    }
    return lookup.get(mode.toLowerCase());
  }
}
