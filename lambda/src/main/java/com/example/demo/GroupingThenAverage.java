package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GroupingThenAverage {
    private static final Logger logger = Logger.getLogger(GroupingThenAverage.class.getName());

    public static void main(String[] args) {

        List<Map<String, String>> mapList = getMapList();

        List<Map<String, String>> groupedList =mapList.stream().collect
                (Collectors.groupingBy(data ->
                        data.get("van")+String.valueOf(data.get("ban")
                        ),
                        Collectors.collectingAndThen(Collectors.toList()
                                , list -> {
                                    Map<String, String> resultMap = new HashMap<>();
                                    Map<String, String> a = list.get(0);
                                    resultMap.put("van", a.get("van"));
                                    resultMap.put("ban", a.get("ban"));

                                    // 計算平均請求處理時間
                                    Double avgTime1= list.stream().collect(Collectors.averagingDouble(data -> Double.parseDouble(data.get("time1"))));
                                    BigDecimal roundedAvgTime1 = new BigDecimal(Double.toString(avgTime1)).setScale(3, RoundingMode.HALF_UP);
                                    resultMap.put("avg_time1", roundedAvgTime1.toString());
                                    Double avgTime2 = list.stream().filter( x -> "02".equals(x.get("ban")))
                                            .collect(Collectors.averagingDouble(data -> Double.parseDouble(String.valueOf(data.get("time2")))));
                                    BigDecimal roundedAvgTime2 = new BigDecimal(Double.toString(avgTime2)).setScale(3, RoundingMode.HALF_UP);
                                    resultMap.put("avg_time2", roundedAvgTime2.toString());
                                    return resultMap;
                                })
                )).values().stream().toList();

        groupedList.forEach( data -> logger.info(data.toString()));

        logger.info("============== List of Map that grouping by value of van & ban and sum success, fail value ===");

    }

    private static List<Map<String, String>> getMapList() {

        Map<String, String> map3 = Map.of("van", "01", "ban", "01", "time1", "1.1", "time2", "1.7");
        Map<String, String> map1 = Map.of("van", "01", "ban", "02", "time1", "0.5", "time2", "1.2");
        Map<String, String> map4 = Map.of("van", "01", "ban", "02", "time1", "0.1", "time2", "0.5");
        Map<String, String> map5 = Map.of("van", "02", "ban", "01", "time1", "1.2", "time2", "1.8");
        Map<String, String> map2 = Map.of("van", "02", "ban", "01", "time1", "0.6", "time2", "1.9");
        return List.of(map1, map2, map3, map4, map5);
    }
}
