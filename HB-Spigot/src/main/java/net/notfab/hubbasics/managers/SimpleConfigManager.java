package net.notfab.hubbasics.managers;

/*
 * Copyright (c) 2018.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import net.md_5.bungee.api.ChatColor;
import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.SimpleConfig;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SimpleConfigManager {

	private JavaPlugin plugin;
	private Map<String, SimpleConfig> configs;

	/**
	 * Manage custom configurations and files
	 */
	public SimpleConfigManager(JavaPlugin plugin) {
		this.plugin = plugin;
		this.configs = new HashMap<>();
	}

	/**
	 * Scans given file for tabs, very useful when loading YAML configuration.
	 * Any configuration loaded using the API in this class is automatically scanned.
	 * Please note that this only works for files within the plugin folder of plugin
	 * given in constructor.
	 *
	 * @param filePath Path of file
     */
	public void scan(String filePath) {
		File file = new File(plugin.getDataFolder(), filePath);
		if (!file.exists()) return;
		Scanner scanner = null;
		int lineNumber = 0;
		String line;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				lineNumber++;
				if (line.contains("\t")) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " ------------------------------------------------------ ");
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "HubBasics > Tab found in file \"" + filePath + "\" on line #" + lineNumber + "!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " ------------------------------------------------------ ");
					throw new IllegalArgumentException("Tab found in file \"" + filePath + "\" on line # " + line + "!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) scanner.close();
		}
	}

	/**
	 * Get new configuration with header
	 *
	 * @param filePath - Path to file
	 * @return - New SimpleConfig
	 */
	public SimpleConfig getNewConfig(String filePath, String[] header) {
		if (this.configs.containsKey(filePath)) return this.configs.get(filePath);
		scan(filePath);
		File file = this.getConfigFile(filePath);
		if (file != null && !file.exists()) {
			this.prepareFile(filePath);
			if (header != null && header.length != 0) {
				this.setHeader(file, header);
			}
		}
		SimpleConfig config = new SimpleConfig(this.getConfigContent(filePath), file, this.getCommentsNum(file), plugin);
		this.configs.put(filePath, config);
		return config;
	}

	/**
	 * Get new configuration
	 *
	 * @param filePath - Path to file
	 * @return - New SimpleConfig
	 */
	public SimpleConfig getNewConfig(String filePath) {
		return this.getNewConfig(filePath, null);
	}

	/**
	 * Get configuration file from string
	 *
	 * @param file - File path
	 * @return - New file object
	 */
	private File getConfigFile(String file) {
		scan(file);
		if (file == null || file.isEmpty()) return null;
		File configFile;
		if (file.contains("/")) {
			if (file.startsWith("/")) {
				configFile = new File(plugin.getDataFolder()
						+ file.replace("/", File.separator));
			} else {
				configFile = new File(plugin.getDataFolder() + File.separator
						+ file.replace("/", File.separator));
			}
		} else {
			configFile = new File(plugin.getDataFolder(), file);
		}
		return configFile;
	}

	/**
	 * Create new file for config and copy resource into it
	 *
	 * @param filePath     - Path to file
	 * @param resource - Resource to copy
	 */
	public void prepareFile(String filePath, String resource) {
		File file = this.getConfigFile(filePath);
		if (file != null && file.exists()) return;
		if(file == null) return;
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
			if (resource != null && !resource.isEmpty()) {
				this.copyResource(plugin.getResource(resource), file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create new file for config without resource
	 *
	 * @param filePath - File to create
	 */
	public void prepareFile(String filePath) {
		this.prepareFile(filePath, null);
	}

	/**
	 * Adds header block to config
	 *
	 * @param file   - Config file
	 * @param header - Header lines
	 */
	public void setHeader(File file, String[] header) {
		if (!file.exists()) return;
		try {
			String currentLine;
			StringBuilder config = new StringBuilder("");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((currentLine = reader.readLine()) != null) {
				config.append(currentLine).append("\n");
			}
			reader.close();
			config.append("# +----------------------------------------------------+ #\n");
			for (String line : header) {
				if (line.length() > 50) {
					continue;
				}
				int length = (50 - line.length()) / 2;
				StringBuilder finalLine = new StringBuilder(line);
				for (int i = 0; i < length; i++) {
					finalLine.append(" ");
					finalLine.reverse();
					finalLine.append(" ");
					finalLine.reverse();
				}
				if (line.length() % 2 != 0) {
					finalLine.append(" ");
				}
				config.append("# < ").append(finalLine.toString()).append(" > #\n");
			}
			config.append("# +----------------------------------------------------+ #");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(this.prepareConfigString(config.toString()));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read file and make comments SnakeYAML friendly
	 *
	 * @param file - Path to file
	 * @return - File as Input Stream
	 */
	public InputStream getConfigContent(File file) {
		if (!file.exists()) return null;
		try {
			int commentNum = 0;
			String addLine;
			String currentLine;
			String pluginName = this.getPluginName();
			StringBuilder whole = new StringBuilder("");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.startsWith("#")) {
					addLine = currentLine.replaceFirst("#", pluginName
							+ "_COMMENT_" + commentNum + ":");
					whole.append(addLine).append("\n");
					commentNum++;
				} else {
					whole.append(currentLine).append("\n");
				}
			}
			String config = whole.toString();
			InputStream configStream = new ByteArrayInputStream(
					config.getBytes(Charset.forName("UTF-8")));
			reader.close();
			return configStream;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get comments from file
	 *
	 * @param file - File
	 * @return - Comments number
	 */
	private int getCommentsNum(File file) {
		if (!file.exists()) return 0;
		try {
			int comments = 0;
			String currentLine;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.startsWith("#")) {
					comments++;
				}
			}
			reader.close();
			return comments;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Get config content from file
	 *
	 * @param filePath - Path to file
	 * @return - readied file
	 */
	public InputStream getConfigContent(String filePath) {
		return this.getConfigContent(this.getConfigFile(filePath));
	}

	private String prepareConfigString(String configString) {
		int lastLine = 0;
		int headerLine = 0;
		String[] lines = configString.split("\n");
		StringBuilder config = new StringBuilder("");
		for (String line : lines) {
			if (line.startsWith(this.getPluginName() + "_COMMENT")) {
				String comment = "#"
						+ line.trim().substring(line.indexOf(":") + 1);
				if (comment.startsWith("# +-")) {
					/*
	                 * If header line = 0 then it is header start, if it's equal
					 * to 1 it's the end of header
					 */
					if (headerLine == 0) {
						config.append(comment).append("\n");
						lastLine = 0;
						headerLine = 1;
					} else {
						config.append(comment).append("\n\n");
						lastLine = 0;
						headerLine = 0;
					}
				} else {
					/*
					 * Last line = 0 - Comment Last line = 1 - Normal path
					 */
					String normalComment;
					if (comment.startsWith("# ' ")) {
						normalComment = comment.substring(0,
								comment.length() - 1)
								.replaceFirst("# ' ", "# ");
					} else {
						normalComment = comment;
					}
					if (lastLine == 0) {
						config.append(normalComment).append("\n");
					} else if (lastLine == 1) {
						config.append("\n").append(normalComment).append("\n");
					}
					lastLine = 0;
				}
			} else {
				config.append(line).append("\n");
				lastLine = 1;
			}
		}
		return config.toString();
	}

	/**
	 * Saves configuration to file
	 *
	 * @param configString - Config string
	 * @param file         - Config file
	 */
	public void saveConfig(String configString, File file) {
		String configuration = this.prepareConfigString(configString);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(configuration);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPluginName() {
		return plugin.getDescription().getName();
	}

	/**
	 * Copy resource from Input Stream to file
	 *
	 * @param resource - Resource from .jar
	 * @param file     - File to write
	 */
	private void copyResource(InputStream resource, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			int lenght;
			byte[] buf = new byte[1024];
			while ((lenght = resource.read(buf)) > 0) {
				out.write(buf, 0, lenght);
			}
			out.close();
			resource.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
