package com.soulseek.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.soulseek.models.Topic;
import com.soulseek.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;

	List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("spring", "Spring framework", "desciprtion1"),
			new Topic("java", "java framework", "desciprtion2"),
			new Topic("javascript", "javascript framework", "desciprtion3")));

	public List<Topic> getAllTopics() {
		// return topics;
		// List<Topic> topics = new ArrayList<Topic>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		// return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return topicRepository.findById(id).orElse(null);
	}

	public Topic getTopicbyName(@PathVariable String name) {
		return topicRepository.findByName(name);
	}

	public void addTopic(Topic topic) {
		// topics.add(topic);
		topicRepository.save(topic);
	}

	public void updateTopic(String id, Topic topic) {
		/*
		 * for (int i = 0; i < topics.size(); i++) { Topic t = topics.get(i); if
		 * (t.getId().equals(id)) { topics.set(i, topic); return; } }
		 */
		topicRepository.save(topic);
	}

}
