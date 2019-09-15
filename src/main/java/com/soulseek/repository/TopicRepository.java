package com.soulseek.repository;

import org.springframework.data.repository.CrudRepository;

import com.soulseek.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {
	public Topic findByName(String name);
}
