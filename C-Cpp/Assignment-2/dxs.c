/* 
    一元多多项式的加法 
    1.先创建链表，存储多项式 
    2.输出多项式 
    3.两个多项式相加 
    4.输出多项式 
*/  
  
# include <stdio.h>  
# include <malloc.h>  
  
typedef struct dxs  //多项式节点  
{  
    float coe;  //系数  
    int exp;   //指数  
    struct dxs * pNext;  //指针域  
  
}DXS, * PDXS;  
  
PDXS creat_dxs();   //创建多项式  
void traverse(PDXS pHead);   //遍历多项式链表  
PDXS add(PDXS Da, PDXS Db);  //多项式求和  
  
int main(void)  
{  
    //用链表结构，创建两个多项式  
    PDXS Da = creat_dxs();  
    traverse(Da);  
  
    PDXS Db = creat_dxs();  
    traverse(Db);  
  
    //求两个多项式的加法  
    PDXS Dj = add(Da, Db);  
    traverse(Dj);  
  
    return 0;  
}  
  
PDXS creat_dxs()  
{  
    PDXS pHead = (PDXS)malloc(sizeof(DXS)); //创建头结点  
    pHead->pNext = NULL;  //尾指针  
  
    PDXS pTail = pHead;  
    PDXS pNew = NULL;  
  
    int len;  
    float c;  
    int e;  
    int i;  
  
    printf("输入多项式的项数：len = ");  
    scanf("%d", &len);  
  
    for(i = 0; i < len; i++)  
    {  
        printf("分别输入第%d项的系数c、指数e:", i+1);  
        scanf("%f%d", &c, &e);  
          
        pNew = (PDXS)malloc(sizeof(DXS));  
  
        //多项式项，系数、指数  
        pNew->coe = c;  
        pNew->exp = e;  
        pNew->pNext = NULL;  
  
        pTail->pNext = pNew;  
        pTail = pNew;  
    }  
  
    return pHead;  
}  
  
//遍历链表  
void traverse(PDXS pHead)  
{  
    PDXS p = pHead->pNext;  //首节点  
  
    while(p != NULL)  
    {  
        printf("(%.2f %d), ", p->coe, p->exp);  
        p = p->pNext;  
    }  
    printf("\n");  
}  
  
  
//多项式相加  
PDXS add(PDXS Da, PDXS Db)  
{  
    PDXS Dj = (PDXS)malloc(sizeof(DXS));  //和的头结点  
    Dj->pNext = NULL;  
    PDXS pTail = Dj;  //和的尾节点  
  
    PDXS Dah = Da->pNext;  //指向多项式的首节点  
    PDXS Dbh = Db->pNext;  
  
    //循环遍历多项式A，B  
    while(Dah && Dbh)  
    {  
        //比较当前两节点的指数  
        //当前 A项节点指数 < B项节点指数  
        if(Dah->exp < Dbh->exp)  
        {  
            pTail->pNext = Dah;  //将此A项加入和链表中  
            pTail = Dah;  
  
            Dah = Dah->pNext;  //A多项式向后遍历  
        }  
  
        //当前 A项节点指数 < B项节点指数  
        else if(Dah->exp > Dbh->exp)  
        {  
            pTail->pNext = Dbh;  //将此B项加入和链表中  
            pTail = Dbh;  
  
            Dbh = Dbh->pNext;  // //B多项式向后遍历  
        }  
  
        //如果两节点的指数相等  
        else  
        {  
            //当前指数的系数和不为0  
            //A项中保存系数和，把此A项加入和链表中  
            if(0 != (Dah->coe + Dbh->coe))  
            {  
                Dah->coe = Dah->coe + Dbh->coe;  
                pTail->pNext = Dah;  
                pTail = Dah;          
            }  
          
            //A,B都向后遍历  
            Dah = Dah->pNext;  
            Dbh = Dbh->pNext;              
        }  
    }  
  
    //插入剩余段  
    if(Dah != NULL)  
    {  
        pTail->pNext = Dah;  
    }  
    if(Dbh != NULL)  
    {  
        pTail->pNext = Dbh;  
    }  
  
    return Dj;  
}  