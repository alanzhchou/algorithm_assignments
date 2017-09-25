#include "stdio.h"
#include "stdlib.h"
#include "time.h"

void printarr(int a[],int n);
void quickSort(int a[], int head, int tail);
void randarr(int a[],int counter, int down_range,int up_range);
int binary_search(int a[],int L,int r,int key);//find any a[i] = key 
int binary_search_first(int a[],int L,int R,int key);//find the first a[i] = key 
int binary_search_last(int a[],int L,int R,int key);//find the last a[i] = key 



int main(int argc, char const *argv[])
{	
	int counter,down_range,up_range;

	counter = 50;
	down_range = 1;
	up_range = 20;

	int a[counter];

	
	randarr(a,counter,down_range,up_range);
	

	printarr(a,counter);
	quickSort(a,0,counter);
	printarr(a,counter);


	int key = 10;
	binary_search(a,0,counter-1,key);
	binary_search_first(a,0,counter-1,key);
	binary_search_last(a,0,counter-1,key);

	return 0;
}


int binary_search(int a[],int L,int R,int key)
{	
	if (L > R)
	{	
		printf("this is a problen,key %d is not in the array!!!\n",key);
		return -1;
	}else{
		int mid = L + (R - L)/2;

		if (key < a[mid])
		{	
			return binary_search(a,L,mid-1,key);
		}
		if (key > a[mid])
		{	
			return binary_search(a,mid+1,R,key);
		}else{
			printf("the key value %d appears at array[%d] \n",key,mid);
			return mid;
		}
	}


}


int binary_search_first(int a[],int L,int R,int key)
{
	if (L < R)
	{	
		int mid = L + (R - L)/2;

		if (key <= a[mid])
		{	
			return binary_search_first(a,L,mid,key);
		}
		if (key > a[mid])
		{	
			return binary_search_first(a,mid+1,R,key);
		}
		
	}else{
		if (key == a[R])
		{
			printf("the first key value %d appears at array[%d] \n",key,R);
			return R;
		}else{
			printf("this is a problen,key %d is not in the array!!!\n",key);
			return -1;	
		}	
	}

}

int binary_search_last(int a[],int L,int R,int key)
{
	if (L < R-1)
	{	
		int mid = L + (R - L)/2;

		if (key < a[mid])
		{	
			return binary_search_last(a,L,mid-1,key);
		}
		if (key >= a[mid])
		{	
			return binary_search_last(a,mid,R,key);
		}
		
	}else{
		if (R > 0 && key == a[R-1])
		{
			printf("the last key value %d appears at array[%d] \n",key,R-1);
			return R-1;
		}
		if (key == a[R])
		{
			printf("the last key value %d appears at array[%d] \n",key,R);
			return R;
		}else{
			printf("this is a problen,key %d is not in the array!!!\n",key);
			return -1;	
		}	
	}
}


void randarr(int a[],int counter,int down_range,int up_range)
{	//the method for product an aay of counter elements of random range being range
	//range is the range between down_range and up_range included
	//counter is the amount of a random aay 	
	srand(time(NULL));
	int i;
	for (i = 0; i < counter; ++i)
	{
		a[i] = (rand()%(up_range - down_range+1) + down_range);
	}
}

void quickSort(int a[], int head, int tail)
{		//this is a inplement of quickSort function

	int key,i,j,temp;
	key = a[head]; //set the first number as the key
	i = head;         
			
	for (j = i+1; j <= tail; ++j)
	{				
		if(a[j] <= key)
		{	
			// compare every element a[j] with key	
			++i;

			// if a[j] less than key, exchange a[j] & a[i] 		
			temp = a[j];
			a[j] = a[i];
			a[i] = temp;
					
		}
	}

	temp = a[head];
	a[head] = a[i];
	a[i] = temp;  
			//after exchange all a[j] & a[i], exchange key and a[i]
	if(head < tail)
	{		//condition for breaking 
		quickSort(a,head,i-1);
		quickSort(a,i+1,tail);
	}
}




void printarr(int a[], int n)	
{		//function of printing a aay of n elements
	printf("array[%d] = {", n);
	for (int i = 0; i < n; ++i)
	{	
		if (i != n-1)
		{
			printf("%d\t",a[i]);
		}else{
			printf("%d",a[i]);
		}		
	}
	printf("}\n");
}


