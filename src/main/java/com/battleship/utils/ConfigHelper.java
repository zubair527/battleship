package com.battleship.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper
{
    private static final String dbPropertiesFile = "hsqldb.properties";

    private static final String gameMessagesFile = "game_messages.properties";

    public static Properties loadDBProperties()
    {
        return loadProperties(dbPropertiesFile);
    }

    public static Properties loadGameMessages()
    {
        return loadProperties(gameMessagesFile);
    }

    public static String getMessageForKey(String messageKey)
    {
        Properties messages = ConfigHelper.loadGameMessages();
        return messages.getProperty(messageKey);

    }

    public static Properties loadProperties(final String filename)
    {

        Properties prop = new Properties();

        try (InputStream input = ConfigHelper.class.getClassLoader().getResourceAsStream(filename)) {
            // load a properties file from class path.
            prop.load(input);
        } catch (IOException impException) {
            System.err.println(
                "If you are developer, Place hsqldb.properties, game_messages.properties file in classpath and it contains "
                    + "required database properties.");
            impException.printStackTrace();
        }
        return prop;
    }
}
