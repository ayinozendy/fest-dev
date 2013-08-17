package com.teamcodeflux.devcup.android.festival.service;

import android.util.Log;
import com.teamcodeflux.devcup.android.festival.model.Event;
import com.teamcodeflux.devcup.android.festival.model.Post;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class RestMethod {
    private static final String TAG = RestMethod.class.getSimpleName();

    private static final String SERVER = "https://calm-wildwood-2164.herokuapp.com";
    private static final String API_ROOT = SERVER + "/api";
    private static final String EVENT = API_ROOT + "/events/{0}";
    private static final String EVENTS = API_ROOT + "/festival/1/events"; //Use Festival 1 as dedicated Festival
    private static final String COMMENTS_FOR_EVENT = API_ROOT + "/events/{0}/comments";
    private static final String COMMENTS = API_ROOT + "/festival/1/comments";
    private static final String POST_COMMENT = API_ROOT + "/festival/1/comments";

    private static RestTemplate restTemplate;
    private static RestTemplate formRestTemplate;

    public static List<Event> getEvents() {
        Event[] events = RestMethod.getRestTemplate().getForObject(EVENTS, Event[].class);
        return new ArrayList<Event>(asList(events));
    }

    public static Event getEvent(int id) {
        Event event = RestMethod.getRestTemplate().getForObject(MessageFormat.format(EVENT, id), Event.class);
        return event;
    }

    public static List<Post> getPostsForEvent(Event event) {
        Post[] posts = RestMethod.getRestTemplate().getForObject(MessageFormat.format(COMMENTS_FOR_EVENT, event.getId()), Post[].class);
        return new ArrayList<Post>(asList(posts));
    }

    public static List<Post> getPosts() {
        Post[] posts = RestMethod.getRestTemplate().getForObject(COMMENTS, Post[].class);
        return new ArrayList<Post>(asList(posts));
    }

    public static URI postComment(Post post) {
        return postComment(post, null);
    }

    public static URI postComment(Post post, String imageFilePath) {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        parts.add("post[username]", post.getUsername());
        parts.add("post[body]", post.getPostBody());

        if (imageFilePath != null) {
            parts.add("post[image]", new FileSystemResource(imageFilePath));
        }

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<?> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(parts, requestHeaders);

        URI result = null;

        try {
            result = RestMethod.getFormRestTemplate().postForLocation(POST_COMMENT, requestEntity);
        } catch (Exception e) {
            Log.e(TAG, "Error posting to server", e);
        }

        return result;
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
        messageConverters.addAll(messageConverters());

        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());

        return messageConverters;
    }
}
