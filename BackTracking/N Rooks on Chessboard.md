# ♜ N Rooks on Chessboard — Backtracking Placement

## 📌 Problem Overview  
You’re given an **N × N chessboard** with some rooks already placed. Your goal is to place the remaining rooks such that:

- Exactly **one rook per row**
- Exactly **one rook per column**
- No two rooks attack each other

If multiple valid arrangements exist, return the one that fills the **leftmost columns first (top to bottom priority)**.

---

## 🧩 Problem Statement  
Complete a partially filled chessboard by placing the remaining rooks while satisfying all constraints.

---

## 🗺️ Board Representation  

| Value | Meaning              |
|------|----------------------|
| 0    | Empty cell           |
| 1    | Rook already placed  |

---

## ❗ Constraints  

- `2 ≤ N ≤ 20`  
- `0 ≤ R ≤ N − 1`  

---

## 🎯 Objective  

Fill the board with **N rooks total** such that:

- No two rooks share the same row  
- No two rooks share the same column  
- Pre-placed rooks remain unchanged  
- Output the **lexicographically smallest valid configuration**

---

## 📥 Input Format  
```
N
row1 (N values)
row2
...
rowN
```
---

## 📤 Output Format  
```
N lines with N values (0 or 1)
```

---

## 🧠 Approach  

This is a **Backtracking problem** (similar to N-Queens LC 51 but without diagonal constraints).

### Key Ideas:
- Each row must contain exactly one rook  
- Use a boolean array to track used columns  
- Respect already placed rooks  
- Always try columns from **left → right** for correct ordering  

---

## ⏱️ Complexity  

- **Time Complexity:** `O(N!)` (worst case)  
- **Space Complexity:** `O(N)`  

---

## 🧪 Example  

### Input
```
4
1 0 0 0
0 0 0 0
0 1 0 0
0 0 0 1
```
### Output
```
1 0 0 0
0 0 1 0
0 1 0 0
0 0 0 1
```

---

## 🏷️ Tags  

- Backtracking  
- Recursion  
- Matrix / Grid  
- Constraint Satisfaction  
- DFS  

---

## 🚀 Related Problems  

- N-Queens  
- Sudoku Solver  
- Permutations with Constraints   

---

## 💡 Insight  

This problem is simpler than N-Queens because it ignores diagonals.  

---

✨ Clean, classic backtracking problem — great for mastering recursion and constraints!
