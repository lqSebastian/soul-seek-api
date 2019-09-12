package com.soulseek.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.io.File;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soulseek.models.Topic;
import com.soulseek.services.TopicService;

@RestController
public class TopicController {

	// needs dependency injection
	@Autowired
	private TopicService topicService;

	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	public List<Topic> getAllTopics() {

		return topicService.getAllTopics();
	}

//ejemplo http://localhost:8080/audio/?song=senses%20fail%20bloody%20romance
	@RequestMapping(value = "/audio", method = RequestMethod.GET)
	public String getYoutubeAudio(@RequestParam(defaultValue = "senses%20fail%20bloody%20romance") String song) {
		String output = null;

		URL url = getClass().getResource("youtube-dl.exe");

		
		try {
			File file = new File(url.toURI());
			// Execute command
		 	String command = file.getPath() + " \"ytsearch:" + song + "\" -f bestaudio -g";

			Process process = Runtime.getRuntime().exec(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((output = input.readLine()) != null) {
				System.out.println(output);
				return output;
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/topics/name/{name}", method = RequestMethod.GET)
	public Topic getTopicbyName(@PathVariable String name) {

		return topicService.getTopicbyName(name);
	}

	/*
	 * @RequestMapping(value = "/topics/{foo}", method = RequestMethod.GET) public
	 * Topic getTopic(@PathVariable("foo") String id) {
	 */
	@RequestMapping(value = "/topics/{id}", method = RequestMethod.GET)
	public Topic getTopic(@PathVariable String id) {

		return topicService.getTopic(id);

	}

	@RequestMapping(value = "/topics", method = RequestMethod.POST)
	public void addTopic(@RequestBody Topic topic) {

		topicService.addTopic(topic);

	}

	@RequestMapping(value = "/topics/{id}", method = RequestMethod.PUT)
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {

		topicService.updateTopic(id, topic);

	}

}
