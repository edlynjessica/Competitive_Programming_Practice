# Sum of 1 to N

```java

// without return type

static void func(int n,int sum){
        if(n==0){
            System.out.println(sum);
            return;
        }
        func(n-1,sum+n);
    }
```


```java

// with return type

static int func(int n){
        if(n==0) return 0;
        return n + func(n-1);
    }

```
