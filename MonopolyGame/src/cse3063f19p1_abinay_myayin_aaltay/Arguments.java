package cse3063f19p1_abinay_myayin_aaltay;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arguments {

    public static final Pattern ARG_PATTERN = Pattern.compile("-(?<key>.+?):(?<value>.+)");

    private Map<String, Object> argumentTable;

    public Arguments(String[] args) {
        Map<String, Object> argumentTable = new HashMap<>();

        for (String arg : args) {
            Matcher matcher = ARG_PATTERN.matcher(arg);

            if (!matcher.matches()) {
                System.err.printf("[WARN] Expected an argument in '-key:value' format, skipping -> %s\n", arg);
                continue;
            }

            String key = matcher.group("key");
            String value = matcher.group("value");

            if (value.startsWith("[") && value.endsWith("]")) {
                String[] values = value.substring(1, value.length() - 1).split(",");
                argumentTable.put(key, values);

            } else if (isInteger(value)) {
                argumentTable.put(key, Integer.parseInt(value));

            } else {
                argumentTable.put(key, value);
            }

        }

        this.argumentTable = argumentTable;
    }

    private boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getString(String key) {
        return getValue(key, String.class, null);
    }

    public String[] getStrings(String key) {
        return getValue(key, String[].class, null);
    }

    public int getInteger(String key) {
        return getValue(key, Integer.class, 0);
    }

    public double getDouble(String key) {
        return getValue(key, Double.class, 0.0);
    }

    private <T> T getValue(String key, Class<T> type, T defaultValue) {
        Object value = argumentTable.get(key);
        return !type.isInstance(value) ? defaultValue : type.cast(value);
    }

}
