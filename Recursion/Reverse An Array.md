# Reverse An Array

```java
static void reverse(int left,int right,int[] arr){
        if (left >= right) return;
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        rev(left+1, right-1 , arr);
}
```
