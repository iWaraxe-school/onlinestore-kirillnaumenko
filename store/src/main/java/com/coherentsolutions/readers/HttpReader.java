package com.coherentsolutions.readers;

import com.coherentsolutions.categories.Category;
import com.coherentsolutions.interfaces.ISourceReader;

import java.util.Collections;
import java.util.List;

public class HttpReader implements ISourceReader {
    @Override
    public List<Category> readCategories() {

        // Read some categories from Http
        return Collections.EMPTY_LIST;
    }
}
