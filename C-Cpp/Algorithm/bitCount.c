#include <stdio.h>

int bitCount(int n);

int main(){

    int a = 64;
    
    printf("%d\n",bitCount(a));
    return 0;
}

int bitCount(int n){
    int count = 0;
    while(n>0){
        count++;
        n &= (n-1);
    }
    return count;
}