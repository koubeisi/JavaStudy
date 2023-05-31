package org.example.map;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * @author KouBeisi
 */
@Slf4j
public class HashMapTest {

    /**
     * HashMap 遍历
     */
    @ParameterizedTest
    @MethodSource("userList")
    void hashMap(List<User> list) {
        HashMap<String, User> users = new HashMap<>(8);
        for (int i = 0; i < list.size(); i++) {
            users.put(String.valueOf(i), list.get(i));
        }

        /*遍历*/
        for (Entry<String, User> entry : users.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * LinkedHashMap并没有对key值进行排序
     * 输出结果为：
     * 5 User [name=Tom, age=24]
     * 1 User [name=Jeck, age=25]
     * 4 User [name=Jerry, age=27]
     * 2 User [name=Harry, age=29]
     * 3 User [name=Martin, age=34]
     * <p>
     * 排序后的LinkedHashMap
     * 输出结果为：
     * 1 User [name=Jeck, age=25]
     * 2 User [name=Harry, age=29]
     * 3 User [name=Martin, age=34]
     * 4 User [name=Jerry, age=27]
     * 5 User [name=Tom, age=24]
     */
    @ParameterizedTest
    @MethodSource("userList")
    void linkedHashMap(List<User> list) {
        Map<String, User> map = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(String.valueOf(i), list.get(i));
        }

        /*遍历*/
        for (Entry<String, User> entry : map.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue());
        }

        /*返回Entry集合*/
        Set<Entry<String, User>> set = map.entrySet();
        /*把Entry集合放到List里*/
        List<Entry<String, User>> entries = new ArrayList<>(set);
        /*使用Comparator接口进行排序*/
        entries.sort(Comparator.comparingInt(o -> o.getValue().getAge()));
        /*清空之前的LinkedHashMap*/
        map.clear();
        /*把排序后的List按照顺序放入之前的LinkedHashMap*/
        for (Entry<String, User> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }

        /*遍历*/
        for (Entry<String, User> entry : map.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue());
        }
    }

    static Stream<List<User>> userList() {
        return Stream.of(Arrays.asList(new User("Jeck", 25)
                , new User("Harry", 29)
                , new User("Martin", 34)
                , new User("Jerry", 27)
                , new User("Tom", 24)
        ));
    }
}

@Data
class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

}