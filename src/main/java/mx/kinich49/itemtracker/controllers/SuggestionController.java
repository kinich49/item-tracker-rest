package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.dtos.SuggestionsDto;
import mx.kinich49.itemtracker.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/suggestions")
@SuppressWarnings("unused")
@CrossOrigin
public class SuggestionController {

    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<JsonApi<SuggestionsDto>> getSuggestionsLike(@RequestParam String name) {
        return suggestionService.findSuggestionsLike(name)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
