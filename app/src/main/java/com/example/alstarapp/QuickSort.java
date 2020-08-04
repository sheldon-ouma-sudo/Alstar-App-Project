package com.example.alstarapp;

import java.util.Arrays;
import java.util.List;

public class QuickSort {

    public static List<Review> QuickSort(List<Review> arr, int elements) {              //{6,3,3,3,2,2,3,2,4,3,5,2}

        if (elements < 2) {     //Base Case
            return arr;
        }
        int current_position = 0;   //position of pivot element
        Review temp; //a temporary variable to assist in swapping

        for (int i = 1; i < elements; i++) //Partitioning loop
        {
            if (arr.get(i).getItemCount() <= arr.get(0).getItemCount()) {
                current_position++;
                temp = arr.get(i);
                arr.set(i, arr.get(current_position));
                arr.set(current_position, temp);
            }
        }
        temp = arr.get(0);
        arr.set(0, arr.get(current_position));
        arr.set(current_position, temp); //Brings pivot to it's appropriate position
        List<Review> left = QuickSort(arr, current_position); //sorts the elements to the left of pivot
        List<Review> arr2 = arr.subList(current_position + 1, elements);
        List<Review> right = QuickSort(arr2, elements - current_position - 1); //sorts the elements to the right of pivot
        Review[] final_array = new Review[elements]; //final array, to merge everything together
        for (int i = 0; i < current_position; i++) {
            final_array[i] = left.get(i);
        }
        final_array[current_position] = arr.get(current_position);
        for (int i = current_position + 1; i < elements; i++) {
            final_array[i] = right.get(i - current_position - 1);
        }

        return Arrays.asList(final_array);

    }

}







