package com.practice.dummymicroservice;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

public class Playground {

    public static void main(String[] args) {
//        List<String> words = new ArrayList<>(List.of("J0hn", "John", "Main", "    John", "alpha", "", "Beta"));
//        PrintInterface printer = s -> System.out.println(s);
//
//
//        words.stream().filter(s -> {
//                            if (s.isBlank()) return false;
//
//                            for (Character c : s.toCharArray()) {
//                                if (Character.isDigit(c)) return false;
//                            }
//
//                            return true;
//                        }
//                ).map(s -> s.strip().toLowerCase())
//                .distinct()
//                .sorted()
//                .forEach(printer::printStr);

        Set<RandomObject> randomSet = new HashSet<>();

        RandomObject obj1 = new RandomObject(1, "Object 1", "Random desc");
        RandomObject obj2 = new RandomObject(1, "Object 2", "Random desc");

        randomSet.add(obj1);
        randomSet.add(obj2);

        randomSet.stream().forEach(element -> System.out.println(element.getName()));

        System.out.println(obj1.equals(obj2));
        //words.stream().forEach(printStr::printStr);

//        Map<String, String> myMap = new HashMap<>(Map.of("test", "test1"));
//        List<String> myList = new ArrayList<>(List.of("ad"));
//        Set<String> mySet = new LinkedHashSet<>(Set.of("zzzz"));
//        String randomStr = "21abzz";
//
//
//
//        if(randomStr.contains("abc")) System.out.println("TRUE!");
//        else System.out.println("POLS");
//
//        List<String> words = new ArrayList<>(List.of("J0hn", "John", "Main", "    John", "alpha", "", "Beta"));
//        words.add(null);
//        words.stream().filter(s-> {
//            if(s!=null) {
//                if (s.isBlank()) return false;
//
//                for (Character c : s.toCharArray()) {
//                    if (Character.isDigit(c)) return false;
//                }
//
//                return true;
//            }
//            return false;
//        }).map(s -> s.toLowerCase().trim()
//                ).distinct().sorted()
//                .forEach(printer::printStr);
//
//    }
    }}

@Data
@AllArgsConstructor
class RandomObject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

//    @Override
//    public boolean equals(Object obj){
//        if(this == obj) return true;
//
//        if(obj == null || obj.getClass()!= this.getClass())
//            return false;
//
//        RandomObject myObj = (RandomObject) obj;
//        return myObj.getId() == this.id;
//    }
//
//    @Override
//    public int hashCode(){
//        return this.id;
//    }
}
