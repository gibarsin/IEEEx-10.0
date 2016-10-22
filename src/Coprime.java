import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coprime {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final int testCases = scanner.nextInt();
    for(int i=0; i < testCases; i++) {

      //scanner.nextLine();
      long N = scanner.nextLong();
      long A = scanner.nextLong();
      long B = scanner.nextLong();

      List<Long> inti = new ArrayList<>();
      for(long j = A; j<=B; j++){
        if(coprime(N, j)){
          inti.add(j%1000000007);
        }
      }
      long sum = inti.parallelStream().mapToLong(e -> Long.valueOf(e)).sum();
//      long sum = 0;
//      for (Long e : inti){
//        sum+=e % 1000000007;
//      }

      System.out.println(String.valueOf(sum));
    }
  }

  static boolean coprime(long u, long v)
  {
    if (((u | v) & 1) == 0) return false;

    while ((u & 1) == 0) u >>= 1;
    if (u == 1) return true;

    do
    {
      while ((v & 1) == 0) v >>= 1;
      if (v == 1) return true;

      if (u > v) { long t = v; v = u; u = t; }
      v -= u;
    } while (v != 0);

    return false;
  }
}
