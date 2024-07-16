package com.tuproject.forum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tuproject.forum.dto.*;
import com.tuproject.forum.entity.Response;
import com.tuproject.forum.entity.Topic;
import com.tuproject.forum.service.ProfileService;
import com.tuproject.forum.service.ResponseService;
import com.tuproject.forum.service.TopicService;

@RestController
@RequestMapping("/forum")
public class ResponseController {

	private final ProfileService profileService;
	private final ResponseService responseService;
	private final TopicService topicService;


	/*************************************************
	 * REST API POST
	 * Register a new Response
	 * END POINT :
	 * http://localhost:8080/forum/topic/1/response
	 **************************************************/
	@PostMapping("/topic/{topicId}/response")
	public ResponseEntity<ResponseTopic> saveResponse(@RequestBody Response response, @PathVariable("topicId") long topicId){

		response.setTopicId(topicId);
		response.setProfile(profileService.getProfileById(response.getProfile().getId()));

		response = responseService.saveResponse(response);

		Topic topic = topicService.getTopicById(topicId);
		ResponseProfile profile = new ResponseProfile(
				topic.getProfile().getFullName(),
				topic.getProfile().getEmail(),
				topic.getProfile().getUser().getUsername());


		List<ResponseResponse> responseResponses = new ArrayList<>();

		topic.getResponses().forEach(topicResponse -> {

			responseResponses.add(new ResponseResponse(
					topicResponse.getId(),
					topicResponse.getMessage(),
					topicResponse.getTopicId(),
					topicResponse.isSolution(), new ResponseProfile(
					topicResponse.getProfile().getFullName(),
					topicResponse.getProfile().getEmail(),
					topicResponse.getProfile().getUser().getUsername())));
		});


		ResponseTopic responseTopic = new ResponseTopic(
				topic.getId(),
				topic.getTitle(),
				topic.getMessage(),
				profile, responseResponses);

		return new ResponseEntity<>(responseTopic, HttpStatus.CREATED);
	}


	/**************************************
	 * REST API GET
	 * Get all Responses
	 * END POINT :
	 * http://localhost:8080/forum/responses
	 ***************************************/
	/*
	@GetMapping("/responses")
	public List<Response> getAllResponse(){
		return responseService.getAllResponses();
	}
	*/
	/*******************************************
	 * REST API GET
	 * Get a Response by id
	 * END POINT :
	 * http://localhost:8080/forum/response/1
	 ********************************************/
	@GetMapping("/response/{id}")
	public ResponseEntity<Response> getResponseById(@PathVariable("id") long id){
		return new ResponseEntity<>(responseService.getResponseById(id), HttpStatus.OK);
	}

	/************************************************
	 * REST API PUT
	 * Update a Response by id
	 * END POINT :
	 * http://localhost:8080/forum/response/2
	 *************************************************/
	@PutMapping("/response/{id}")
	public ResponseEntity<Response> updateResponse(@PathVariable("id") long id
			,@RequestBody Response response){

		Response existingResponse = responseService.getResponseById(id);

		ResponseResponse responseResponse;

		HttpStatus httpStatus = HttpStatus.NOT_MODIFIED;
		if(existingResponse.getProfile().getId() == response.getProfile().getId())
		{
			response = responseService.updateResponse(response, id);

			existingResponse.setMessage(response.getMessage());
			httpStatus = HttpStatus.OK;
		}

		response.setProfile(profileService.getProfileById(response.getProfile().getId()));

		responseResponse = new ResponseResponse(
				response.getId(),
				response.getMessage(),
				response.getTopicId(),
				response.isSolution(), new ResponseProfile(
				response.getProfile().getFullName(),
				response.getProfile().getEmail(),
				response.getProfile().getUser().getUsername()));

		return new ResponseEntity<>(responseResponse, httpStatus);
	}

	/************************************************
	 * REST API DELETE
	 * Delete a Response by id
	 * END POINT :
	 * http://localhost:8080/forum/response/1
	 *************************************************/
	@DeleteMapping("/response/{id}")
	public ResponseEntity<String> deleteResponse(@PathVariable("id") long id){

		// delete response from DB
		responseService.deleteResponse(id);

		return new ResponseEntity<>("Response deleted successfully!.", HttpStatus.OK);
	}
}