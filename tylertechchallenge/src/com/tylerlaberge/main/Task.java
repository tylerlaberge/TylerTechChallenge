package com.tylerlaberge.main;


import java.util.HashMap;
import java.util.List;

public interface Task {

    String solve(HashMap<String, String> constraints, List<HashMap<String, String>> food_items);
}
