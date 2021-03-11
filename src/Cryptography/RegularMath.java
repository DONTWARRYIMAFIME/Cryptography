package Cryptography;

import java.util.Objects;

public class RegularMath {
    public static int linearSearch(Object[] arr, Object key)
    {
        int size = arr.length;
        for(int i = 0; i < size; i++)
        {
            if (Objects.equals(arr[i], key))
                return i;
        }

        return -1;
    }
}
