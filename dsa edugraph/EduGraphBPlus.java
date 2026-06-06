import java.util.*;

public class EduGraphBPlus {

    private TreeMap<Integer, Integer> data = new TreeMap<>();

    void insert(int marks, int rollNo) {
        data.put(marks, rollNo);
    }

    void pointSearch(int marks) {
        if (data.containsKey(marks))
            System.out.println("Roll No: " + data.get(marks));
        else
            System.out.println("Not Found");
    }

    void rangeQuery(int low, int high) {
        System.out.println("Students in range [" + low + "," + high + "]");

        for (Map.Entry<Integer, Integer> entry :
                data.subMap(low, true, high, true).entrySet()) {

            System.out.println(
                    "Marks: " + entry.getKey() +
                    " Roll No: " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        EduGraphBPlus tree = new EduGraphBPlus();

        tree.insert(45, 1001);
        tree.insert(62, 1002);
        tree.insert(78, 1003);
        tree.insert(55, 1004);
        tree.insert(89, 1005);
        tree.insert(73, 1006);
        tree.insert(91, 1007);
        tree.insert(67, 1008);
        tree.insert(82, 1009);
        tree.insert(58, 1010);
        tree.insert(95, 1011);
        tree.insert(70, 1012);

        tree.rangeQuery(70, 90);
    }
}