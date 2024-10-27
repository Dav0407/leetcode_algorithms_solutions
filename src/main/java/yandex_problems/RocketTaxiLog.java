package yandex_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RocketTaxiLog {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(reader.readLine());
            List<Log> logs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String[] logParts = reader.readLine().split(" ");
                int day = Integer.parseInt(logParts[0]);
                int hour = Integer.parseInt(logParts[1]);
                int minute = Integer.parseInt(logParts[2]);
                int id = Integer.parseInt(logParts[3]);
                char status = logParts[4].charAt(0);
                logs.add(new Log(day, hour, minute, id, status));
            }

            logs.sort(Comparator.comparingInt(Log::toMinutes));

            Map<Integer, Integer> movementTimes = new HashMap<>();
            Map<Integer, Integer> startTimes = new HashMap<>();

            for (Log log : logs) {
                int rocketId = log.id;
                int time = log.toMinutes();
                char status = log.status;

                if (status == 'A')
                    startTimes.put(rocketId, time);
                else if (status == 'C' || status == 'S') {
                    if (startTimes.containsKey(rocketId)) {
                        int startTime = startTimes.get(rocketId);
                        int duration = time - startTime;
                        movementTimes.put(rocketId, movementTimes.getOrDefault(rocketId, 0) + duration);
                        startTimes.remove(rocketId);
                    }
                }
            }
            // To sort the ids by rocketId
            List<Integer> sortedIds = new ArrayList<>(movementTimes.keySet());
            Collections.sort(sortedIds);

            StringBuilder builder = new StringBuilder();
            for (Integer sortedId : sortedIds) {
                builder.append(movementTimes.get(sortedId)).append(" ");
            }
            System.out.println(builder.toString().trim());

        }
    }

    public static class Log {
        int day;
        int hour;
        int minute;
        int id;
        char status;

        public Log(int day, int hour, int minute, int id, char status) {
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.id = id;
            this.status = status;
        }

        public int toMinutes() {
            return day * 24 * 60 + hour * 60 + minute;
        }
    }

}
