public class PropertiesDome1 {
    /*
     Properties作为集合的使用
     Object setProperty (String key, String value) : 类似Map集合的put方法
     String getProperty (String key): 类似Map集合的get方法
     Set<String> stringPropertyNames() : 类似Map是集合的keySet方法
*/
    public static void main(String[] args) {
        java.util.Properties prop = new java.util.Properties();
        prop.setProperty("001", "Alice");
        prop.setProperty("002", "Bob");
        prop.setProperty("003", "Charlie");

        String name = prop.getProperty("002");
        System.out.println("ID 002: " + name);

        System.out.println("All IDs:");
        for (String key : prop.stringPropertyNames()) {
            String value = prop.getProperty(key);
            System.out.println(key + ": " + value);
        }
    }

}
