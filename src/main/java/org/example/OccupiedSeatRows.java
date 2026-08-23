package org.example;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class OccupiedSeatRows {

    public static void main(String[] args) {
	// launch example
        List<Integer> row = List.of(0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0 ,0, 0, 0, 0, 1, 0, 0);
        System.out.println(chooseSeat(row));
    }

/* My solution */
    public static int chooseSeat(List<Integer> row) {
        List<List<Integer>> zeroGroups = new ArrayList<>();
        List<Integer> zeroGroup = new ArrayList<>();

        // find groups with indexes of zero values
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i) == 0) {
                zeroGroup.add(i);
            }
            if (!zeroGroup.isEmpty() && (row.get(i) == 1 | i == row.size() - 1)) {
                zeroGroups.add(new ArrayList<>(zeroGroup));
                zeroGroup.clear();
            }
        }

        return findLargestGroupBestSeat(zeroGroups, row);
    }

    public static int findLargestGroupBestSeat(List<List<Integer>> zeroGroups, List<Integer> row) {
        int largestGroupSize = -1;
        int resultIndex = -1;

        /* if the first index of group is the first from input - we can consider group size as twice larger,
               because it doesn't have closest neighbour at the start */
        List<Integer> firstGroup = zeroGroups.getFirst();
        if (firstGroup.getFirst() == 0) {
            largestGroupSize = firstGroup.size() * 2;
            resultIndex = firstGroup.getFirst();
        }

	/* if the last index of group is the last from input - we can consider group size as twice larger,
               because it doesn't have closest neighbour at the end */
        List<Integer> lastGroup = zeroGroups.getLast();
        if (lastGroup.getLast() == row.size() - 1 && lastGroup.size() >= largestGroupSize) {
            largestGroupSize = lastGroup.size() * 2;
            resultIndex = lastGroup.getLast();
        }

        for (List<Integer> group : zeroGroups) {
            if (group.size() > largestGroupSize) {
                largestGroupSize = group.size();
                resultIndex = group.get(group.size() / 2);
            }
        }

        return resultIndex;
    }


/* Task description:

In a row of seats in a movie theater, each seat is represented as an array of integers:
1 means that the seat is occupied,
0 means that it is free.

You need to seat a person as far away from the workstation section as possible.

Return the index of the array in which to seat the person.

Constraints:
It is guaranteed that the array always contains at least one free and at least one occupied seat.
*/

/* Easier solution from chatgpt */

    public static int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int maxDistance = -1;
        int bestIndex = -1;
        
        for (int i = 0; i < n; i++) {
            if (seats[i] == 0) {
                // Find a distance to closest seat
                int left = i - 1;
                int right = i + 1;
                int distance = Integer.MAX_VALUE;

                while (left >= 0) {
                    if (seats[left] == 1) {
                        distance = Math.min(distance, i - left);
                        break;
                    }
                    left--;
                }

                while (right < n) {
                    if (seats[right] == 1) {
                        distance = Math.min(distance, right - i);
                        break;
                    }
                    right++;
                }

                if (distance > maxDistance) {
                    maxDistance = distance;
                    bestIndex = i;
                }
            }
        }

        return bestIndex;
    }
}