package net.notfab.hubbasics.plugin.messages;

/*
 * Copyright (c) 2016.
 *
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 *
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 */

import java.util.HashMap;
import java.util.Map;

import net.notfab.hubbasics.HubBasics;
import net.notfab.hubbasics.objects.SimpleConfig;
import net.notfab.hubbasics.plugin.utils.HubBasicsFile;

import lombok.Getter;

public class MessageManager {
	private final HubBasics pl;

	@Getter private Map<HubBasicsMessage, String> messages;
	@Getter private SimpleConfig messageFile;

	public MessageManager() {
		this.pl = HubBasics.getInstance();
		this.messageFile = HubBasicsFile.MESSAGES;
		this.messages = new HashMap<>();
	}

	public void loadMessages() {
		for (HubBasicsMessage message : HubBasicsMessage.values()) {
			if (!this.messageFile.contains(message.getPath())) this.messageFile.set(message.getPath(), message.getDefaultValue());
			this.messages.put(message, this.messageFile.getString(message.getPath()));
		}
		this.messageFile.saveConfig();
	}

	public String getMessage(HubBasicsMessage message) {
		if (this.messages.size() == 0) this.loadMessages();
		return this.messages.get(message);
	}
}
