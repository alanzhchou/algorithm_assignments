/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Random;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FrameTest
/*     */   extends JFrame
/*     */   implements MouseListener, MouseMotionListener, ActionListener, KeyListener
/*     */ {
/*     */   private static JFrame frame;
/*     */   private int x;
/*     */   private int y;
/*     */   private int tempX;
/*     */   private int tempY;
/*     */   private int winX;
/*     */   private int winY;
/*     */   private int oldX;
/*     */   private int oldY;
/*     */   private static JLabel label;
/*     */   private static JTextArea ja;
/*     */   private static JTextArea fi;
/*  50 */   private static String txt = "";
/*     */   private int _X;
/*     */   private int _Y;
/*     */   private int _fY;
/*     */   int l;
/*     */   
/*     */   public static void main(String[] args) {
/*  57 */     try { FrameTest localFrameTest = new FrameTest(0);
/*     */     }
/*     */     catch (URISyntaxException e) {
/*  60 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public FrameTest(int x) throws URISyntaxException
/*     */   {
/*  66 */     init(x);
/*  67 */     new Thread(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         for (;;)
/*     */         {
/*     */           try {
/*  74 */             Thread.sleep(100L);
/*     */           }
/*     */           catch (InterruptedException e) {
/*  77 */             e.printStackTrace();
/*     */           }
/*  79 */           FrameTest.this.setXY();
/*     */         }
/*     */       }
/*     */     })
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */       .start();
/*     */   }
/*     */   
/*  85 */   public void setXY() { if (this._Y >= 1080) {
/*  86 */       this._Y = (-20 * this.l);
/*     */     }
/*  88 */     Toolkit tk = getToolkit();
/*  89 */     Dimension dm = tk.getScreenSize();
/*  90 */     setLocation(this._X, this._Y + 16);
/*  91 */     this._Y += 20;
/*     */   }
/*     */   
/*     */   private void init(int x) {
/*  95 */     frame = this;
/*  96 */     Toolkit tk = getToolkit();
/*  97 */     Dimension dm = tk.getScreenSize();
/*     */     
/*  99 */     frame.setUndecorated(true);
/* 100 */     frame.setVisible(true);
/* 101 */     frame.addMouseListener(this);
/* 102 */     frame.addMouseMotionListener(this);
/*     */     
/* 104 */     setBackground(new Color(0, 0, 0, 0));
/*     */     
/* 106 */     setSize(22, 400);
/* 107 */     Random r = new Random();
/* 108 */     this._Y = r.nextInt(1080);
/* 109 */     System.out.println(this._Y);
/*     */     
/* 111 */     setLocation(x, this._Y);
/*     */     
/* 113 */     this._X = x;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */     ja = new JTextArea();
/* 122 */     Font font = new Font("黑体", 1, 18);
/* 123 */     ja.setForeground(Color.GREEN);
/* 124 */     ja.setLineWrap(true);
/* 125 */     ja.setEditable(false);
/* 126 */     ja.setFont(font);
/* 127 */     int l1 = r.nextInt(5);
/* 128 */     this.l = (3 + l1);
/* 129 */     String str = "";
/* 130 */     for (int i = 0; i < this.l; i++) {
/* 131 */       str = str + r.nextInt(2) + "\n";
/*     */     }
/* 133 */     ja.setText(str);
/* 134 */     ja.setOpaque(false);
/*     */     
/* 136 */     ja.setBounds(0, 0, 22, 200);
/* 137 */     ja.setVisible(true);
/* 138 */     getLayeredPane().add(ja, new Integer(Integer.MIN_VALUE));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 143 */     fi = new JTextArea();
/* 144 */     fi.setForeground(Color.WHITE);
/* 145 */     fi.setLineWrap(true);
/* 146 */     fi.setEditable(false);
/* 147 */     fi.setFont(font);
/* 148 */     fi.setText(r.nextInt(2));
/* 149 */     fi.setOpaque(false);
/*     */     
/* 151 */     fi.setBounds(0, 20 * this.l, 22, 200);
/* 152 */     fi.setVisible(true);
/* 153 */     getLayeredPane().add(fi, new Integer(Integer.MIN_VALUE));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 160 */     JPanel imagePanel = (JPanel)getContentPane();
/* 161 */     imagePanel.setOpaque(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 171 */     Point point = e.getPoint();
/* 172 */     Rectangle rec = frame.getBounds();
/*     */     
/* 174 */     this.winX = ((int)rec.getX());
/* 175 */     this.winY = ((int)rec.getY());
/* 176 */     this.x = ((int)point.getX());
/* 177 */     this.y = ((int)point.getY());
/* 178 */     this.tempX = (this.x - this.oldX);
/* 179 */     this.tempY = (this.y - this.oldY);
/*     */     
/* 181 */     frame.setLocation(this.winX + this.tempX, this.winY + this.tempY);
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 186 */     Rectangle rec = frame.getBounds();
/* 187 */     Point point = e.getPoint();
/* 188 */     this.tempX = ((int)point.getX());
/* 189 */     this.tempY = ((int)point.getY());
/* 190 */     this.oldX = ((int)point.getX());
/* 191 */     this.oldY = ((int)point.getY());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent arg0) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 223 */     e.getKeyCode();
/*     */   }
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ }


/* Location:              A:\课程学习\课件\大二课件\小白的程序猿&射鸡狮之路\CodeRain.jar!\FrameTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */