# break label 与 continue label

* break label 跳到label处,循环中逻辑不再执行; 
* continue label 直接调到label出继续运行循环内容;
* label必须出现在break和continue之前

## break label

```java
 public void testBreakContinue() {
        int i = 1, j = 0;

        label:
        while (true) {
            System.out.println("first loop");
            while (true) {
                System.out.println("second loop");
                if (j * i == 6) {
                    System.out.println("8");
                    break label;
                }
                j++;
            }
        }

    }
```

输出:

```text
first loop
second loop
second loop
second loop
second loop
second loop
second loop
second loop
8
```

## continue label

```java
 public void testBreakContinue() {
        int i = 1, j = 0;

        label:
        while (true) {
            System.out.println("first loop");
            while (true) {
                System.out.println("second loop");
                if (j * i == 6) {
                    System.out.println("8");
                    continue label;
                }
                j++;
            }
        }

    }
```

输出:

```text
first loop
second loop
second loop
second loop
second loop
second loop
second loop
second loop
8
first loop
second loop
8
first loop
second loop
8
first loop
second loop
8
```