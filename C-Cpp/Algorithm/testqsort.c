/*
*author：周恒
*student ID:11510629
*
*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void printArr(int a[],int n);//the method of printing an array of n elements
void quickSort(int a[], int head, int tail);// the quicksort,from smallest to largest
void randArr(int a[],int range, int counter);//product a "counter" elements range in range random array
void storeFile(int a[],int n, FILE *fp); //store the array into a file
void getFile(int b[],int n, FILE *fp);//readd from a file
void getch();//for waiting


int main(int argc, char const *argv[])
{	
	int n = 10000;	//this is the size of the random array
	int range = 1000;//the range of elements in array
	int a[n];		//the inital random array
	int b[n];		//the array read from file 
	FILE *fp = fopen("Data_Store.txt","wb+");//the docu structure
		if( fp == NULL )//if there are problems with docu,exit
		{
      		printf("Error on write Data_Store.txt file!");
		    getch();
		    exit(1);
 		}

 	
	randArr(a,n,range);
	printf("the inital array : \n");
	//show that the inital random array has been stored in file
	printArr(a,n);
	storeFile(a,n,fp);
	getFile(b,n,fp);
	printf("has been stored in file : \n");
	printArr(b,n);




	quickSort(a,0,n);
	printf("array after sorting : \n");
	printArr(a,n);

	rewind(fp);//store from the head

	//show that the array stored in file has been changed
	storeFile(a,n,fp);
	getFile(b,n,fp);
	printf("has been stored in file : \n");
	printArr(b,n);



	
	fclose(fp);
	return 0;
}


void randArr(int a[],int counter, int range)
{		//the method for product an array of counter elements of random range being range
		//range is the range of the random array 
		//counter is the amount of a random array 	
	srand(time(NULL));

	for (int i = 0; i < counter; ++i)
	{
		a[i] = rand()%range+1;
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


void printArr(int a[], int n)	
{		//function of printing a array of n elements
	for (int i = 0; i < n; ++i)
	{
		printf("%d\t",a[i]);
	}
	printf("\n\n\n");
}