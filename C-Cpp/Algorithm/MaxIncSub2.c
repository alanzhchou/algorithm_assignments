/*
*最大递增子序列
*StudentID:11510629
*name:周恒
*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int LISS(const int arr[],int length,int result[]);
void printArr(int a[],int n);//the method of printing an array of n elements
void randArr(int a[],int counter,int down_range,int up_range);
void storeFile(int a[],int n, FILE *fp); //store the array into a file
void getFile(int b[],int n, FILE *fp);//read from a file
void getch();//just for waiting




int main(int argc, char const *argv[])
{
	int n = 10;	//this is the size of the random array
	int down_range = -5;
	int up_range = 5;//the range of elements in array
	int a[n];		//the inital random array
	int b[n];		//the array read from file
	int result[n];		//the array read from file 


	FILE *fp = fopen("Data_Store.txt","wb+");//the docu structure
		if( fp == NULL )//if there are problems with docu,exit
		{
      		printf("Error on write Data_Store.txt file!");
		    getch();
		    exit(1);
 		}

 	randArr(a,n,down_range,up_range);
	printf("the inital array : \n");
	//show that the inital random array
	printArr(a,n);

	storeFile(a,n,fp);
	getFile(b,n,fp);
	//show that the random array has been stored in file
	printf("has been stored in file : \n");
	printArr(b,n);

	printf("the number of increasing elements:\t%d\n",LISS(a,10,result));	
	printf("the elements are:\n");
	printArr(result,LISS(a,10,result));



	rewind(fp);//store from the head
	fclose(fp);

	return 0;
}



int LISS(const int arr[],int length,int result[])
{
	int i,j,k,max;
	//i,j,k作为循环遍历变量
	//max作为返回值，储存最长递增子序列的长度

	int liss[length];
	//liss数组为储存原输入数组(arr)中以第i为结尾元素的最长递增子序列的长度

	int pre[length];
	//pre数组为储存原输入数组(arr)中以第i为结尾元素的最长递增子序列的
	//最后元素的前驱元素，即result[]有效的倒数第二个元素，


	for (i = 0; i < length; ++i)
	{//初始化每个liss【】元素，使其默认初始长度都为1，且其前驱元素为自己
		liss[i] = 1;
		pre[i] = i;
	}


	for (i = 1,max = 1,k = 0;i < length;++i)
	{	
		//对i = 1 到i = length - 1 进行遍历
		//求出以每个元素为末尾元素的最长递增子序列，储存其长度和前驱元素
		for (j = 0; j < i; ++j)
		{
			//对每个取出来的第i个元素前的所有元素进行遍历
			//将满足要求的情况下对liss【】和pre【】进行操作
			//操作前提为第i个元素，前有这样一个元素：（第j个）
			//arr[j]比arr[i]要小，且以arr[j]为末尾的最长递增子序列
			if (arr[j] < arr[i]&&liss[j]+1 > liss[i])
			{	
				liss[i] = liss[j] + 1;
				pre[i] = j;
			
				if(max < liss[i])
				{
					max = liss[i];
					k = i;
				}
			}
		}
	}

	i = max - 1;

	while(pre[k] != k)
	{
		result[i--] = arr[k];
		k = pre[k];
	}
	result[i] = arr[k];

	return max;

}



void printArr(int a[], int n)	
{		//function of printing a array of n elements
	for (int i = 0; i < n; ++i)
	{
		printf("%d\t",a[i]);
	}
	printf("\n\n\n");
}





void randArr(int a[],int counter,int down_range,int up_range)
{	//the method for product an array of counter elements of random range being range
	//range is the range between down_range and up_range included
	//counter is the amount of a random array 	
	srand(time(NULL));
	int i;
	for (i = 0; i < counter; ++i)
	{
		a[i] = (rand()%(up_range - down_range + 1) + down_range);
	}
}

void storeFile(int a[],int n, FILE *fp)
{		//store an array in file *fp
	int i = 0;
	for (int i = 0; i < n; ++i)
	{
		fwrite(a, sizeof(int), n, fp);
	}
}

void getFile(int b[],int n, FILE *fp)
{		//get an array from file *fp
	int i = 0;
	for (int i = 0; i < n; ++i)
	{	
		rewind(fp);
		fread(b, sizeof(int), n, fp);
	}
}

