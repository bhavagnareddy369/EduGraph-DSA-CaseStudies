import java.util.*;

public class EduGraphCourses {

    Map<String, List<String>> adj = new LinkedHashMap<>();
    Map<String, Integer> color = new HashMap<>();
    boolean hasCycle = false;

    void addCourse(String c) {
        adj.putIfAbsent(c, new ArrayList<>());
    }

    void addEdge(String u, String v) {
        adj.get(u).add(v);
    }

    void bfs(String start) {

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new LinkedHashSet<>();

        q.add(start);
        visited.add(start);

        System.out.println("BFS Traversal:");

        while (!q.isEmpty()) {

            String cur = q.poll();
            System.out.print(cur + " ");

            for (String nb : adj.get(cur)) {
                if (!visited.contains(nb)) {
                    visited.add(nb);
                    q.add(nb);
                }
            }
        }
        System.out.println();
    }

    void dfs(String u) {

        color.put(u, 1); // Gray

        for (String v : adj.get(u)) {

            if (!color.containsKey(v))
                dfs(v);

            else if (color.get(v) == 1) {
                hasCycle = true;
            }
        }

        color.put(u, 2); // Black
    }

    public static void main(String[] args) {

        EduGraphCourses g = new EduGraphCourses();

        String[] courses = {
                "Math", "DSA", "DBMS",
                "OS", "Networks",
                "ML", "AI", "Cloud"
        };

        for (String c : courses)
            g.addCourse(c);

        g.addEdge("Math", "DSA");
        g.addEdge("Math", "ML");
        g.addEdge("DSA", "DBMS");
        g.addEdge("DSA", "OS");
        g.addEdge("DBMS", "AI");
        g.addEdge("OS", "Networks");
        g.addEdge("Networks", "Cloud");
        g.addEdge("ML", "AI");
        g.addEdge("OS", "AI");

        g.bfs("Math");

        for (String c : courses) {
            if (!g.color.containsKey(c))
                g.dfs(c);
        }

        System.out.println("Cycle Found: " + g.hasCycle);
    }
}