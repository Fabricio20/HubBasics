package net.notfab.hubbasics.plugin.messages;

import java.util.HashMap;
import java.util.Map;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.SimpleConfig;

import lombok.Getter;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

public class MessageManager {
	private final HubBasics pl;

	@Getter private Map<HubBasicsMessage, String> messages;
	@Getter private SimpleConfig messageFile;

	public MessageManager() {
		this.pl = HubBasics.getInstance();
		this.messageFile = pl.getConfigManager().getNewConfig("messages.yml");
		this.messages = new HashMap<>();
	}

	public void loadMessages() {
		for (HubBasicsMessage message : HubBasicsMessage.values()) {
			if (!this.messageFile.contains(message.getFilePath())) {
				this.messageFile.set(message.getFilePath(), message.getDefaultValue());
			}

			this.messages.put(message, this.messageFile.getString(message.getFilePath()));
		}

		this.messageFile.saveConfig();
	}

	public String getMessage(HubBasicsMessage message) {
		if (this.messages.size() == 0) this.loadMessages();
		return this.messages.get(message);
	}
}
