#include <stdio.h>

char* strcpy(char* dest, const char* str);

int main(){

    char* src = "abcdefg";
    char* dest;

    strcpy(dest,src);

    printf("%s\n",dest);

    return 0;
}

char* strcpy(char* dest, const char* src){
    if(dest == NULL||src ==NULL){
        return dest;
    }
    int i=0;
    while(src[i] != '\0'){
        dest[i] = src[i];
    }
    return dest;
}