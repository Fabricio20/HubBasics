package net.notfab.hubbasics.spigot.entities;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import net.notfab.hubbasics.spigot.managers.Logger;

public class LevelFilter extends AbstractMatcherFilter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().isGreaterOrEqual(Logger.getLevel())) return FilterReply.ACCEPT;
        return FilterReply.DENY;
    }

}
