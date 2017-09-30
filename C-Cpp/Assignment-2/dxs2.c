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
PDXS addition(PDXS Da,PDXS Db);//多项式加法
PDXS subtraction(PDXS Da,PDXS Db);//多项式减法
PDXS multiplication(PDXS Da,PDXS Db);//多项式乘法
PDXS differentiation(PDXS phead);//多项式求导

int main(){
    //创建两个多项式的链表，创建一个就打印输出一个
    PDXS Da = create_dxs();
    print_dxs(Da);
    PDXS Db = create_dxs();
    print_dxs(Db);

    //测试多项式的加法
    PDXS Dout = addition(Da,Db);
    print_dxs(Dout);

    //测试多项式的减法
    PDXS Dout = subtraction(Da,Db);
    print_dxs(Dout);

    // //测试多项式的乘法
    // PDXS Dout = multiplication(Da,Db);
    // print_dxs(Dout);

    // //测试多项式的求导
    // PDXS Dout = differentiation(Da,Db);
    // print_dxs(Dout);

    return 0;
}

PDXS create_dxs(){
    PDXS pHead = (PDXS)malloc(sizeof(DXS));//为头结点申请内存
    pHead->pNext = NULL;//下一个域的指针

    PDXS pTail = pHead; //创建的链表尾部，随每次插入新的节点而更新
    PDXS pNew = NULL;//这里先声明下个链上的新节点

    int length;  //链表的长度
    float c;  //多项式的各项系数
    int e;  //多项式的各项指数
    int i;  

    printf("input the length of the polynomial = ");
    scanf("%d",&length);//获取多项式的长度，
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

        pTail->pNext = pNew;
        pTail = pNew;
    }
    return pHead;//返回链表的头指针，用->pNext不断索引下个节点
}


void print_dxs(PDXS pHead)
{  
    printf("**********NEXT**********\n");
    PDXS p = pHead->pNext;  //首节点  
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

int findDegree(PDXS pHead){
    PDXS p = pHead->pNext;

    int max = 0;
    while(p != NULL){
        if(p->expo > max){
            max = p->expo;
        }
    }
    return max;
}

//实现多项式的加法
PDXS addition(PDXS Da, PDXS Db){
    PDXS Dout = (PDXS)malloc(sizeof(DXS));//储存多项式和的链表
    Dout->pNext = NULL;//头结点初始化为NULL
    PDXS pTail = Dout;

    PDXS DaHead = Da->pNext;
    PDXS DbHead = Db->pNext;

    while(DaHead && DbHead){
        if(DaHead->expo < DbHead->expo){
            pTail->pNext = DaHead;
            pTail = DaHead;

            DaHead = DaHead->pNext;
        }else if(DaHead->expo > DbHead->expo){
            pTail->pNext = DbHead;
            pTail = DbHead;

            DbHead = DbHead->pNext;
        }else{
            if((DaHead->coef + DbHead->expo) != 0){

                DaHead->coef = DaHead->coef + DbHead->coef;
                pTail->pNext = DaHead;
                pTail = DaHead;
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

    return Dout;
}

//实现多项式的减法----未完成
PDXS subtraction(PDXS Da,PDXS Db){
    PDXS Dout = (PDXS)malloc(sizeof(DXS));//储存多项式和的链表
    Dout->pNext = NULL;//头结点初始化为NULL
    PDXS pTail = Dout;

    PDXS DaHead = Da->pNext;
    PDXS DbHead = Db->pNext;

    while(DaHead && DbHead){
        if(DaHead->expo < DbHead->expo){
            pTail->pNext = DaHead;
            pTail = DaHead;

            DaHead = DaHead->pNext;
        }else if(DaHead->expo > DbHead->expo){
            pTail->pNext = DbHead;
            pTail = DbHead;

            DbHead = DbHead->pNext;
        }else{
            if((DaHead->coef + DbHead->expo) != 0){

                DaHead->coef = DaHead->coef + DbHead->coef;
                pTail->pNext = DaHead;
                pTail = DaHead;
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

    return Dout;
}