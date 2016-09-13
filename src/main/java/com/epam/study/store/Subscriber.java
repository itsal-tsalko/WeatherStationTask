package com.epam.study.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iuliia Tsal-Tsalko
 */
public interface Subscriber {

    void notifySubscriber(Item item);
}
