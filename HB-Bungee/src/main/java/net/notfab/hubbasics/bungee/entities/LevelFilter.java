package net.notfab.hubbasics.bungee.entities;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import net.notfab.hubbasics.bungee.HubBasics;

/**
 * Copyright (C) Fabricio20 2017 - All Rights Reserved.
 * Created by Fabricio20 on 2017-12-14.
 */
public class LevelFilter extends AbstractMatcherFilter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if(event.getLevel().isGreaterOrEqual(HubBasics.getInstance().getLoggerManager().getLevel())) return FilterReply.ACCEPT;
        return FilterReply.DENY;
    }

}
