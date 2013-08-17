package com.teamcodeflux.devcup.android.festival.service;

import com.teamcodeflux.devcup.android.festival.model.Event;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class RestMethod {

    private static final String SERVER = "http://127.0.0.1:3000";
    private static final String API_ROOT = SERVER + "/api";
    private static final String EVENTS = API_ROOT + "/festival/1/events"; //Use Festival 1 as dedicated Festival
    private static final String POSTS = API_ROOT + "/festival/1/comments";

    private static RestTemplate restTemplate;
    private static RestTemplate formRestTemplate;

    public static List<Event> getEvents() {
        Event[] events = RestMethod.getRestTemplate().getForObject(EVENTS, Event[].class);
        return new ArrayList<Event>(asList(events));
    }

    public static List<Post> getPosts() {
        Post[] posts = RestMethod.getRestTemplate().getForObject(POSTS, Post[].class);
        return new ArrayList<Post>(asList(posts));
    }

    //Rest Template
    private static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = createRestTemplate();
        }

        return restTemplate;
    }

    private static RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters());
        return restTemplate;
    }

    private static List<HttpMessageConverter<?>> messageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());

        messageConverters.add(new ByteArrayHttpMessageConverter());

        messageConverters.add(new FormHttpMessageConverter());

        return messageConverters;
    }

    //Form Rest Template
    private static RestTemplate getFormRestTemplate() {
        if (formRestTemplate == null) {
            formRestTemplate = createFormRestTemplate();
        }

        return formRestTemplate;
    }

    private static RestTemplate createFormRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(formConverters());
        return restTemplate;
    }

    private static List<HttpMessageConverter<?>> formConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.addAll(messageConverters());
        return messageConverters;
    }
}
