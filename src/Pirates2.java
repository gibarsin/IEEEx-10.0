import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pirates2 {
  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final int N = scanner.nextInt();
    final int M = scanner.nextInt();
    final long testCases = scanner.nextLong();
    int x1, x2, y1, y2, origin, dest;

    scanner.nextLine();

    char[] line;
    char[][] m = new char[N][M];

    for (int i=0; i<N; i++){
      line = scanner.nextLine().toCharArray();
      for(int j=0; j<M; j++){
        m[i][j] = line[j];
      }
    }

    for(int i=0; i < testCases; i++) {
      x1 = scanner.nextInt() - 1;
      x2 = scanner.nextInt() - 1;
      y1 = scanner.nextInt() - 1;
      y2 = scanner.nextInt() - 1;

      origin = x1 * M + x2;
      dest = y1 * M + y2;

      // System.out.println(String.valueOf(findMinPath(origin, dest, m));
      System.out.println(String.valueOf(findMinPath(x1, x2, y1, y2, m));
    }
  }

  private static int findMinPath(int x1, int x2, int y1, int y2, char[][] m) {
    // Integer[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {}};
    Map<Point, Integer> map = new HashMap<>();
    char orig = m[x1][x2], dest;

    if(x1 == y1 && x2 == y2){
      return 0;
    }

    for(int i=-1; i<=1; i++){
      for(int j=-1; j<=1; j++){
        if(i == 0 && j == 0 || x1+i < 0 || x1+i == m.length || x2+j < 0 || x2+j == m[0].length){
          continue;
        }
        if(map.get() != null){
          return map.get()
        }
        dest = m[x1+i][x2+j];
      }
    }
      if  i = findMinPath(directions) +1< min
        min = i


  }

}
