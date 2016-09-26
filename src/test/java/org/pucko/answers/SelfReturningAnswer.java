package org.pucko.answers;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.RETURNS_DEFAULTS;

/**
 * As suggested by David Wallace
 * http://stackoverflow.com/questions/8501920/how-to-mock-a-builder-with-mockito
 */
public class SelfReturningAnswer implements Answer<Object> {

    public Object answer(InvocationOnMock invocation) throws Throwable {
        Object mock = invocation.getMock();
        if( invocation.getMethod().getReturnType().isInstance( mock )){
            return mock;
        }
        else{
            return RETURNS_DEFAULTS.answer(invocation);
        }
    }
}

