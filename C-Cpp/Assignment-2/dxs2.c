/*
*链表实现多项式相加
*周恒
*/

#include <stdio.h>
#include <malloc.h>

typedef struct dxs{  //多项式结构体，包含系数，指数，以及指向下一个系数及指数的指针
    float coef;
    int expo;
    struct dxs* pNext; 
}DXS, *PDXS;        //全局定义的多项式类型的变量DXS和多项式类型的指针PDXS

PDXS create_dxs();//创建多项式的方法
void print_dxs(PDXS pHead);//打印输出对象多项式
int findDegree(PDXS pHead);//找最大的指数，即多项式的次数
PDXS addition(PDXS Da,PDXS Db);//多项式加法,由指数从小到大输入各项
PDXS subtraction(PDXS Da,PDXS Db);//多项式减法
PDXS inverse(PDXS Da);
int getLen(PDXS head);
PDXS multiplication(PDXS Da,PDXS Db);//多项式乘法
PDXS differentiation(PDXS phead);//多项式求导

int main(){
    //创建两个多项式的链表，创建一个就打印输出一个
    PDXS Da = create_dxs();
    print_dxs(Da);
    PDXS Db = create_dxs();
    print_dxs(Db);


    // printf("length:%d\t%d\n\n",getLen(Da),getLen(Db));
    //测试输出多项式的次数
    // printf("\nthe highest degree of polynomial is %d \n\n",findDegree(Da));
    // printf("\nthe highest degree of polynomial is %d \n\n",findDegree(Db));
    
    // print_dxs(inverse(Db));

    //测试多项式的加法
    print_dxs(addition(Da,inverse(Db)));

    // print_dxs(addition(Da,inverse(Db)));

    // // print_dxs(inverse(Da));

    // //测试多项式的减法
    // print_dxs(subtraction(Da,Db));

    // //测试多项式的乘法
    // PDXS Dout = multiplication(Da,Db);
    // print_dxs(Dout);

    // //测试多项式的求导
    // PDXS Dout = differentiation(Da,Db);
    // print_dxs(Dout);

    return 0;
}


PDXS subtraction(PDXS Da,PDXS Db){
    PDXS DaHead = Da->pNext;
    PDXS DbHead = Db->pNext;

    if(DaHead != NULL&&DbHead != NULL){
        //两个多项式都不为空，则进行减法运算
        return addition(inverse(Db),Da);
    }else if(DaHead == NULL&&DbHead != NULL){
        return inverse(Db);
    }else if(DbHead == NULL&&DaHead != NULL){
        //减数是空链表，直接返回被减数多项式
        return Da;
    }else{
        //如果两个多项式都是空的，这直接返回一个空链表
        PDXS Dout = (PDXS)malloc(sizeof(DXS));//储存多项式和的链表
        Dout->pNext = NULL;//头结点初始化为NULL
        return Dout;   
    }
}

int getLen(PDXS Da)
{
    PDXS p=Da->pNext ;
    int len=0;
    while (p)
    {
            len++;
            p=p->pNext;
    }
    return len; 
}

PDXS create_dxs(){
    //*****************这里生成的头结点并无数据，只是指向有数据的节点的一个指针
    PDXS pHead = (PDXS)malloc(sizeof(DXS));//为头结点申请内存
    pHead->pNext = NULL;//下一个域的指针

    PDXS pTail = pHead; //创建的循环遍历指针，随每次插入新的节点而更新
    PDXS pNew = NULL;//这里先声明下个链上的新节点

    int length;  //链表的长度
    float c;  //多项式的各项系数
    int e;  //多项式的各项指数
    int i;  

    printf("input the length of the polynomial = ");
    scanf("%d",&length);//获取多项式的长度。

    if(length <= 0){
        return pHead;
    }

    printf("will create a polynomial of length %d\n",length);
    for(i=0; i<length; i++){
        printf("coefficient of the %dth term = \n",i+1);
        scanf("%f",&c);
        printf("exponent of the %dth term = \n",i+1);
        scanf("%d",&e);//获取多项式的各项的系数和指数

        pNew = (PDXS)malloc(sizeof(DXS));//为新节点申请内存

        pNew->coef = c;
        pNew->expo = e;
        pNew->pNext = NULL;//使下个节点为空

        pTail->pNext = pNew;//这里第一次用时是将pHead的pNext指向新节点，之后用于不断向下遍历
        pTail = pNew;//在这里向下遍历，将pTail的下一个指针指向新指针
    }
    return pHead;//返回链表的头指针，用->pNext不断索引下个节点
}

void print_dxs(PDXS pHead)
{  
    printf("************************\n");
    PDXS p = pHead->pNext;  //首节点  

    if(p == NULL){
        printf("\n**********NOTHING**********\n\n");
    }else{
        while(p != NULL)  
        {  
            printf("%.1fX^%d", p->coef, p->expo);  
            p = p->pNext;  
            if(p != NULL){
                printf(" + ");
            }
        }  
        printf("\n**********NEXT**********\n\n");
    }
    
}  

int findDegree(PDXS pHead){
    PDXS p = pHead->pNext;
    int max = 0;
    while(p != NULL){
        if(p->expo > max){
            max = p->expo;
        }
        p = p->pNext;
    }
    //长度为0时输出零，否则输出最大的指数值
    return max;
}

//实现多项式的加法
PDXS addition(PDXS Da, PDXS Db){
    PDXS Dout = (PDXS)malloc(sizeof(DXS));//储存多项式和的链表
    Dout->pNext = NULL;//头结点初始化为NULL
    PDXS pTail = Dout;

    PDXS DaHead = Da->pNext;
    PDXS DbHead = Db->pNext;

    if(DaHead != NULL&&DbHead != NULL){
        //两个多项式都不为空，则进行加法运算
        print_dxs(Dout);
        while(DaHead && DbHead){
            //比较当前两节点的指数  
            //当前 A项节点指数 < B项节点指数 
            if(DaHead->expo < DbHead->expo){
                pTail->pNext = DaHead;//将此A项加入和链表中  
                pTail = DaHead;
                
                DaHead = DaHead->pNext;//A多项式向后遍历  

            }else if(DaHead->expo > DbHead->expo){
                pTail->pNext = DbHead;
                pTail = DbHead;

                DbHead = DbHead->pNext;
            }else{
                if((DaHead->coef + DbHead->coef) != 0){
                    DaHead->coef = DaHead->coef + DbHead->coef;
                    pTail->pNext = DaHead;
                    pTail = DaHead;
                }else(){
                    
                }
                DaHead = DaHead->pNext;
                DbHead = DbHead->pNext;
            }
        }
        if(DaHead != NULL){
            pTail->pNext = DaHead;
        }
        if(DbHead != NULL){
            pTail->pNext = DbHead;
        }
    }else if(DaHead == NULL&&DbHead != NULL){
        //某一项是空链表，直接返回另一个多项式
        Dout = Db;
    }else if(DbHead == NULL&&DaHead != NULL){
        Dout = Da;
    }else if(DbHead == NULL&&DaHead == NULL){
        //如果两个多项式都是空的，这直接返回一个空链表
    }

    return Dout;
}

PDXS inverse(PDXS Da){
    PDXS Dout = (PDXS)malloc(sizeof(DXS));//储存多项式和的链表
    Dout->pNext = NULL;//头结点初始化为NULL
    PDXS pTail = Dout;

    PDXS DaHead = Da->pNext;

    while (DaHead)
    {   
        DaHead->coef = 0 - DaHead->coef;
        pTail->pNext = DaHead;
        pTail = DaHead;
        DaHead = DaHead->pNext;
    }
    return Dout;
}