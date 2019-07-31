package org.ff4j.event;

/*-
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2017 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.ff4j.event.Event.Action;
import org.ff4j.event.Event.Source;
import org.ff4j.utils.TimeUtils;
import org.ff4j.utils.Util;

/**
 * Sample Query into Event Repository.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class EventQueryDefinition implements Serializable {
	
	/** Serial. */
	private static final long serialVersionUID = -1649081647715140190L;

	/** Bound bottom. */
	private long from = TimeUtils.getTodayMidnightTime();
	
	/** Bound top. */
	private long to = TimeUtils.getTomorrowMidnightTime();
	
	/** Filter about names (feature uid, property name). */
	private Set < String > namesFilter = new HashSet<>();
	
	/** Filter on action @see {@link EventConstants}. */
	private Set < Action > actionFilters = new HashSet<>();
	
	/** Filter on source @see {@link EventConstants}. */
    private Set < Source > sourceFilters = new HashSet<>();
    
    /** Filter on source @see {@link EventConstants}. */
    private Set < String > hostFilters = new HashSet<>();
    
	/**
	 * Default constucot
	 */
	public EventQueryDefinition() {
	}
	
	/**
	 * Constructor for a slot.
	 */
	public EventQueryDefinition(long from, long to) {
		this.from = from;
		this.to = to;
	}
	
	public EventQueryDefinition addFilterName(String name) {
	    this.namesFilter.add(name);
	    return this;
	}
	
	public EventQueryDefinition addFilterAction(Action action) {
        this.actionFilters.add(action);
        return this;
    }
	
	public EventQueryDefinition addFilterSource(Source source) {
        this.sourceFilters.add(source);
        return this;
    }
	
	public EventQueryDefinition addFilterHost(String host) {
        this.hostFilters.add(host);
        return this;
    }

	/**
	 * Match Event.
	 *
	 * @param evt
	 *         current event over this query
	 * @return
	 *         if event is valid         
	 */
	public boolean match(Event evt) {
	    return (evt.getTimestamp() >= from) && 
	           (evt.getTimestamp() <= to)   &&
	           matchAction(evt.getAction()) && 
	           matchHost(evt.getHostName()) &&
               matchName(evt.getTargetUid()) &&
               matchSource(evt.getSource());
	}
	
	// actionFilters.isEmpty() = No filter
	public boolean matchAction(Action action) {
	    return actionFilters.isEmpty() || actionFilters.contains(action);
	}
	
	public boolean matchSource(Source source) {
	    return sourceFilters.isEmpty() ||  sourceFilters.contains(source);
    }
    
    public boolean matchHost(String host) {
        return (hostFilters.isEmpty()) || (Util.hasLength(host) && hostFilters.contains(host));
    }
	
    public boolean matchName(String name) {
        return (namesFilter.isEmpty()) || (Util.hasLength(name) && namesFilter.contains(name));
    }
	
	/**
	 * Getter accessor for attribute 'from'.
	 *
	 * @return
	 *       current value of 'from'
	 */
	public Long getFrom() {
		return from;
	}

	/**
	 * Setter accessor for attribute 'from'.
	 *
	 * @param from
	 * 		new value for 'from '
	 */
	public void setFrom(Long from) {
		this.from = from;
	}

	/**
	 * Getter accessor for attribute 'to'.
	 *
	 * @return
	 *       current value of 'to'
	 */
	public Long getTo() {
		return to;
	}

	/**
	 * Setter accessor for attribute 'to'.
	 *
	 * @param to
	 * 		new value for 'to '
	 */
	public void setTo(Long to) {
		this.to = to;
	}

	/**
	 * Getter accessor for attribute 'namesFilter'.
	 *
	 * @return
	 *       current value of 'namesFilter'
	 */
	public Set<String> getNamesFilter() {
		return namesFilter;
	}

	/**
	 * Setter accessor for attribute 'namesFilter'.
	 * @param namesFilter
	 * 		new value for 'namesFilter '
	 */
	public void setNamesFilter(Set<String> namesFilter) {
		this.namesFilter = namesFilter;
	}

    /**
     * Getter accessor for attribute 'actionFilters'.
     *
     * @return
     *       current value of 'actionFilters'
     */
    public Set<Action> getActionFilters() {
        return actionFilters;
    }

    /**
     * Setter accessor for attribute 'actionFilters'.
     * @param actionFilters
     * 		new value for 'actionFilters '
     */
    public void setActionFilters(Set<Action> actionFilters) {
        this.actionFilters = actionFilters;
    }

    /**
     * Getter accessor for attribute 'sourceFilters'.
     *
     * @return
     *       current value of 'sourceFilters'
     */
    public Set<Source> getSourceFilters() {
        return sourceFilters;
    }

    /**
     * Setter accessor for attribute 'sourceFilters'.
     * @param sourceFilters
     * 		new value for 'sourceFilters '
     */
    public void setSourceFilters(Set<Source> sourceFilters) {
        this.sourceFilters = sourceFilters;
    }

    /**
     * Getter accessor for attribute 'hostFilters'.
     *
     * @return
     *       current value of 'hostFilters'
     */
    public Set<String> getHostFilters() {
        return hostFilters;
    }

    /**
     * Setter accessor for attribute 'hostFilters'.
     * @param hostFilters
     * 		new value for 'hostFilters '
     */
    public void setHostFilters(Set<String> hostFilters) {
        this.hostFilters = hostFilters;
    }

    /**
     * Setter accessor for attribute 'from'.
     * @param from
     * 		new value for 'from '
     */
    public void setFrom(long from) {
        this.from = from;
    }

    /**
     * Setter accessor for attribute 'to'.
     * @param to
     * 		new value for 'to '
     */
    public void setTo(long to) {
        this.to = to;
    }
}
