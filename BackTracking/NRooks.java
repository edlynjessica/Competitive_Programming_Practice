import java.util.*;

public class NRooks{
    static int n;
    static int[][] board;
    static boolean[] colsUsed;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        board=new int[n][n];
        colsUsed=new boolean[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j]=sc.nextInt();
                if(board[i][j]==1){
                    colsUsed[j]=true;
                }
            }
        }
        backtrack(0); // start from first row
        // print updated board
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    // done using bactracking
    static boolean backtrack(int row){
        if(row==n) return true; // end of the board -> ie all rooks are placed in the board
        // if row already has rook
        for(int j=0;j<n;j++){
            if(board[row][j]==1){
                return backtrack(row+1);
            }
        }
        // place rook in empty row
        for(int j=0;j<n;j++){
            if(!colsUsed[j]){
                board[row][j]=1;
                colsUsed[j]=true; // place and check if its a possible move
                if(backtrack(row+1)) return true;
                // if cant be placed , backtrack
                board[row][j]=0; // undoing the move
                colsUsed[j]=false;
            }
        }
        return false;
    }
}
