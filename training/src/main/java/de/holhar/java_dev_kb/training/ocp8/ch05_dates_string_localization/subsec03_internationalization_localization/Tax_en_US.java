package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec03_internationalization_localization;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * With java resource bundles you can use values that are not a String.
 */
public class Tax_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {{ "tax", new UsTaxCode() }};
    }

    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(
                "de.holhar.ocppreparation.ch05_dates_string_localization.subsec03_internationalization_localization.Tax",
                Locale.US
        );
        println(rb.getObject("tax"));
    }
}

class UsTaxCode {

    private int taxValue;

    public UsTaxCode() {
        taxValue = 23;
    }

    @Override
    public String toString() {
        return "UsTaxCode[taxValue=" + taxValue + "]";
    }
}
