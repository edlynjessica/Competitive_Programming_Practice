# [C - Puddles](https://atcoder.jp/contests/abc450/tasks/abc450_c)

**AtCoder ABC 450 C — 300 points**

![Language](https://img.shields.io/badge/Language-Java-blueviolet?style=for-the-badge)
![Graph](https://img.shields.io/badge/Topic-Graph+%20DFS-blue?style=for-the-badge)

## Problem Statement

There is a grid with `H` rows and `W` columns.

The cell at the `i`-th row from the top and `j`-th column from the left is:

- `#` → Black
- `.` → White

Among the four-directionally connected regions consisting of white cells, find the number of those that are **surrounded by black cells**.

Two cells are adjacent if they share an edge:

```text
    ↑
←   .   →
    ↓
```

A connected component is a maximal set of white cells where every cell can be reached from every other cell by repeatedly moving to an adjacent white cell.

We need to count the connected components of white cells that **do not contain any cell on the outermost border of the grid**.

In other words, a white connected component is counted if it does not touch :

- The first row
- The last row
- The first column
- The last column

---

## Constraints

- `3 ≤ H, W ≤ 10^3`
- `H` and `W` are integers.
- Each `S[i][j]` is either `#` or `.`.

---

## Input

```text
H W
S1
S2
⋮
SH
```

---

## Output

Print the number of connected components of white cells that do not contain any cell on the outermost border.

---

## Example 1

### Input

```text
5 15
##########..###
#...#######.###
####....###..##
######.########
########....###
```

### Output

```text
2
```

### Explanation

There are two white regions that are completely surrounded by black cells.

The first region consists of the three cells in row `2`, columns `2`, `3`, and `4`.

The second region consists of five cells:

```text
row 3: columns 5, 6, 7, 8
row 4: column 7
```

Neither region touches the outer border, so the answer is `2`.

---

## Example 2

### Input

```text
10 22
######################
####.#################
###...################
##.###.##.....########
##.....##.####.#######
.######.#......#.....#
.######.#.####.#.#####
#########.....##.#####
################.#####
################.....#
```

### Output

```text
4
```

---

# Approach

This is a **Connected Components + DFS** problem.

We can think of every white cell `.` as a node in a graph.

Two white cells are connected if they are adjacent in one of the four directions:

```text
    ↑
←   .   →
    ↓
```

We need to find every connected component of white cells and determine whether that component touches the border.

The algorithm is:

1. Scan every cell in the grid.
2. If the cell is an unvisited white cell, start a DFS.
3. During DFS, visit every white cell belonging to that connected component.
4. Check whether any cell in the component lies on the border.
5. If the component does not touch the border, increment the answer.

---

# Why This Works

Every white cell belongs to exactly one connected component.

When DFS starts from an unvisited white cell, it visits **all white cells in that component**.

There are two cases.

### Case 1: The component touches the border

At least one cell in the component satisfies:

```text
i == 0
i == h - 1
j == 0
j == w - 1
```

Therefore:

```java
touches = true;
```

We do not count this component.

---

### Case 2: The component does not touch the border

DFS visits every cell in the component, but none of them is on the border.

Therefore:

```java
touches = false;
```

We increment the answer:

```java
count++;
```

Thus, every and only every surrounded white component is counted.

---

# Important Observation

We do **not** need to explicitly check whether every boundary around the white component consists of `#`.

Instead, we only need to check whether the component reaches the border.

If a white connected component does not touch the outer border, it is completely inside the grid.

Therefore:

> **A white connected component is surrounded by black cells if and only if it does not touch the outer border of the grid.**

This makes the problem much simpler.

---

# Complexity

There are at most:

```text
H × W ≤ 10^6
```

cells.

Every cell is visited at most once.

For every visited cell, we check four directions, which is constant work.

### Time Complexity

```text
O(H × W)
```

### Space Complexity

```text
O(H × W)
```

The space is used for:

- The grid
- The `visited` array
- The DFS recursion stack

---

# Java Solution

```java
import java.util.*;

public class Main {

    static int h, w;
    static char[][] grid;
    static boolean[][] visited;
    static boolean touches;

    static int[] dx = {0, -1, 0, 1}; // Left, Up, Right, Down
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        h = sc.nextInt();
        w = sc.nextInt();
        sc.nextLine();

        grid = new char[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            String s = sc.next();

            for (int j = 0; j < w; j++) {
                grid[i][j] = s.charAt(j);
            }
        }

        int count = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                if (!visited[i][j] && grid[i][j] == '.') {

                    touches = false; // If the component touches the border

                    dfs(i, j);

                    if (!touches) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    static void dfs(int i, int j) {

        visited[i][j] = true;

        // If the cell is on the border,
        // the component is not completely surrounded.
        if (i == 0 || j == 0 || i == h - 1 || j == w - 1) {
            touches = true;
        }

        // Explore Left, Up, Right, Down
        for (int d = 0; d < 4; d++) {

            int di = i + dx[d];
            int dj = j + dy[d];

            if (di >= 0 && dj >= 0 && di < h && dj < w) {

                if (grid[di][dj] == '.' && !visited[di][dj]) {
                    dfs(di, dj);
                }
            }
        }
    }
}
```

---

# Key Takeaway

This is a classic **Grid DFS + Connected Components** problem.

The pattern to remember:

```text
Scan the grid
    ↓
Find an unvisited '.'
    ↓
Start DFS
    ↓
Visit the entire connected component
    ↓
Check whether it touches the border
    ↓
    ┌───────────────┐
    │               │
   Yes              No
    │               │
    ↓               ↓
Don't count      count++
```

The key idea is:

> **Instead of checking whether a white region is directly surrounded by `#`, simply check whether the connected component reaches the border. If it does not, it is a surrounded region.**
