package fir.im.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FunctionalUtil {
    public static final Grouper<HasName, String> NAME_GROUPER = new Grouper<HasName, String>() {
        public String group(HasName element) {
            return element.getName();
        }
    };

    public static <F, ELEM extends F, GROUP> Map<GROUP, ELEM> zipWith(
            Grouper<F, GROUP> grouper, Collection<ELEM> elements) {
        Map elementsByGroup = new HashMap();

        for (Iterator i$ = elements.iterator(); i$.hasNext();) {
            Object element = i$.next();
            Object group = grouper.group((F) element);
            Object grouping = elementsByGroup.get(group);
            if (grouping == null) {
                elementsByGroup.put(group, element);
            }
        }

        return elementsByGroup;
    }

    public static <ELEM extends HasName> Map<String, ELEM> zipByName(
            Collection<ELEM> elements) {
        return zipWith(NAME_GROUPER, elements);
    }

    public static <ELEM, GROUP> Map<GROUP, List<ELEM>> groupBy(
            Grouper<ELEM, GROUP> grouper, Collection<ELEM> elements) {
        Map elementsByGroup = new HashMap();

        for (Iterator i$ = elements.iterator(); i$.hasNext();) {
            Object element = i$.next();
            Object group = grouper.group((ELEM) element);
            List grouping = (List) elementsByGroup.get(group);
            if (grouping == null) {
                grouping = new LinkedList();
                elementsByGroup.put(group, grouping);
            }
            grouping.add(element);
        }

        return elementsByGroup;
    }

    public static <ELEM, GROUP> Map<GROUP, List<ELEM>> sortValues(
            Comparator<ELEM> itemSorter, Map<GROUP, List<ELEM>> items) {
        for (Map.Entry itemsByHead : items.entrySet()) {
            Collections.sort((List) itemsByHead.getValue(), itemSorter);
        }

        return items;
    }

    public static abstract interface ProviderNoException<TYPE> {
        public abstract TYPE get();
    }

    public static abstract interface Provider<TYPE, EXCEPTION extends Exception> {
        public abstract TYPE get() throws Exception;
    }

    public static abstract interface Grouper<ELEM, GROUP> {
        public abstract GROUP group(ELEM paramELEM);
    }
}