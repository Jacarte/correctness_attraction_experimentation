package services.utils;

import java.util.Dictionary;
import java.util.Map;
import java.util.TreeMap;

public class CommandLineParser {

    public static Map<String, String> CONFIG = new TreeMap<>();

    static {
        CONFIG.put("size", "100");
        CONFIG.put("time", "30000");
        CONFIG.put("type", "pone");
    }

    public void run(int start , String[] chunks){


        CONFIG = new TreeMap<>();

        String[] triple = new String[2];

        int index = 0;

        for(int i = start; i < chunks.length; i++){
            triple[index] = chunks[i];

            if(index == 0)
                triple[index] = triple[index].substring(1);

            if(index == 1)
                CONFIG.put(triple[0], triple[1]);

            index = (index + 1)%2;
        }


        validateConfig();
    }

    private void validateConfig(){
        if(CONFIG.containsKey("time")){
            if(Integer.parseInt(CONFIG.get("time")) <= 0)
                throw new RuntimeException("The timeout must be greater than 0");
        }
        if(CONFIG.containsKey("size")){
            if(Integer.parseInt(CONFIG.get("size")) <= 0){
                throw new RuntimeException("The size of input test data must be greater than 0");
            }
        }

        if(CONFIG.containsKey("type")){
            if(!CONFIG.get("type").equals("pbool") && !CONFIG.get("type").equals("pone")){
                throw new RuntimeException("The experiment type must be <pone|pbool>");
            }
        }
    }
}
