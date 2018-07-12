package com.drools.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiKeyParser {
    public static void main(String[] args) throws Exception {
        new ApiKeyParser().parse();
    }

    @SuppressWarnings("unchecked")
    private void parse() throws Exception {
        File file = new File("c:/temp/API_keys.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int i = 0;
        File outputFile = new File("c:/temp/result.csv");
        while ((line = br.readLine()) != null) {
            String[] s = line.split("\\t");
            String method = s[0];
            String json = s[1];
            Map<String, Object> m = new ObjectMapper().readValue(json, Map.class);
            List<Map<String, Object>> parameterMap = (List<Map<String, Object>>)m.get("parameterObjects");
            String uri = "";
            for (Map<String, Object> param : parameterMap) {
                if ("uri".equalsIgnoreCase(String.valueOf(param.get("objectKey")))) {
                    uri = String.valueOf(param.get("objectValue"));
                    break;
                }
            }
            if (StringUtils.isEmpty(uri)) {
                String data =
                    i + "\t" + method + "\t" + m.get("type") + "\t" + "" + "\t" + nullCheck(m.get("url")) + "\n";
                FileUtils.writeStringToFile(outputFile, data, true);
            } else {
                String data =
                    i + "\t" + method + "\t" + m.get("type") + "\t" + uri + "\t" + nullCheck(m.get("url")) + "\n";
                FileUtils.writeStringToFile(outputFile, data, true);
            }
            i++;
        }
        br.close();
    }

    private String nullCheck(Object object) {
        return StringUtils.isEmpty(object) ? "" : String.valueOf(object);
    }

}
