package com.PackageMeasurementConversion.Package_Measurement_Conversion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PackageMeasurementConversionController {
    @GetMapping("/convert-measurements")
    public List<Integer> convertMeasurements(@RequestParam("measurements") String measurements) {
        return getCount(measurements);
    }

    private List<Integer> getCount(String s) {
        List<Integer> counts = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                int num = c - 'a' + 1;
                if (num == 26) {
                    int j = i + 1;
                    while (j < s.length() && s.charAt(j) == '_') {
                        j++;
                    }
                    List<Integer> tempCounts = getCount(s.substring(i + 1, j));
                    int pos = counts.size() - 1;
                    while (pos >= 0 && counts.get(pos) == 0) {
                        pos--;
                    }
                    counts.addAll(pos + 1, tempCounts);
                    i = j;
                } else {
                    int j = i + 1;
                    while (j < s.length() && s.charAt(j) == c) {
                        j++;
                    }
                    counts.add(num);
                    i = j;
                }
            } else {
                i++;
            }
        }
        return counts;
    }
}
