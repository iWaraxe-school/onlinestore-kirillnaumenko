package com.coherentsolutions.readers;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ISourceReader;

import java.util.Collections;
import java.util.List;

public class DatabaseReader implements ISourceReader {
    @Override
    public List<Category> readCategories() {

        // Read some categories from DB
        return Collections.<Category>emptyList();
    }
}
