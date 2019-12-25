package cse3063f19p1_abinay_myayin_aaltay.game.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import cse3063f19p1_abinay_myayin_aaltay.game.PrintHelper;

import java.io.*;

/**
 * BaseConfig is the abstract base class for GSON Library.
 * Holds base attributes and functionality for generating,reading and writing config files.
 * @author Anıl Altay
 */
public abstract class BaseConfig {

    private static Gson GSON_INSTANCE = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    protected String path;

    public BaseConfig(String path) {
        this.path = path;
    }

    public BaseConfig readConfig() {
        try {
            FileReader fileReader = new FileReader(path);
            BaseConfig config = GSON_INSTANCE.fromJson(fileReader, getClass());
            fileReader.close();
            return config;

        } catch (FileNotFoundException e) {
            generate();

        } catch (IOException e) {
            throw new InternalError("Cannot read JSON from -> " + path);

        } catch (JsonSyntaxException e) {
            System.out.println("Malformed configuration..");
            throw e;
        }

        return this;
    }

    public void generate() {
        try {
            File configFile = new File(path);
            configFile.createNewFile();
            this.resetConfig();
            this.writeConfig();

        } catch (IOException e) {
            throw new InternalError("Cannot create JSON at -> " + path);
        }

    }

    public abstract void resetConfig();

    public void writeConfig() throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        GSON_INSTANCE.toJson(this, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

}
