// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2025 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.youngandroid.properties;

import static com.google.appinventor.client.Ode.MESSAGES;
import com.google.appinventor.client.widgets.properties.ChoicePropertyEditor;

/**
 * Property editor for display mode - controls how the screen layout interacts
 * with system UI elements like status bar, navigation bar, and screen cutouts.
 *
 * @author MIT App Inventor
 */
public class YoungAndroidDisplayModeChoicePropertyEditor extends ChoicePropertyEditor {

  // Display mode choices
  private static final Choice[] displayModeChoices = new Choice[] {
    new Choice(MESSAGES.safeDisplayMode(), "safe"),
    new Choice(MESSAGES.edgeToEdgeDisplayMode(), "edge-to-edge"),
    new Choice(MESSAGES.backgroundEdgeToEdgeDisplayMode(), "background-edge-to-edge"),
  };

  public YoungAndroidDisplayModeChoicePropertyEditor() {
    super(displayModeChoices);
  }
}
