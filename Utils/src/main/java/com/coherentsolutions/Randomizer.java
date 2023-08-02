package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private static final  Random random = new Random(System.currentTimeMillis());

    public static <E> List<E> selectRandomElements(List<E> list, int amount)
    {
        // Avoid a deadlock
        if (amount >= list.size())
        {
            return list;
        }

        List<E> selected = new ArrayList<>();
        int listSize = list.size();

        // Get a random item until we got the requested amount
        while (selected.size() < amount)
        {
            int randomIndex = random.nextInt(listSize);
            E element = list.get(randomIndex);

            if (!selected.contains(element))
            {
                selected.add(element);
            }
        }

        return selected;
    }

    public static <E> E selectRandomElement(List<E> list)
    {
        int listSize = list.size();
        int randomIndex = random.nextInt(listSize);

        return list.get(randomIndex);

    }
}
