/*    */ import java.net.URISyntaxException;
/*    */ 
/*    */ 
/*    */ public class main
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  8 */     for (int i = 0; i <= 120; i++) {
/*    */       try {
/* 10 */         FrameTest localFrameTest = new FrameTest(i * 16);
/*    */       }
/*    */       catch (URISyntaxException e) {
/* 13 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              A:\课程学习\课件\大二课件\小白的程序猿&射鸡狮之路\CodeRain.jar!\main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */