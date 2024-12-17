package com.example.demo;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class GroupingThenSumCount {
    private static final Logger logger = Logger.getLogger(GroupingThenSumCount.class.getName());

    // https://stackoverflow.com/questions/26340688/group-by-and-sum-objects-like-in-sql-with-java-lambdas
    public static void main(String[] args) {

        List<Map<String, String>> mapList = getMapList();

        // grouping by value of van & ban
        mapList.stream().collect(Collectors.groupingBy(data ->
                data.get("van") + data.get("ban"), Collectors.toList()
        )).forEach((key, value) -> logger.info(key + ":" + value));
        logger.info("=== grouping by value of van & ban ===");

        // grouping by value of van & ban and sum success, fail value
        List<Map<String, String>> groupedList = mapList.stream().collect(Collectors.groupingBy(data ->
                        data.get("van") + data.get("ban")))
                .values().stream()
                .map(values -> values.stream()
                        .reduce((a, b) ->
                                Map.of("van", a.get("van"), "ban", a.get("ban"),
                                        "success", String.valueOf(Integer.parseInt(a.get("success")) + Integer.parseInt(b.get("success"))),
                                        "fail", String.valueOf(Integer.parseInt(a.get("fail")) + Integer.parseInt(b.get("fail")))
                                )
                        ).orElseThrow()
                ).toList();

        groupedList.forEach( data -> logger.info(data.toString()));

        logger.info("============== List of Map that grouping by value of van & ban and sum success, fail value ===");
    }

    private static List<Map<String, String>> getMapList() {
        Map<String, String> map1 = Map.of("van", "01", "ban", "02", "success", "0", "fail", "1");
        Map<String, String> map2 = Map.of("van", "02", "ban", "01", "success", "0", "fail", "1");
        Map<String, String> map3 = Map.of("van", "01", "ban", "01", "success", "1", "fail", "0");
        Map<String, String> map4 = Map.of("van", "01", "ban", "02", "success", "0", "fail", "1");
        Map<String, String> map5 = Map.of("van", "02", "ban", "01", "success", "1", "fail", "0");
        return List.of(map1, map2, map3, map4, map5);
    }


}
