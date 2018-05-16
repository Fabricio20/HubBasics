package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (c) HubBasics 2018.
 * <p>
 * The contents of this project are licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International.
 * Please read the information linked below before you attempt to use this project or it's contents to make sure you are abiding
 * by it's terms.
 * <p>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 * <p>
 * File Created by Fabricio20 on 13/12/2017.
 */
public class Result {

    @Getter private boolean success;
    private List<Object> data = new ArrayList<>();

    public Result(Boolean success, Object... data) {
        this.success = success;
        Collections.addAll(this.data, data);
    }

    public Result(Object... data) {
        this.success = true;
        Collections.addAll(this.data, data);
    }

    //

    public Result setExtras(Object... data) {
        this.data.clear();
        Collections.addAll(this.data, data);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getExtra(int extra) {
        return (T) this.data.get(extra);
    }

}
