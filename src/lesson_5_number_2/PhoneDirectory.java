package lesson_5_number_2;

import java.util.*;

class PhoneDirectory {
    private final Map<String, List<String>> directory = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        directory.computeIfAbsent(lastName, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return directory.getOrDefault(lastName, Collections.emptyList());
    }
}
