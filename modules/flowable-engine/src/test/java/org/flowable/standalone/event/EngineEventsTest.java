/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */
package org.flowable.standalone.event;

import org.flowable.engine.delegate.event.FlowableEngineEventType;
import org.flowable.engine.impl.test.ResourceFlowableTestCase;
import org.flowable.engine.test.api.event.TestFlowableEventListener;

/**
 * Test to verify event-listeners, which are configured in the cfg.xml, are notified.
 * 
 * @author Frederik Heremans
 */
public class EngineEventsTest extends ResourceFlowableTestCase {

    public EngineEventsTest() {
        super("org/flowable/standalone/event/flowable-eventlistener.cfg.xml");
    }

    public void testEngineEventsTest() {
        // Fetch the listener to check received events
        TestFlowableEventListener listener = (TestFlowableEventListener) processEngineConfiguration.getBeans().get("eventListener");
        assertNotNull(listener);

        // Check create-event
        assertEquals(FlowableEngineEventType.ENGINE_CREATED, listener.getEventsReceived().get(0).getType());
        listener.clearEventsReceived();

        // Check close-event
        processEngine.close();
        assertEquals(FlowableEngineEventType.ENGINE_CLOSED, listener.getEventsReceived().get(listener.getEventsReceived().size() - 1).getType());

    }

}