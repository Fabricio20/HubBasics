package net.notfab.hubbasics.spigot.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Result {

    @Getter
    private boolean success;
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
