import java.util.*;

class Solution {
  public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crewQueue = new PriorityQueue<>(timetable.length); 
        for (int i = 0; i < timetable.length; i++) {
            int hours = Integer.parseInt(timetable[i].substring(0, 2));
            int minutes = Integer.parseInt(timetable[i].substring(3, 5));
            crewQueue.offer(hours * 60 + minutes);
        }

        int first = 540;
        int last = first + (n - 1) * t;
        int conPassTime = 0;
        int lastBoardTime = 0;
        int onBoardCnt = 0;

        for (int i = 0; i < n; i++) {
            int validTime = first + i * t;
            onBoardCnt = 0;
            for (int j = 0; j < m; j++) {
                if (crewQueue.isEmpty()) {
                    break;
                }
                int targetCrewTime = crewQueue.peek();
                if (targetCrewTime <= validTime) {
                    lastBoardTime = crewQueue.poll();
                    onBoardCnt++;
                }
            }
        }

        if (onBoardCnt == m) {
            conPassTime = lastBoardTime - 1;
        } else {
            conPassTime = last;
        }
      
        return parseTime(conPassTime);
    }

    private static String parseTime(int conPassTime) {
        int hours = conPassTime / 60;
        int minutes = conPassTime % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
