package cse3063f19p1_abinay_myayin_aaltay.game.config;

import java.io.*;
import java.util.Properties;

public class MonopolyConfig {

    private int taxSquareCount;
    private int startingBalance;
    private int goSalary, taxPayment;
    private double pieceMoveSpeed;
    private String[] players;

    /**
     * Extract configurations from given config file
     * and validate known value ranges and etc.
     *
     * @param configFile Config file to attempt loading
     */
    public MonopolyConfig(File configFile) {
        if (!configFile.getName().endsWith(".properties"))
            throw new IllegalArgumentException("Config has wrong extension -> " + configFile.getName());

        Properties properties = readFrom(configFile);

        taxSquareCount = getValue(properties, "taxSquareCount", Integer.class);
        if (taxSquareCount <= 0)
            throw new IllegalArgumentException("taxSquareCount expected to be > 0");

        startingBalance = getValue(properties, "startingBalance", Integer.class);
        if (startingBalance <= 0)
            throw new IllegalArgumentException("startingBalance expected to be > 0");

        goSalary = getValue(properties, "goSalary", Integer.class);
        if (goSalary <= 0)
            throw new IllegalArgumentException("goSalary expected to be > 0");

        taxPayment = getValue(properties, "taxPayment", Integer.class);
        if (taxPayment <= 0)
            throw new IllegalArgumentException("taxPayment expected to be > 0");

        pieceMoveSpeed = getValue(properties, "pieceMoveSpeed", Double.class);
        if (pieceMoveSpeed <= 0)
            throw new IllegalArgumentException("pieceMoveSpeed expected to be > 0");

        players = getValue(properties, "players", String[].class);
        if (!(2 <= players.length && players.length <= 8))
            throw new IllegalArgumentException("Length of players should be in range [2,8]");
    }

    private <T> T getValue(Properties properties, String key, Class<T> type) {
        if (!properties.containsKey(key))
            throw new IllegalArgumentException("Config MUST contain " + key);

        String value = properties.getProperty(key);

        if (type == Integer.class) {
            try { return type.cast(Integer.parseInt(value)); } catch (NumberFormatException e) {
                throw new IllegalArgumentException(key + " expected to be an integer value, found -> " + value);
            }

        } else if (type == Double.class) {
            try { return type.cast(Double.parseDouble(value)); } catch (NumberFormatException e) {
                throw new IllegalArgumentException(key + " expected to be a double value, found -> " + value);
            }

        } else if (type == String[].class) {
            if (!value.startsWith("[") || !value.endsWith("]"))
                throw new IllegalArgumentException(key + " string array must start with '[' and end with ']', found -> " + value);

            String[] values = value.substring(1, value.length() - 1).split(",");
            return type.cast(values);
        }

        throw new InternalError("Unexpected type -> " + type);
    }

    private Properties readFrom(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fis);
            fis.close();
            return properties;

        } catch (IOException e) {
            throw new InternalError("Unexpected IOException raised!", e);
        }
    }

    public int getTaxSquareCount() {
        return taxSquareCount;
    }

    public int getStartingBalance() {
        return startingBalance;
    }

    public int getGoSalary() {
        return goSalary;
    }

    public int getTaxPayment() {
        return taxPayment;
    }

    public double getPieceMoveSpeed() {
        return pieceMoveSpeed;
    }

    public String[] getPlayers() {
        return players;
    }

}
