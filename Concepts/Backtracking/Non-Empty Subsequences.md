# Print All Non-Empty Subsequences (Backtracking)

Prints all **non-empty subsequences** of a string using **recursion + backtracking**.

## Approach

For every character, we have two choices:

- **Include** the character
- **Exclude** the character

The recursion always follows this order:

```text
Include
   ↓
Print
   ↓
Recurse
   ↓
Backtrack
   ↓
Exclude
```

The `StringBuilder` is reused throughout the recursion. After exploring the **include** branch, we remove the last character (`deleteCharAt`) so the **exclude** branch starts with the correct previous state.

---

## Recursion Tree (`"abcd"`)

```text
                              "" (empty string)
                         /            \
                       a               -
                    /     \         /     \
                  ab       a       b       -
                 /  \     / \     / \     / \
              abc   ab   ac  ad  bc  bd  c   -
             /  \         |       |      |    \
         abcd  abd      acd     bcd     cd     d
                                         
```

`-` = Character excluded

---

## Java Code

```java
import java.util.*;

class Main{
    public static void main(String[] arg){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int n=s.length();
        helper(0,new StringBuilder(),s);
    }
    public static void helper(int i, StringBuilder sb, String s){
        if(i==s.length()) return;
        // include
        sb.append(s.charAt(i));
        System.out.println(sb.toString());
        helper(i+1, sb, s);
        // backtrack
        sb.deleteCharAt(sb.length() - 1);
        // exclude
        helper(i+1,sb,s);
    }
}
```

---

## Dry Run

```text
Start

sb = ""

append(a) -> "a"     print
append(b) -> "ab"    print
append(c) -> "abc"   print
append(d) -> "abcd"  print

delete(d) -> "abc"

delete(c) -> "ab"
append(d) -> "abd"   print
delete(d) -> "ab"

delete(b) -> "a"
append(c) -> "ac"    print
...
```

The same `StringBuilder` is modified throughout the recursion:

- `append()` → Choose
- Recursive call → Explore
- `deleteCharAt()` → Undo (Backtrack)

---

## Complexity

- **Time:** `O(n × 2ⁿ)`
- **Space:** `O(n)`
