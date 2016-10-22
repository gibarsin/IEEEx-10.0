import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Paginacion {

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    final int testCases = scanner.nextInt();

    for(int i=0; i < testCases; i++){

      Deque<Long> fifo = new LinkedList<>();
      Deque<Long> lru = new LinkedList<>();

      long p = scanner.nextLong(); // #pages
      long s = scanner.nextLong(); // size
      long n = scanner.nextLong(); // number of accesses

      long addr, index, fifoRep = 0, lruRep = 0;
      for(long j=0; j<n; j++){
        addr = scanner.nextLong();
        index = index(addr, s);
        fifoRep += updateFifo(index, fifo, p);
        lruRep += updateLRU(index, lru, p);
      }
      String ans = lruRep < fifoRep ? "yes " : "no ";
      System.out.println(ans + String.valueOf(fifoRep) + " " + String.valueOf(lruRep));

    }
  }

  private static long updateLRU(long index, Deque<Long> lru, long length) {
    if(lru.contains(index)){
      lru.remove(index);
      lru.offer(index);
      return 0;
    }
    else if(lru.size() < length){
      lru.offer(index);
      return 0;
    }
    lru.poll();
    lru.offer(index);
    return 1;
  }

  private static long updateFifo(long index, Deque<Long> fifo, long length) {
    if(fifo.contains(index)){
      return 0;
    }
    else if(fifo.size() < length){
      fifo.offer(index);
      return 0;
    }
    fifo.poll();
    fifo.offer(index);
    return 1;
  }


  public static long index(long addr, long size){
    return (long)Math.floor(addr/size);
  }
}
