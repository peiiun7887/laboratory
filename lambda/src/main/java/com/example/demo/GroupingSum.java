package main.java.com.example.demo;

import java.util.*;
import java.util.stream.Collectors;


public class GroupingSum {

    // https://stackoverflow.com/questions/26340688/group-by-and-sum-objects-like-in-sql-with-java-lambdas
    public static void main(String[] args) {

        Map<String, String> map1 = Map.of("van", "01", "ban", "02", "success", "0", "fail", "1");
        Map<String, String> map2 = Map.of("van", "02", "ban", "01", "success", "0", "fail", "1");
        Map<String, String> map3 = Map.of("van", "01", "ban", "01", "success", "1", "fail", "0");
        Map<String, String> map4 = Map.of("van", "01", "ban", "02", "success", "0", "fail", "1");
        Map<String, String> map5 = Map.of("van", "02", "ban", "01", "success", "1", "fail", "0");
        List<Map<String, String>> mapList = List.of(map1, map2, map3, map4, map5);

        // grouping by value of van & ban
        mapList.stream().collect(Collectors.groupingBy(data ->
                data.get("van") + data.get("ban"), Collectors.toList()
        )).forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("============== grouping by value of van & ban ===");
        System.out.println();

        // grouping by value of van & ban and sum success, fail value
        List<Map<String, String>> newmapList = mapList.stream().collect(Collectors.groupingBy(data ->
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

        System.out.println(newmapList);
        System.out.println("============== List of Map that grouping by value of van & ban and sum success, fail value ===");
    }


}
