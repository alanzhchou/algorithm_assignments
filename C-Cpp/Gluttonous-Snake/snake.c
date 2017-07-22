#include <windows.h>
#include <time.h>
#include <stdio.h>
#include "snake.h"
#define APP_NAME "SNAKE"
#define APP_TITLE "Snake Game"
#define GAMEOVER "GAME OVER"
#define COLUMS 40
#define ROWS 40
#define FOOD_COUNT 3
#define RED RGB(255,0,0)
#define GREEN RGB(0,255,0)
#define BLUE RGB(0,0,255)
#define YELLOW RGB(255,255,0)
#define GRAY RGB(128,128,128)
#define BLACK RGB(0,0,0)
#define WHITE RGB(255,255,255)
#define STONE RGB(192,192,192)
#define CHARS_IN_LINE 14
#define SCORE "SCORE %4d"
typedef struct _snake_body{
	int x;
	int y;
	struct _snake_body *next;
}snake_body;
typedef enum _direction{
	UP=0,
	DOWN,
	LEFT,
	RIGHT
}direction;
enum game_state{
	game_start,
	game_run,
	game_pause,
	game_over
}state = game_start;
COLORREF food_color[]={
	RED,
	BLUE,
	YELLOW
};
snake_body *head=NULL;
direction dirt;
char score_char[CHARS_IN_LINE]={0};
char *press_enter="Press Enter key...";
char *help[]={
	"press direction key to change snake's direction.",
	"press enter key to pause/resume game.",
	"enjoy it. :-",
	0
	};
