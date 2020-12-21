package pl.comp.view;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][] {
                {"author1", "Markus Deriu"},
                {"author2", "Hubert Krzemiński"},
        };
    }
}