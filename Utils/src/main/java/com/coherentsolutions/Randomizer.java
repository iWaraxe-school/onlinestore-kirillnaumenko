package com.coherentsolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
    public static <E> List<E> selectRandomElements(List<E> list, int amount)
    {
        // Avoid a deadlock
        if (amount >= list.size())
        {
            return list;
        }

        List<E> selected = new ArrayList<>();
        Random random = new Random();
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
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);

        return list.get(randomIndex);

    }
}
