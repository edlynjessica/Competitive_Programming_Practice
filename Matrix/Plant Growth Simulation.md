# 🌱 Plant Growth Simulation

## 📌 Problem Statement

In a matrix containing **R** rows and **C** columns, various plants are planted. 
The growth of a given plant in a calendar month depends on it's current height at the beginning of the month. 
If the **current height is a prime number**, the height by which the plant grows in that month is equal to the *current height minus previous prime number*
(If there is no previous prime number consider the previous prime number as zero). 
If the **current height is not a prime number** then the height by which the plant grows in that month is equal to the *sum of the unit digits of the adjacent plants height at the beginning of the month* 
(If there is no adjacent plant to left or right consider the value as zero). Given the current height of the plants at the beginning of first month, print the height of the plants after N months.

---

## 🌿 Growth Rules

For each plant:

### 🔹 If the height is a prime number:
Growth = Current Height − Previous Prime  
- If no previous prime exists, use 0

### 🔹 If the height is not a prime number:
Growth = (Unit digit of left neighbor) + (Unit digit of right neighbor)  
- If a neighbor does not exist, consider it as 0

---

## 📥 Input Format
```
R C
<Next R lines with C space-separated integers>
N
```
## 📤 Output Format
```
R lines with C space-separated integers (final heights after N months)
```

---

## 📏 Constraints

- 1 ≤ R, C ≤ 999  
- 1 ≤ N ≤ 100  
- 1 ≤ Initial height ≤ 100  

---

## 📘 Example

### Input
```
3 3
1 2 4
6 1 1
5 7 12
2
```
### Output
```
4 13 10
9 20 23
9 25 21
```
---

## 🧠 Approach

1. Repeat for N months:
   - Create a temporary matrix
   - For each cell:
     - Check if prime
     - Apply corresponding growth rule
   - Update the original matrix

---

## ⚡ Helper Functions

- `isPrime(n)` → Check if number is prime  
- `previousPrime(n)` → Largest prime less than n  
- `unitDigit(n)` → n % 10  

---
