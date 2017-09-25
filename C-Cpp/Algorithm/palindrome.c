//判断回文
#include <stdio.h>
#include <time.h>

int isPalindrome(char *a,int length);

int main(){
    clock_t t1,t2;
    int s, ms;
    t1=clock();//获取tick数。

    char* str = "";
    int length = sizeof(str)/sizeof(str[0]);

   for(int i=0; i<1000; i++){
    int result = isPalindrome(str,length);
    printf("%d\n",result);
    }

   t2=clock();//获取tick数。
   s=(t2-t1)/CLOCKS_PER_SEC;//计算秒。
   ms=(t2-t1)%CLOCKS_PER_SEC*1000/CLOCKS_PER_SEC;//计算毫秒值。
   printf("程序已经运行%d.%03d秒\n", s, ms);//输出结果。
    return 0;
}

//判断是否为回文的具体实现：是则返回1，否返回0
int isPalindrome(char *a,int length){
    int i;
    for(i=0; i<length/2; i++){
        if(a[i] != a[length-1-i]){
            return 0;
        }
    }
    return 1;
}

