# String Palindrome Check

```java
static boolean check(int left,int right,String s){

        if(left>=right) return true;
        if(s.charAt(left)!=s.charAt(right)) return false;
        return check(left+1,right-1,s);

}
```
