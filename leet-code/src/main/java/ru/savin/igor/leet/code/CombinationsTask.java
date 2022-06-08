package ru.savin.igor.leet.code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/combinations/
 *
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class CombinationsTask {

  public static void main(String[] args) {
    // TEST
    List<List<Integer>> result = combine(7, 2);

    // PRINT RESULT
    for (int i = 0; i < result.size(); i++) {
      List<Integer> subResult = result.get(i);
      System.out.print("[");
      for (int j = 0; j < subResult.size(); j++) {
        System.out.print(subResult.get(j));
        System.out.print(", ");
      }
      System.out.println("]");
    }
  }

  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();

    // Обработка граничного результата
    if (n == k) {
      List<Integer> subResult = new ArrayList<>();
      for (int i = 1; i <= k; i++) {
        subResult.add(i);
      }
      result.add(subResult);
      return result;
    }

    result = combine(n-1, k);
    Set<List<Integer>> tempNewSubResults = new HashSet<>();

    for (List<Integer> subResult : result) {
      Deque<Integer> deque = new ArrayDeque<>(subResult);
      int firstElement = deque.peekFirst();

      do {
        List<Integer> newSubResult = new ArrayList<>();
        for (int i = 0; i < k - 1; i++) {
          int tempElement = deque.poll();
          newSubResult.add(tempElement);
          deque.addLast(tempElement);
        }
        newSubResult.add(n);
        Collections.sort(newSubResult);
        tempNewSubResults.add(newSubResult);
      } while (deque.peekFirst() != firstElement);

    }

    // Добавить новые подрезультаты в общий рузультат
    result.addAll(tempNewSubResults);
    return result;
  }
}