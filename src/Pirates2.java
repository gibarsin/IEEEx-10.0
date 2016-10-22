import java.awt.*;
import java.util.*;

public class Pirates2 {
  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final int N = scanner.nextInt();
    final int M = scanner.nextInt();
    final int testCases = scanner.nextInt();
    int x1, x2, y1, y2;

    String empty = scanner.nextLine();

    char[] line;
    char[][] m = new char[N][M];

    for (int i=0; i<N; i++){
      line = scanner.nextLine().toCharArray();
      for(int j=0; j<M; j++){
        m[i][j] = line[j];
//        System.out.printf(String.valueOf(m[i][j]));
//        if(j % 11 == 0 && j != 0){
//          System.out.printf("\n");
//        }
      }
    }

    for(int i=0; i < testCases; i++) {
      x1 = scanner.nextInt() - 1;
      x2 = scanner.nextInt() - 1;
      y1 = scanner.nextInt() - 1;
      y2 = scanner.nextInt() - 1;

//      origin = x1 * M + x2;
//      dest = y1 * M + y2;

      // System.out.println(String.valueOf(findMinPath(origin, dest, m));
      Map<Integer, Integer> map = new HashMap<>();
      Set<Integer> visited = new HashSet<>();

      System.out.println(findMinPath(x1, x2, y1, y2, m, map, -1, -1, visited));
    }
  }

  private static int findMinPath(int x1, int x2, int y1, int y2, char[][] m, Map<Integer, Integer> map, int prevx1, int prevx2, Set<Integer> visited) {
    int N = m.length, M = m[0].length, nextx1, nextx2;
    char orig = m[x1][x2], dest;
    int originID, destID, changedTerrain, min = -1, res;

    if (x1 == y1 && x2 == y2) {
      return 0;
    }

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {

        nextx1 = x1 + i;
        nextx2 = x2 + j;

        destID = getID(nextx1, nextx2, M);

        // Invalid
        if ( (i == 0 && j == 0) || visited.contains(destID) || nextx1 < 0 || nextx1 == N
                || nextx2 < 0 || nextx2 == M) {
          continue;
        }

        // Look for value in cache
        dest = m[nextx1][nextx2]; // Dest ASCII
        originID = getID(x1, x2, M);
        changedTerrain = changedTerrain(orig, dest);

        if (map.get(destID) != null){
          res = map.get(destID) + changedTerrain;
        } else {
          visited.add(originID); // visit destination
          res = findMinPath(nextx1, nextx2, y1, y2, m, map, x1, x2, visited) + changedTerrain;
          visited.remove(originID); // unvisit destination

        }
        if(min == -1 || res < min){
          min = res;
          map.put(originID, res);
        }
      }
    }
    return min;
  }

  private static int getID(int x1, int x2, int M){
    // x1 * M + x2
    return x1 * M + x2;
  }

  private static int changedTerrain(char cur, char other) {
    if (cur == '~' && other == 'O'){ // cur == '~' && other == 'O'
      return 1;
    }
    return 0;
  }

}
