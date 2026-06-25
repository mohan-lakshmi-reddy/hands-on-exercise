package com.example.mockito_exercises;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class myservicetest {
    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);// Create Mock Object
        MyService service = new MyService(mockApi);// Create Service
        service.fetchData();// Call Method
        verify(mockApi).getData();// Verify Interaction
    }
}