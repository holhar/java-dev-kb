package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec03_internationalization_localization;

import java.util.ListResourceBundle;

/**
 * A Java class resource bundle
 */
public class Zoo_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"hello", "Hello"},
                {"open", "The zoo is open"}
        };
    }
}
