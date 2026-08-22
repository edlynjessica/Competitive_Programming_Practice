# [C - On a Diet](https://atcoder.jp/contests/abc472/tasks/abc472_c)

**AtCoder ABC 472 C**

![Language](https://img.shields.io/badge/Language-Java-blueviolet?style=for-the-badge)
![Sliding Window](https://img.shields.io/badge/Topic-Sliding%20Window-blue?style=for-the-badge)

## Problem Statement

Takahashi is staying at his parents' house for an *N*-day homecoming trip.

A snack is prepared every day, and the calorie count of the snack on day *i* is `A[i]`.

For each day, Takahashi decides whether to eat the snack according to the following rule:

> Assuming that he eats the snack on day *i*, if the total calorie count of the snacks eaten in the most recent *M* days is at most *K*, he actually eats the snack. Otherwise, he does not eat it.

For each day, determine whether Takahashi eats the snack.

---

## Constraints

* `1 ≤ M ≤ N ≤ 2 × 10^5`
* `1 ≤ K ≤ 10^15`
* `1 ≤ A[i] ≤ 10^9`
* All input values are integers.

---

## Input

```text
N M K
A1 A2 ... AN
```

---

## Output

Output `N` lines.

* Print `Yes` if Takahashi eats the snack.
* Print `No` otherwise.

---

## Example 1

### Input

```text
5 3 83
48 73 59 90 21
```

### Output

```text
Yes
No
No
No
Yes
```

For each day, assuming that he eats the snack, the total calorie count of the snacks eaten in the most recent three days is:

```text
Day 1 → 48
Day 2 → 48 + 73 = 121
Day 3 → 48 + 59 = 107
Day 4 → 90
Day 5 → 21
```

Therefore:

```text
Yes
No
No
No
Yes
```

---

## Example 2

### Input

```text
7 4 728
187 816 349 609 255 308 175
```

### Output

```text
Yes
No
Yes
No
Yes
No
Yes
```

---

## Example 3

### Input

```text
10 3 1368290936
216519459 804733999 297250023 775422599 287963235 999315644 354987425 974810607 653940822 117157941
```

### Output

```text
Yes
Yes
Yes
No
Yes
Yes
No
No
Yes
Yes
```

# Approach

### 💡 Key Observation

We only care about the snacks that were **actually eaten** in the most recent `M` days.

Maintain:

```text
sum = total calories of eaten snacks
       currently inside the M-day window
```

For each day:

1. Remove the snack that is leaving the window, **only if it was eaten**.
2. Check whether today's snack can be eaten:

```text
sum + A[i] <= K
```

3. If yes:

   * Mark it as eaten.
   * Add its calories to `sum`.
4. Otherwise:

   * Do not eat it.
   * Do not add anything to `sum`.

---

### 🌱 Why do we need `eaten[]`?

A snack that was rejected contributes **zero calories** to the current sum.

Therefore, when a day leaves the `M`-day window, we need to know whether that day's snack was actually eaten.

```java
if(i>=m && eaten[i-m]){
    sum-=a[i-m];
}
```

If it was eaten, remove its calories.

If it wasn't eaten, there is nothing to remove.

---

### ⭐ Important Point

This is a **sliding window**, but it is slightly different from the usual fixed-window problems.

The window is based on **days**, while the sum contains only **eaten snacks**.

So:

```text
Window → last M days
Sum    → eaten snacks within those M days
```

---

# Complexity

### ⏱️ Time

**O(N)**

Each day is processed once, and each eaten snack is added and removed at most once.

### 💾 Space

**O(N)**

The `eaten[]` array stores whether each snack was eaten.

---

# Java Solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int m=sc.nextInt();
      long k=sc.nextLong();
      long[] a=new long[n];
      for(int i=0;i<n;i++){
        a[i]=sc.nextLong();
      }
      boolean[] eaten=new boolean[n];
      long sum=0;
      for(int i=0;i<n;i++){
        // remove the value leaving the recent window if it was eaten
        if(i>=m && eaten[i-m]){
          sum-=a[i-m];
        }
        // try to eat today's snack
        if(a[i]+sum<=k){
          eaten[i]=true;
          sum+=a[i];
          System.out.println("Yes");
        }else{
          eaten[i]=false;
          System.out.println("No");
        }
      }
    }
}
```

# 📌 Key Takeaways

* ✅ This is a **Sliding Window** problem.
* ✅ Maintain the calorie sum of **eaten snacks only**.
* ✅ Remove `A[i-M]` when it leaves the window **only if it was eaten**.
* ✅ Use `long` because `K` can be as large as `10^15`.
* ✅ Today's decision uses:

```text
sum + A[i] <= K
```

* ✅ Each element is added and removed at most once.
* ✅ Overall complexity: **O(N)**.
