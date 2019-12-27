package cse3063f19p1_abinay_myayin_aaltay.game.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import cse3063f19p1_abinay_myayin_aaltay.game.PrintHelper;

import java.io.*;

/**
 * BaseConfig is the abstract base class for GSON Library.
 * Holds base attributes and functionality for generating,reading and writing config files.
 *
 * @author Anıl Altay, Ayten Binay, Merve Yayın
 */
public abstract class BaseConfig {

    /**
     * One and only GSON instance used by this simulation.
     * Includes pretty printing for IO operations.
     */
    private static Gson GSON_INSTANCE = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    /**
     * Path of the config file. Used by {@link BaseConfig#readConfig()}
     * for IO JSON deserialization purposes. Set by the constructor ({@link BaseConfig#BaseConfig(String)})
     */
    protected String path;

    /**
     * Constructs a base JSON config representation
     *
     * @param path Path to the JSON file
     */
    public BaseConfig(String path) {
        this.path = path;
    }

    /**
     * Reads the file from {@link BaseConfig#path} and
     * deserializes a config object from it
     *
     * @return Deserialized config object
     */
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

    /**
     * Generates an empty JSON file on the filesystem path
     * pointed by {@link BaseConfig#path}
     */
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

    /**
     * Resets the config object into its initial state.
     * Used when the config file is absent and being re-created.
     */
    public abstract void resetConfig();

    /**
     * Serializes the JSON config representation
     * and writes it on the filesystem location pointed by {@link BaseConfig#path}
     *
     * @throws IOException Thrown if an unexpected IO problem occurs
     */
    public void writeConfig() throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        GSON_INSTANCE.toJson(this, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

}