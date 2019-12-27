package net.notfab.hubbasics.bungee.managers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    public ConfigHandler(File folder) {
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File configFile = new File(folder, "config.yml");
        if (!configFile.exists()) {
            List<String> lines = getResource("config.yml");
            this.writeToFile(lines, new File(folder, "config.yml"));
        }
        File messagesFile = new File(folder, "messages.yml");
        if (!messagesFile.exists()) {
            List<String> lines = getResource("messages.yml");
            this.writeToFile(lines, new File(folder, "messages.yml"));
        }
    }

    private List<String> getResource(String fileName) {
        InputStream stream = this.getClass().getResourceAsStream("/" + fileName);
        List<String> lines = new ArrayList<>();
        if (stream == null) return lines;
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void writeToFile(List<String> lines, File file) {
        try {
            if (!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getPath());
            lines.forEach(line -> {
                try {
                    fw.write(line + "\n");
                } catch (IOException ignored) {
                }
            });
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}