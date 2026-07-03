## 1. Print 1 to N

```java
static void func(int n){
        if(n==0) return;
        func(n-1);
        System.out.print(n+" ");
    }
```

## 2. Print N to 1

```java
static void func(int n){
        if(n==0) return;
        System.out.print(n+" ");
        func(n-1);
    }
```