int score = 0;
int table[ROWS][COLUMS]={0};
clock_t start=0;
clock_t finish=0;
HWND gameWND;
HBITMAP memBM;
HBITMAP memBMOld;
HDC memDC;
RECT clientRC;
HBRUSH blackBrush;
HBRUSH snakeBrush;
HBRUSH foodBrush[FOOD_COUNT];
HPEN grayPen;
HFONT bigFont;
HFONT smallFont;
void clear_snake(){
	snake_body *p=head;
	while (head!=NULL){
		p=head->next;
		free(head);
		head=p;
	}
}
int out_of_table(int x,int y){
	if(x<0||y<0||x>=COLUMS||y>=ROWS){
		return 1;
	}
	return 0;
}
int eat_self(int x,int y){
	snake_body *p=head;
	while(p!=NULL){
		if(x==p->x && y==p->y){
			return 1;
		}
		p=p->next;
	}
	return 0;
}
void eat_food(int x,int y){
	snake_body *p = head;
	head = (snake_body*)malloc(sizeof(snake_body));
	head->x=x;
	head->y=y;
	head->next=p;
}
void move(int x,int y){
	int tmpx,tmpy;
	snake_body *p=head;
	while(p!=NULL){
		tmpx=p->x;
		tmpy=p->y;
		p->x=x;
		p->y=y;
		x=tmpx;
		y=tmpy;
		p=p->next;
	}
}
void create_food(){
	int x,y;
	x=random(COLUMS -2);
	y=random(ROWS-2);
	if(table[y+1][x+1]>0){
		create_food();
		Sleep(1);
		return;
	}
	else{
		table[y+1][x+1]=random(FOOD_COUNT)+1;
	}
}
int random(int seed){
	if(seed==0){
		return 0;
	}
	return (rand()%seed);
}
void new_game(){
	dirt=UP;
	int i=0;
	snake_body *p;
	srand((unsigned)time(NULL));
	memset(table,0,sizeof(int)*COLUMS*ROWS);
	clear_snake();
	head=(snake_body*)malloc(sizeof(snake_body));
	head->x=COLUMS/2;
	head->y=(ROWS-4)/2;
	head->next = NULL;
	p=head;
	for(i=1;i<4;i++){
		p->next=(snake_body*)malloc(sizeof(snake_body));
		p->next->x=head->x;
		p->next->y=head->y+i;
		p->next->next=NULL;
		p=p->next;	
	}
	for(i=0;i<3;i++){
		create_food();
	}
	start = clock();
	score=0;
}
void run_game(){
	int x,y;
	finish=clock();
	if((finish-start)>100){
		x=head->x;
		y=head->y;
		switch(dirt){
			case UP:
			y--;
			break;
			case DOWN:
			y++;
			break;
			case LEFT:
			x--;
			break;
			case RIGHT:
			x++;
			break;
		}
		if(out_of_table(x,y)){
			state=game_over;
		}
		else if(eat_self(x,y)){
			state=game_over;
		}
		else if(table[y][x]){
			score+=table[y][x];
			table[y][x]=0;
			create_food();
			eat_food(x,y);
		}
		else{
			move(x,y);
		}
		start = clock();
		InvalidateRect(gameWND,NULL,TRUE);
	}
}
void draw_table(){
	HBRUSH hBrushOld;
	HPEN hPenOld;
	HFONT hFontOld;
	RECT rc;
	int x0,y0,w;
	int x,y,i,j;
	char *str;
	snake_body* snake=NULL;
	w=clientRC.bottom/(ROWS+2);
	x0=y0=w;
	FillRect(memDC,&clientRC,blackBrush);
	if(state==game_start||state==game_over){
		memcpy(&rc,&clientRC,sizeof(RECT));
		rc.bottom=rc.bottom/2;
		hFontOld=SelectObject(memDC,bigFont);
		SetBkColor(memDC,BLACK);
		if(state==game_start){
			str=APP_TITLE;
			SetTextColor(memDC,YELLOW);
		}
		else{
			str=GAMEOVER;
			SetTextColor(memDC,RED);
		}
		DrawText(memDC,str,strlen(str),&rc,DT_SINGLELINE|DT_CENTER|DT_BOTTOM);
		SelectObject(memDC,hFontOld);
		hFontOld=SelectObject(memDC,smallFont);
		rc.top=rc.bottom;
		rc.bottom=rc.bottom*2;
		if(state==game_over){
			SetTextColor(memDC,YELLOW);
			sprintf(score_char,SCORE,score);
			DrawText(memDC,score_char,strlen(score_char),&rc,DT_SINGLELINE|DT_CENTER|DT_TOP);
		}
		SetTextColor(memDC,STONE);
		DrawText(memDC,press_enter,strlen(press_enter),&rc,DT_SINGLELINE|DT_CENTER|DT_VCENTER);
		SelectObject(memDC,hFontOld);
		return;
	}
	for(i=0;i<ROWS;i++){
		for(j=0;j<COLUMS;j++){
			if(table[i][j]>0){
				x=x0+j*w;
				y=y0+i*w;
				hBrushOld=SelectObject(memDC,foodBrush[table[i][j]-1]);
				Ellipse(memDC,x,y,x+w+1,y+w+1);
			}
		}
	}
	hBrushOld = SelectObject(memDC,snakeBrush);
	snake=head;
	while(snake!=NULL){
		x=x0+snake->x*w;
		y=y0+snake->y*w;
		Rectangle(memDC,x,y,x+w+1,y+w+1);
		snake=snake->next;
	}
	hPenOld=SelectObject(memDC,grayPen);
	for(i=0;i<=ROWS;i++){
		MoveToEx(memDC,x0,y0+i*w,NULL);
		LineTo(memDC,x0+COLUMS*w,y0+i*w);
	}
	for(i=0;i<=COLUMS;i++){
		MoveToEx(memDC,x0+i*w,y0,NULL);
		LineTo(memDC,x0+i*w,y0+ROWS*w);
	}
	SelectObject(memDC,hPenOld);
	x0=x0+COLUMS*w+3*w;
	y0=y0+w;
	hFontOld = SelectObject(memDC,smallFont);
	SetTextColor(memDC,YELLOW);
	sprintf(score_char,SCORE,score);
	TextOut(memDC,x0,y0,score_char,strlen(score_char));
	x0=(COLUMS+2)*w;
	y0+=4*w;
	SetTextColor(memDC,GRAY);
	i=0;
	while(help[i]){
		TextOut(memDC,x0,y0,help[i],strlen(help[i]));
		y0+=clientRC.bottom/(ROWS+2);
		i++;
	}
	SelectObject(memDC,hFontOld);
}
void paint(){
	PAINTSTRUCT ps;
	HDC hdc;
	draw_table();
	hdc=BeginPaint(gameWND,&ps);
	BitBlt(hdc,clientRC.left,clientRC.top,clientRC.right,clientRC.bottom,memDC,0,0,SRCCOPY);
	EndPaint(gameWND,&ps);
}
void key_down(WPARAM wParam){
	if(state!=game_run){
		if(wParam==VK_RETURN){
			switch(state){
				case game_start:
				state=game_run;
				break;
				case game_pause:
				state=game_run;
				break;
				case game_over:
				new_game();
				state = game_run;
				break;
			}
		}
	}
	else{
		switch(wParam){
			case VK_UP:
			if(dirt!=DOWN){
				dirt=UP;
			}
			break;
			case VK_LEFT:
			if(dirt!=RIGHT){
				dirt=LEFT;
			}
			break;
			case VK_RIGHT:
			if(dirt!=LEFT){
				dirt=RIGHT;
			}
			break;
			case VK_DOWN:
			if(dirt!=UP){
				dirt=DOWN;
			}
			break;
			case VK_RETURN:
			state=game_pause;
			break;
		}
	}
	InvalidateRect(gameWND,NULL,TRUE);
}
void resize(){
	HDC hdc;
	LOGFONT lf;
	hdc=GetDC(gameWND);
	GetClientRect(gameWND,&clientRC);
	SelectObject(memDC,memBMOld);
	DeleteObject(memBM);
	memBM=CreateCompatibleBitmap(hdc,clientRC.right,clientRC.bottom);
	memBMOld=SelectObject(memDC,memBM);
	DeleteObject(bigFont);
	memset(&lf,0,sizeof(LOGFONT));
	lf.lfWidth=(clientRC.right-clientRC.left)/CHARS_IN_LINE;
	lf.lfHeight=(clientRC.bottom-clientRC.top)/4;
	lf.lfItalic=1;
	lf.lfWeight=FW_BOLD;
	bigFont=CreateFontIndirect(&lf);
	DeleteObject(smallFont);
	lf.lfHeight=clientRC.bottom/(ROWS+2);
	lf.lfWidth=lf.lfHeight*3/4;
	lf.lfItalic=0;
	lf.lfWeight=FW_NORMAL;
	smallFont=CreateFontIndirect(&lf);
	ReleaseDC(gameWND,hdc);
}
void initialize(){
	PAINTSTRUCT ps;
	LOGFONT lf;
	HDC hdc;
	int i;
	hdc=GetDC(gameWND);
	GetClientRect(gameWND,&clientRC);
	memDC = CreateCompatibleDC(hdc);
	memBM = CreateCompatibleBitmap(hdc,clientRC.right,clientRC.bottom);
	memBMOld = SelectObject(memDC,memBM);
	blackBrush = CreateSolidBrush(BLACK);
	snakeBrush=CreateSolidBrush(GREEN);
	for(i=0;i<FOOD_COUNT;i++){
		foodBrush[i]=CreateSolidBrush(food_color[i]);
	}
	grayPen = CreatePen(PS_SOLID,1,GRAY);
	memset(&lf,0,sizeof(LOGFONT));
	lf.lfWidth=(clientRC.right-clientRC.left)/CHARS_IN_LINE;
	lf.lfHeight=(clientRC.bottom-clientRC.top)/4;
	lf.lfItalic=1;
	lf.lfWeight=FW_BOLD;
	bigFont=CreateFontIndirect(&lf);
	lf.lfHeight=clientRC.bottom/(ROWS+2);
	lf.lfWidth=lf.lfHeight*3/4;
	lf.lfItalic=0;
	lf.lfWeight=FW_NORMAL;
	smallFont=CreateFontIndirect(&lf);
	ReleaseDC(gameWND,hdc);
	EndPaint(gameWND,&ps);
}
void finalize(){
	int i=0;
	DeleteObject(blackBrush);
	DeleteObject(snakeBrush);
	for(i=0;i<FOOD_COUNT;i++){
		DeleteObject(foodBrush[i]);
	}
	DeleteObject(grayPen);
	DeleteObject(bigFont);
	DeleteObject(smallFont);
	SelectObject(memDC,memBMOld);
	DeleteObject(memBM);
	DeleteObject(memDC);
}
LRESULT CALLBACK WndProc(HWND hwnd,UINT message,WPARAM wParam,LPARAM lParam){
	switch(message){
		case WM_SIZE:
		resize();
		return 0;
		case WM_ERASEBKGND:
		return 0;
		case WM_PAINT:
		paint();
		return 0;
		case WM_KEYDOWN:
		key_down(wParam);
		return 0;
		case WM_DESTROY:
		PostQuitMessage(0);
		return 0;
	}
	return DefWindowProc(hwnd,message,wParam,lParam);
}
int WINAPI WinMain(HINSTANCE hInstance,HINSTANCE hprevInstance,PSTR szCmdLine,int iCmdShow){
	MSG msg;
	WNDCLASS wndclass;
	wndclass.style=CS_HREDRAW|CS_VREDRAW;
	wndclass.lpfnWndProc=WndProc;
	wndclass.cbClsExtra=0;
	wndclass.cbWndExtra=0;
	wndclass.hInstance=hInstance;
	wndclass.hIcon=LoadIcon(NULL,IDI_APPLICATION);
	wndclass.hCursor=LoadCursor(NULL,IDC_ARROW);
	wndclass.hbrBackground=(HBRUSH)GetStockObject(BLACK_BRUSH);
	wndclass.lpszMenuName=NULL;
	wndclass.lpszClassName=APP_NAME;
	RegisterClass(&wndclass);
	gameWND=CreateWindow(APP_NAME,
	APP_TITLE,
	WS_OVERLAPPEDWINDOW,
	CW_USEDEFAULT,
	CW_USEDEFAULT,
	CW_USEDEFAULT,
	CW_USEDEFAULT,
	NULL,NULL,
	hInstance,NULL);
	initialize();
	ShowWindow(gameWND,iCmdShow);
	UpdateWindow(gameWND);
	new_game();
	for(;;){
		if(state==game_run){
			run_game();
		}
		if(PeekMessage(&msg,NULL,0,0,PM_NOREMOVE)){
			if(GetMessage(&msg,NULL,0,0)){
				TranslateMessage(&msg);
				DispatchMessage(&msg);
			}
			else{
				break;
			}
		}
	}
	finalize();
	return msg.wParam;
}
