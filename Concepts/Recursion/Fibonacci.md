# Fibonacci Series - Multiple Recursive Calls

```java

static int fibo(int n){

    if(n==0 || n==1) return n;
    return fibo(n-1)+fibo(n-2);
}

```
