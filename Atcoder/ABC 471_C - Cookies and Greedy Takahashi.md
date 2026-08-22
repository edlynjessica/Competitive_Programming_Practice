# [C - Cookies and Greedy Takahashi](https://atcoder.jp/contests/abc471/tasks/abc471_c)

**AtCoder ABC 471 C — 300 points**

![Language](https://img.shields.io/badge/Language-Java-blueviolet?style=for-the-badge)
![Greedy](https://img.shields.io/badge/Topic-Greedy+%20Sorting+%20Two%20Pointers-blue?style=for-the-badge)

## Problem Statement

There are cookies at `N` positions on a number line. The coordinate of the `i`-th cookie is `Ai`.

Takahashi is initially at coordinate `0` and repeats the following action until he has picked up all `N` cookies:

* Move to the coordinate of the nearest cookie from his current position.
* If there are multiple nearest cookies, choose the one with the **smallest coordinate**.
* Pick up that cookie.

Find the total distance Takahashi travels.

## Constraints

* `1 ≤ N ≤ 3 × 10^5`
* `−10^9 ≤ Ai ≤ 10^9`
* `Ai ≠ 0`
* All `Ai` are distinct.
* All input values are integers.

## Input

```text
N
A1 A2 ... AN
```

## Output

Print the total distance Takahashi travels.

## Example 1

### Input

```text
4
-1 -4 2 -11
```

### Output

```text
23
```

### Explanation

Takahashi moves as follows:

```text
0 → -1    distance = 1
-1 → -4   distance = 3
-4 → 2    distance = 6
2 → -11   distance = 13
```

Total:

```text
1 + 3 + 6 + 13 = 23
```

At the second step, the distances to `-4` and `2` are both `3`.

Since the tie-breaking rule chooses the **smaller coordinate**, Takahashi chooses `-4`.

## Example 2

### Input

```text
10
1 2 3 4 5 -1 -2 -3 -4 -6
```

### Output

```text
17
```

---

# Approach

The important observation is that after sorting the cookie coordinates, the nearest unpicked cookie will always be one of the two cookies immediately surrounding Takahashi's current position.

So we can use **two pointers**:

* `left` → largest unpicked cookie coordinate smaller than the current position.
* `right` → smallest unpicked cookie coordinate greater than the current position.

Initially, Takahashi is at `0`.

After sorting:

```text
negative cookies | positive cookies
                 ↑
               right
```

We set:

```text
right = index of the first positive cookie
left = right - 1
```

At every step:

1. Calculate the distance to `cookies[left]`.
2. Calculate the distance to `cookies[right]`.
3. Choose the smaller distance.
4. If the distances are equal, choose `left`, because its coordinate is smaller.
5. Add the chosen distance to the answer.
6. Move that pointer.

If one side is exhausted, we simply continue taking cookies from the other side.

---

# Why Only Two Pointers Are Needed

Suppose Takahashi is currently at coordinate `x`.

Among all unpicked cookies:

* Any cookie further to the left than `cookies[left]` is farther away than `cookies[left]`.
* Any cookie further to the right than `cookies[right]` is farther away than `cookies[right]`.

Therefore, only these two candidates can possibly be the nearest cookie:

```text
cookies[left]    x    cookies[right]
      ← closest      closest →
```

So we don't need to repeatedly search through all cookies.

---

# Complexity

```text
O(N log N)
```

After sorting, each cookie is processed exactly once using the two pointers:

Therefore:

### Time Complexity

```text
O(N log N)
```

### Space Complexity

```text
O(N)
```

for storing the cookie coordinates.

---

# Java Solution

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cookies = new int[n]
        for (int i = 0; i < n; i++) {
            cookies[i] = sc.nextInt();
        }

        // Sort coordinates in ascending order
        Arrays.sort(cookies);

        // Find the first positive cookie
        int right = 0;
        while (right < n && cookies[right] < 0) {
            right++;
        }
        // Largest negative cookie
        int left = right - 1;

        // Takahashi starts at coordinate 0
        long start = 0;
        long sum = 0;

        while (left >= 0 || right < n) {

            // Only right pointer is available
            if (left < 0) {
                long rightd = Math.abs(cookies[right] - start);
                start = cookies[right];
                sum += rightd;
                right++;
            }

            // Only left pointer is available
            else if (right >= n) {
                long leftd = Math.abs(cookies[left] - start);
                start = cookies[left];
                sum += leftd;
                left--;
            }

            // Both sides have unpicked cookies
            else {
                long leftd = Math.abs(cookies[left] - start);
                long rightd = Math.abs(cookies[right] - start);
                // If equal, choose the smaller coordinate (left)
                if (leftd <= rightd) {
                    start = cookies[left];
                    sum += leftd;
                    left--;
                } else {
                    start = cookies[right];
                    sum += rightd;
                    right++;
                }
            }
        }
        System.out.println(sum);
    }
}
```

## Key Takeaway

This is basically a **sorted array + two pointers + greedy choice** problem.

The pattern to remember:

```text
Sort
  ↓
Split around current position
  ↓
Keep nearest unpicked element on each side
  ↓
Compare left distance and right distance
  ↓
Choose the smaller one
  ↓
Tie → choose smaller coordinate
```

The important greedy insight is:

> **After sorting, the nearest remaining cookie must be one of the two boundary cookies around the current position.**
