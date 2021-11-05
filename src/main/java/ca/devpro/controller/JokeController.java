package ca.devpro.controller;

import ca.devpro.api.JokeDto;
import ca.devpro.core.joke.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

  private String blah;



  @Autowired
  private JokeService jokeService;

  @GetMapping()
  public List<JokeDto> findAll() {
    return jokeService.findAll();
  }

  @GetMapping("/{jokeId}")
  public JokeDto get(@PathVariable("jokeId") UUID jokeId) {
    return jokeService.get(jokeId);
  }

  @PostMapping
  public JokeDto create(@RequestBody JokeDto dto) {
    return jokeService.create(dto);
  }

  @PutMapping("/{jokeId}")
  public JokeDto update(@PathVariable("jokeId") UUID jokeId, @RequestBody JokeDto dto) {
    dto.setJokeId(jokeId);
    return jokeService.update(dto);
  }

  @DeleteMapping("/{jokeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("jokeId") UUID jokeId) {

    InputStream inputStream = null;
    try {
      String blarg = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      System.err.println(blarg);
    }catch(IOException e) {
      e.printStackTrace();
    }

    jokeService.delete(jokeId);
  }
}
