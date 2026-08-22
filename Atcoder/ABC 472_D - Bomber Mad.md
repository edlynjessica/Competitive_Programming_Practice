# [D - Bomber Mad](https://atcoder.jp/contests/abc472/tasks/abc472_d)

**AtCoder — D - Bomber Mad**

![Language](https://img.shields.io/badge/Language-Java-blueviolet?style=for-the-badge)
![Graph](https://img.shields.io/badge/Topic-Graph+%20BFS-blue?style=for-the-badge)

## Problem Statement

There is a grid with *H* rows and *W* columns. Each cell is either an empty cell or a bomb cell.

For an empty cell `(i,j)`, if there is **no bomb cell in the i-th row nor in the j-th column**, that cell is called a **safe empty cell**.

In one move, you can move from the current cell to an adjacent empty cell in the up, down, left, or right direction. You cannot move to a bomb cell.

Find the number of *empty* cells `(i,j)` from which a **safe empty cell** can be reached in at most **K moves**.

---

## Constraints

* `1 ≤ H, W ≤ 5 × 10^5`
* `H × W ≤ 5 × 10^5`
* `0 ≤ K ≤ H × W − 1`
* `Sᵢ` is a string of length `W` consisting of `.` and `#`.
* `H`, `W`, and `K` are integers.

---

## Input

```text
H W K
S₁
S₂
⋮
Sₕ
```

---

## Output

Output the number of empty cells satisfying the condition.

---

## Example 1

### Input

```text
3 3 1
#..
...
..#
```

### Output

```text
5
```

The only **safe empty cell** is `(2,2)`.

There are five empty cells from which `(2,2)` can be reached in at most one move:

```text
(1,2), (2,1), (2,2), (2,3), (3,2)
```

---

## Example 2

### Input

```text
2 3 0
...
...
```

### Output

```text
6
```

Since there is no bomb cell, all six cells are safe empty cells. Therefore, every empty cell satisfies the condition with zero moves.

---

## Example 3

### Input

```text
5 7 2
..#....
..#....
.......
...#...
...#...
```

### Output

```text
29
```

# Approach

### 💡 Key Observation

First, identify all **safe empty cells**.

A cell is safe when:

```text
grid[i][j] == '.'
AND
row i contains no bomb
AND
column j contains no bomb
```

So we maintain:

```java
boolean[] bombRow
boolean[] bombCol
```

to quickly determine whether a row or column contains a bomb.

---

### 🌱 Step 1: Mark Bomb Rows and Columns

While reading the grid:

```java
if(grid[i][j]=='#'){
    bombRow[i]=true;
    bombCol[j]=true;
}
```

After this:

* `bombRow[i] = true` → row `i` contains a bomb.
* `bombCol[j] = true` → column `j` contains a bomb.

---

### 🌱 Step 2: Find All Safe Cells

For every empty cell:

```java
if(grid[i][j]=='.' && !bombRow[i] && !bombCol[j])
```

it is a safe empty cell.

Put **all safe cells into the BFS queue initially**.

This is called **Multi-Source BFS**.

```text
Safe Cell 1 ─┐
Safe Cell 2 ─┤
Safe Cell 3 ─┼──→ BFS
Safe Cell 4 ─┤
Safe Cell 5 ─┘
```

Set their distance to `0`.

---

### 🌱 Step 3: Multi-Source BFS

Normally, BFS starts from one source.

Here, we have **multiple safe cells**, so all of them are inserted into the queue initially.

```java
dist[i][j] = 0;
```

Then BFS expands from all safe cells simultaneously.

For every neighboring empty cell:

```java
dist[nr][nc] = dist[r][c] + 1;
```

The first time a cell is reached, BFS guarantees that its distance from the **nearest safe cell** is minimum.

Therefore:

```text
dist[i][j] = minimum number of moves
             required to reach any safe cell
```

---

### 🌱 Step 4: Count Cells Within K Moves

After BFS, count every empty cell whose distance is at most `K`.

```java
if(dist[i][j]!=-1 && dist[i][j]<=k)
    ans++;
```

So the final condition is:

```text
dist[i][j] ≤ K
```

---

### ⭐ Why Multi-Source BFS?

The problem asks:

> Can this cell reach **any safe cell** within K moves?

We don't care which safe cell is reached.

By starting BFS from **all safe cells simultaneously**, the distance stored for every cell becomes its distance to the **nearest safe cell**.

Therefore, we only need:

```text
minimum distance ≤ K
```

---

# Complexity

Let:

```text
N = H × W
```

Each cell is processed at most once during BFS.

### ⏱️ Time

**O(H × W)**

### 💾 Space

**O(H × W)**

For:

* `grid`
* `dist`
* BFS queue

Since `H × W ≤ 5 × 10^5`, this fits the constraints.

---

# Java Solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int h=sc.nextInt();
      int w=sc.nextInt();
      int k=sc.nextInt();
      boolean[] bombRow = new boolean[h];
      boolean[] bombCol = new boolean[w];
      
      char[][] grid=new char[h][w];
      for(int i=0;i<h;i++){
        String row=sc.next();
        for(int j=0;j<w;j++){
          grid[i][j]=row.charAt(j);
          if(grid[i][j]=='#'){ // mark cols and rows with # (bomb)
            bombRow[i]=true;
            bombCol[j]=true;
          }
        }
      }
    
      int[][] dist=new int[h][w];
      for(int[] row:dist){
        Arrays.fill(row,-1); // -1 means not visited
      }
      // '.' are the safe cells now and 'E' are empty cells
      // multi source bfs
      Queue<int[]> q=new ArrayDeque<>();
      int safe=0;
      for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
          if(grid[i][j]=='.' && !bombRow[i] && !bombCol[j]){
            q.offer(new int[]{i,j});
            safe++;
            dist[i][j]=0;
          }
        }
      }
      if(safe==0){
        System.out.println(0);
        return;
      }
      int[][] dirs={
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
      };
  
      while(!q.isEmpty()){
        int[] curr=q.poll();
        int r=curr[0];
        int c=curr[1];
        for(int[] d:dirs){
          int nr=r+d[0];
          int nc=c+d[1];
          if(nr>=0 && nc>=0 && nr<h && nc<w && grid[nr][nc]=='.' && dist[nr][nc]==-1){
            dist[nr][nc]=dist[r][c]+1;
            q.offer(new int[]{nr,nc});
          }
        }
      }
      int ans=0;
      for(int i=0;i<h;i++){
        for(int j=0;j<w;j++){
          if(dist[i][j]!=-1 && dist[i][j]<=k) ans++;
        }
      }
      System.out.println(ans);
    }
}
```

# 📌 Key Takeaways

* ✅ First identify **safe empty cells** using bomb rows and columns.
* ✅ Put **all safe cells** into the queue.
* ✅ This makes the problem a **Multi-Source BFS**.
* ✅ `dist[i][j]` represents the distance to the **nearest safe cell**.
* ✅ Count cells where `dist[i][j] ≤ K`.
* ✅ Overall complexity: **O(H × W)**.
