package com.tuproject.forum.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tuproject.forum.dto.*;
import com.tuproject.forum.entity.Topic;
import com.tuproject.forum.service.CourseService;
import com.tuproject.forum.service.ProfileService;
import com.tuproject.forum.service.TopicService;

@RestController
@RequestMapping("/forum")
public class TopicController {

	private final TopicService topicService;
	private final CourseService courseService;
	private final ProfileService profileService;


	/***********************************
	 * REST API POST
	 * Register a new Topic
	 * END POINT :
	 * http://localhost:8080/forum/topic
	 *************************************/
	@PostMapping("/topic")
	public ResponseEntity<ResponseTopic> saveTopic(@RequestBody Topic topic){

		topic.setCourse(courseService.getCourseById(topic.getCourse().getId()));
		topic.setProfile(profileService.getProfileById(topic.getProfile().getId()));

		topic = topicService.saveTopic(topic);

		ResponseTopic responseTopic = new ResponseTopic(
				topic.getId(),
				topic.getTitle(), topic.getMessage(),
				new ResponseProfile(
						topic.getProfile().getFullName(),
						topic.getProfile().getEmail(),
						topic.getProfile().getUser().getUsername()), null);

		return new ResponseEntity<>(responseTopic, HttpStatus.CREATED);
	}


	/**************************************
	 * REST API GET
	 * Get all Topics
	 * END POINT :
	 * http://localhost:8080/forum/topics
	 ***************************************/
	@GetMapping("/topics")
	public List<ResponseTopic> getAllTopics(){

		List<ResponseTopic> responseTopicList = new ArrayList<>();
		List<Topic> topics = topicService.getAllTopics();

		topics.forEach(topic -> {
			ResponseProfile profile = new ResponseProfile(
					topic.getProfile().getFullName(),
					topic.getProfile().getEmail(),
					topic.getProfile().getUser().getUsername());


			List<ResponseResponse> responseResponses = new ArrayList<>();

			topic.getResponses().forEach(response -> {

				responseResponses.add(new ResponseResponse(
						response.getId(),
						response.getMessage(),
						response.getTopicId(),
						response.isSolution(), new ResponseProfile(
						response.getProfile().getFullName(),
						response.getProfile().getEmail(),
						response.getProfile().getUser().getUsername())));
			});



			ResponseTopic responseTopic = new ResponseTopic(
					topic.getId(),
					topic.getTitle(),
					topic.getMessage(),
					profile, responseResponses);

			responseTopicList.add(responseTopic);
		});

		return responseTopicList;
	}


	/*******************************************
	 * REST API GET
	 * Get a Topic by id
	 * END POINT :
	 * http://localhost:8080/forum/topic/1
	 ********************************************/
	@GetMapping("/topic/{id}")
	public ResponseEntity<ResponseTopic> getTopicById(@PathVariable("id") long id){

		Topic topic = topicService.getTopicById(id);
		ResponseProfile profile = new ResponseProfile(
				topic.getProfile().getFullName(),
				topic.getProfile().getEmail(),
				topic.getProfile().getUser().getUsername());


		List<ResponseResponse> responseResponses = new ArrayList<>();

		topic.getResponses().forEach(response -> {

			responseResponses.add(new ResponseResponse(
					response.getId(),
					response.getMessage(),
					response.getTopicId(),
					response.isSolution(), new ResponseProfile(
					response.getProfile().getFullName(),
					response.getProfile().getEmail(),
					response.getProfile().getUser().getUsername())));
		});


		ResponseTopic responseTopic = new ResponseTopic(
				topic.getId(),
				topic.getTitle(),
				topic.getMessage(),
				profile, responseResponses);


		return new ResponseEntity<>(responseTopic, HttpStatus.OK);
	}


	/************************************************
	 * REST API GET
	 * Get Responses of a Topic by topic id
	 * END POINT :
	 * http://localhost:8080/forum/topic/1/responses
	 *************************************************/
	@GetMapping("/topic/{id}/responses")
	public ResponseEntity<Topic> getTopicResponsesById(@PathVariable("id") long id){

		return new ResponseEntity<>(topicService.getTopicResponsesById(id), HttpStatus.OK);
	}


	/************************************************
	 * REST API PUT
	 * Update a Topic by id
	 * END POINT :
	 * http://localhost:8080/forum/topic/1
	 *************************************************/
	@PutMapping("/topic/{id}")
	public ResponseEntity<Topic> updateTopic(@PathVariable("id") long id
			,@RequestBody Topic topic){
		return new ResponseEntity<>(topicService.updateTopic(topic, id), HttpStatus.OK);
	}

	/************************************************
	 * REST API DELETE
	 * Delete a Topic by id
	 * END POINT :
	 * http://localhost:8080/forum/topic/1
	 *************************************************/
	@DeleteMapping("/topic/{id}")
	public ResponseEntity<String> deleteTopic(@PathVariable("id") long id){

		// delete topic from DB
		topicService.deleteTopic(id);

		return new ResponseEntity<>("Topic deleted successfully!.", HttpStatus.OK);
	}

}
}
