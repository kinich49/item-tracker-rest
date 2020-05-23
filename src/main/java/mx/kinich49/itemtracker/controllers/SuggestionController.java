package mx.kinich49.itemtracker.controllers;

import mx.kinich49.itemtracker.dtos.SuggestionsDto;
import mx.kinich49.itemtracker.services.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping(params = "name")
    public ResponseEntity<SuggestionsDto> getSuggestionsLike(@RequestParam String name) {
        return suggestionService.findSuggestionsLike(name)
                .map(suggestions -> new ResponseEntity<>(suggestions, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
